/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.apt.meta;

import static org.seasar.doma.internal.util.AssertionUtil.*;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.internal.apt.AptException;
import org.seasar.doma.internal.apt.type.EntityType;
import org.seasar.doma.message.DomaMessageCode;

/**
 * @author taedium
 * 
 */
public class AutoModifyQueryMetaFactory extends
        AbstractQueryMetaFactory<AutoModifyQueryMeta> {

    public AutoModifyQueryMetaFactory(ProcessingEnvironment env) {
        super(env);
    }

    @Override
    public QueryMeta createQueryMeta(ExecutableElement method, DaoMeta daoMeta) {
        assertNotNull(method, daoMeta);
        AutoModifyQueryMeta queryMeta = createAutoModifyQueryMeta(method,
                daoMeta);
        if (queryMeta == null) {
            return null;
        }
        doTypeParameters(queryMeta, method, daoMeta);
        doReturnType(queryMeta, method, daoMeta);
        doParameters(queryMeta, method, daoMeta);
        doThrowTypes(queryMeta, method, daoMeta);
        return queryMeta;
    }

    protected AutoModifyQueryMeta createAutoModifyQueryMeta(
            ExecutableElement method, DaoMeta daoMeta) {
        AutoModifyQueryMeta queryMeta = new AutoModifyQueryMeta();
        Insert insert = method.getAnnotation(Insert.class);
        if (insert != null && !insert.sqlFile()) {
            queryMeta.setQueryTimeout(insert.queryTimeout());
            queryMeta.setNullExcluded(insert.excludeNull());
            queryMeta.setIncludedPropertyNames(insert.include());
            queryMeta.setExcludedPropertyNames(insert.exclude());
            queryMeta.setQueryKind(QueryKind.AUTO_INSERT);
        }
        Update update = method.getAnnotation(Update.class);
        if (update != null && !update.sqlFile()) {
            queryMeta.setQueryTimeout(update.queryTimeout());
            queryMeta.setNullExcluded(update.excludeNull());
            queryMeta.setVersionIncluded(update.includeVersion());
            queryMeta.setOptimisticLockExceptionSuppressed(update
                    .suppressOptimisticLockException());
            queryMeta.setUnchangedPropertyIncluded(update.includeUnchanged());
            queryMeta.setIncludedPropertyNames(update.include());
            queryMeta.setExcludedPropertyNames(update.exclude());
            queryMeta.setQueryKind(QueryKind.AUTO_UPDATE);
        }
        Delete delete = method.getAnnotation(Delete.class);
        if (delete != null && !delete.sqlFile()) {
            queryMeta.setQueryTimeout(delete.queryTimeout());
            queryMeta.setVersionIgnored(delete.ignoreVersion());
            queryMeta.setOptimisticLockExceptionSuppressed(delete
                    .suppressOptimisticLockException());
            queryMeta.setQueryKind(QueryKind.AUTO_DELETE);
        }
        if (queryMeta.getQueryKind() == null) {
            return null;
        }
        queryMeta.setName(method.getSimpleName().toString());
        queryMeta.setExecutableElement(method);
        return queryMeta;
    }

    @Override
    protected void doReturnType(AutoModifyQueryMeta queryMeta,
            ExecutableElement method, DaoMeta daoMeta) {
        QueryReturnMeta returnMeta = createReturnMeta(method);
        if (!returnMeta.isPrimitiveInt()) {
            throw new AptException(DomaMessageCode.DOMA4001, env, returnMeta
                    .getElement());
        }
        queryMeta.setReturnMeta(returnMeta);
    }

    @Override
    protected void doParameters(AutoModifyQueryMeta queryMeta,
            ExecutableElement method, DaoMeta daoMeta) {
        List<? extends VariableElement> parameters = method.getParameters();
        int size = parameters.size();
        if (size != 1) {
            throw new AptException(DomaMessageCode.DOMA4002, env, method);
        }
        QueryParameterMeta parameterMeta = createParameterMeta(parameters
                .get(0));
        EntityType entityType = parameterMeta.getEntityType();
        if (entityType == null) {
            throw new AptException(DomaMessageCode.DOMA4003, env, parameterMeta
                    .getElement());
        }
        queryMeta.setEntityType(entityType);
        queryMeta.setEntityParameterName(parameterMeta.getName());
        queryMeta.addParameterMeta(parameterMeta);
        if (parameterMeta.isBindable()) {
            queryMeta.addBindableParameterType(parameterMeta.getName(),
                    entityType.getType());
        }
        validateEntityPropertyNames(entityType.getType(), method, queryMeta);
    }

}
