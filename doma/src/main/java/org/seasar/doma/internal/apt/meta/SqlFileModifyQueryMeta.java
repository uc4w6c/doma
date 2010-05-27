/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

import org.seasar.doma.internal.apt.mirror.ModifyMirror;
import org.seasar.doma.internal.apt.type.EntityType;

/**
 * @author taedium
 * 
 */
public class SqlFileModifyQueryMeta extends AbstractSqlFileQueryMeta {

    protected final List<EntityType> entityTypes = new ArrayList<EntityType>();

    protected ModifyMirror modifyMirror;

    public SqlFileModifyQueryMeta(ExecutableElement method) {
        super(method);
    }

    public List<EntityType> getEntityTypes() {
        return entityTypes;
    }

    public void addEntityType(EntityType entityType) {
        entityTypes.add(entityType);
    }

    public ModifyMirror getModifyMirror() {
        return modifyMirror;
    }

    public void setModifyMirror(ModifyMirror modifyMirror) {
        this.modifyMirror = modifyMirror;
    }

    @Override
    public <R, P> R accept(QueryMetaVisitor<R, P> visitor, P p) {
        return visitor.visistSqlFileModifyQueryMeta(this, p);
    }
}
