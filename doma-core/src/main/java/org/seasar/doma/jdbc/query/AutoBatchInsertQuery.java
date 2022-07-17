package org.seasar.doma.jdbc.query;

import static org.seasar.doma.internal.util.AssertionUtil.assertEquals;
import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;
import org.seasar.doma.internal.jdbc.entity.AbstractPostInsertContext;
import org.seasar.doma.internal.jdbc.entity.AbstractPreInsertContext;
import org.seasar.doma.internal.jdbc.sql.PreparedSqlBuilder;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcException;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.PreparedSql;
import org.seasar.doma.jdbc.SqlKind;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.entity.EntityPropertyType;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.entity.GeneratedIdPropertyType;
import org.seasar.doma.jdbc.entity.Property;
import org.seasar.doma.jdbc.id.IdGenerationConfig;
import org.seasar.doma.jdbc.id.ReservedIdProvider;
import org.seasar.doma.message.Message;

public class AutoBatchInsertQuery<ENTITY> extends AutoBatchModifyQuery<ENTITY>
    implements BatchInsertQuery {

  protected GeneratedIdPropertyType<ENTITY, ?, ?> generatedIdPropertyType;

  protected IdGenerationConfig idGenerationConfig;

  protected boolean batchSupported = true;

  protected boolean generatedKeysIgnored = false;

  public AutoBatchInsertQuery(EntityType<ENTITY> entityType) {
    super(entityType);
  }

  public void setGeneratedKeysIgnored(boolean generatedKeysIgnored) {
    this.generatedKeysIgnored = generatedKeysIgnored;
  }

  @Override
  public void prepare() {
    super.prepare();
    assertNotNull(method, entities, sqls);
    int size = entities.size();
    if (size == 0) {
      return;
    }
    executable = true;
    executionSkipCause = null;
    currentEntity = entities.get(0);
    preInsert();
    prepareIdAndVersionPropertyTypes();
    prepareOptions();
    prepareTargetPropertyTypes();
    prepareIdValue();
    prepareVersionValue();
    prepareSql();
    entities.set(0, currentEntity);
    for (ListIterator<ENTITY> it = entities.listIterator(1); it.hasNext(); ) {
      currentEntity = it.next();
      preInsert();
      prepareIdValue();
      prepareVersionValue();
      prepareSql();
      it.set(currentEntity);
    }
    currentEntity = null;
    assertEquals(entities.size(), sqls.size());
  }

  protected void preInsert() {
    AutoBatchPreInsertContext<ENTITY> context =
        new AutoBatchPreInsertContext<>(entityType, method, config);
    entityType.preInsert(currentEntity, context);
    if (context.getNewEntity() != null) {
      currentEntity = context.getNewEntity();
    }
  }

  @Override
  protected void prepareIdAndVersionPropertyTypes() {
    super.prepareIdAndVersionPropertyTypes();
    generatedIdPropertyType = entityType.getGeneratedIdPropertyType();
    if (generatedIdPropertyType != null) {
      if (idGenerationConfig == null) {
        idGenerationConfig =
            new IdGenerationConfig(
                config, entityType, new ReservedIdProvider(config, entityType, entities.size()));
        generatedIdPropertyType.validateGenerationStrategy(idGenerationConfig);
        autoGeneratedKeysSupported =
            !generatedKeysIgnored
                && generatedIdPropertyType.isAutoGeneratedKeysSupported(idGenerationConfig);
        batchSupported =
            generatedKeysIgnored || generatedIdPropertyType.isBatchSupported(idGenerationConfig);
      }
    }
  }

  protected void prepareTargetPropertyTypes() {
    targetPropertyTypes = new ArrayList<>(entityType.getEntityPropertyTypes().size());
    for (EntityPropertyType<ENTITY, ?> propertyType : entityType.getEntityPropertyTypes()) {
      if (!propertyType.isInsertable()) {
        continue;
      }
      if (propertyType.isId()) {
        if (propertyType != generatedIdPropertyType
            || generatedIdPropertyType.isIncluded(idGenerationConfig)) {
          targetPropertyTypes.add(propertyType);
        }
        if (generatedIdPropertyType == null) {
          Property<ENTITY, ?> property = propertyType.createProperty();
          property.load(currentEntity);
          if (property.getWrapper().get() == null) {
            throw new JdbcException(Message.DOMA2020, entityType.getName(), propertyType.getName());
          }
        }
        continue;
      }
      if (!isTargetPropertyName(propertyType.getName())) {
        continue;
      }
      targetPropertyTypes.add(propertyType);
    }
  }

  protected void prepareIdValue() {
    if (generatedIdPropertyType != null && idGenerationConfig != null) {
      currentEntity =
          generatedIdPropertyType.preInsert(entityType, currentEntity, idGenerationConfig);
    }
  }

  protected void prepareVersionValue() {
    if (versionPropertyType != null) {
      currentEntity = versionPropertyType.setIfNecessary(entityType, currentEntity, 1);
    }
  }

  protected void prepareSql() {
    Naming naming = config.getNaming();
    Dialect dialect = config.getDialect();
    PreparedSqlBuilder builder = new PreparedSqlBuilder(config, SqlKind.BATCH_INSERT, sqlLogType);
    builder.appendSql("insert into ");
    builder.appendSql(entityType.getQualifiedTableName(naming::apply, dialect::applyQuote));
    builder.appendSql(" (");
    for (EntityPropertyType<ENTITY, ?> p : targetPropertyTypes) {
      builder.appendSql(p.getColumnName(naming::apply, dialect::applyQuote));
      builder.appendSql(", ");
    }
    builder.cutBackSql(2);
    builder.appendSql(") values (");
    for (EntityPropertyType<ENTITY, ?> propertyType : targetPropertyTypes) {
      Property<ENTITY, ?> property = propertyType.createProperty();
      property.load(currentEntity);
      builder.appendParameter(property.asInParameter());
      builder.appendSql(", ");
    }
    builder.cutBackSql(2);
    builder.appendSql(")");
    PreparedSql sql = builder.build(this::comment);
    sqls.add(sql);
  }

  @Override
  public boolean isBatchSupported() {
    return batchSupported;
  }

  @Override
  public void generateId(Statement statement, int index) {
    if (generatedIdPropertyType != null && idGenerationConfig != null) {
      ENTITY newEntity =
          generatedIdPropertyType.postInsert(
              entityType, entities.get(index), idGenerationConfig, statement);
      entities.set(index, newEntity);
    }
  }

  @Override
  public void complete() {
    for (ListIterator<ENTITY> it = entities.listIterator(); it.hasNext(); ) {
      currentEntity = it.next();
      postInsert();
      it.set(currentEntity);
    }
  }

  protected void postInsert() {
    AutoBatchPostInsertContext<ENTITY> context =
        new AutoBatchPostInsertContext<>(entityType, method, config);
    entityType.postInsert(currentEntity, context);
    if (context.getNewEntity() != null) {
      currentEntity = context.getNewEntity();
    }
  }

  protected static class AutoBatchPreInsertContext<E> extends AbstractPreInsertContext<E> {

    public AutoBatchPreInsertContext(EntityType<E> entityType, Method method, Config config) {
      super(entityType, method, config);
    }
  }

  protected static class AutoBatchPostInsertContext<E> extends AbstractPostInsertContext<E> {

    public AutoBatchPostInsertContext(EntityType<E> entityType, Method method, Config config) {
      super(entityType, method, config);
    }
  }
}
