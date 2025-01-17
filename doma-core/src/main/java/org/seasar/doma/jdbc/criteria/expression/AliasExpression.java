package org.seasar.doma.jdbc.criteria.expression;

import java.util.Objects;
import org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel;
import org.seasar.doma.jdbc.entity.EntityPropertyType;

public class AliasExpression<PROPERTY> implements PropertyMetamodel<PROPERTY> {

  private final PropertyMetamodel<PROPERTY> originalPropertyMetamodel;
  private final String alias;

  public AliasExpression(PropertyMetamodel<PROPERTY> originalPropertyMetamodel, String alias) {
    this.originalPropertyMetamodel = Objects.requireNonNull(originalPropertyMetamodel);
    this.alias = Objects.requireNonNull(alias);
  }

  @Override
  public EntityPropertyType<?, ?> asType() {
    return originalPropertyMetamodel.asType();
  }

  @Override
  public Class<?> asClass() {
    return originalPropertyMetamodel.getClass();
  }

  @Override
  public String getName() {
    return originalPropertyMetamodel.getName();
  }

  @Override
  public void accept(PropertyMetamodel.Visitor visitor) {
    if (visitor instanceof AliasExpression.Visitor) {
      AliasExpression.Visitor v = (AliasExpression.Visitor) visitor;
      v.visit(this);
    }
  }

  public PropertyMetamodel<PROPERTY> getOriginalPropertyMetamodel() {
    return originalPropertyMetamodel;
  }

  public String getAlias() {
    return alias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AliasExpression<?> that = (AliasExpression<?>) o;
    return originalPropertyMetamodel.equals(that.originalPropertyMetamodel)
        && alias.equals(that.alias);
  }

  @Override
  public int hashCode() {
    return Objects.hash(originalPropertyMetamodel, alias);
  }

  public interface Visitor {

    void visit(AliasExpression<?> expression);
  }
}
