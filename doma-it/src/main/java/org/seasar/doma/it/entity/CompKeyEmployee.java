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
package org.seasar.doma.it.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.seasar.doma.ChangedProperties;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.seasar.doma.it.ItNamingConvention;

@Entity(namingConvention = ItNamingConvention.class)
@Table(name = "COMP_KEY_EMPLOYEE")
public class CompKeyEmployee {

    @Id
    Integer employeeId1;

    @Id
    Integer employeeId2;

    Integer employeeNo;

    String employeeName;

    Integer managerId1;

    Integer managerId2;

    Date hiredate;

    BigDecimal salary;

    Integer departmentId1;

    Integer departmentId2;

    Integer addressId1;

    Integer addressId2;

    @Version
    Integer version;

    @ChangedProperties
    Set<String> changedProperties = new HashSet<String>();

    public Integer getEmployeeId1() {
        return employeeId1;
    }

    public void setEmployeeId1(Integer employeeId1) {
        changedProperties.add("employeeId1");
        this.employeeId1 = employeeId1;
    }

    public Integer getEmployeeId2() {
        return employeeId2;
    }

    public void setEmployeeId2(Integer employeeId2) {
        changedProperties.add("employeeId2");
        this.employeeId2 = employeeId2;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        changedProperties.add("employeeNo");
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        changedProperties.add("employeeName");
        this.employeeName = employeeName;
    }

    public Integer getManagerId1() {
        return managerId1;
    }

    public void setManagerId1(Integer managerId1) {
        changedProperties.add("managerId1");
        this.managerId1 = managerId1;
    }

    public Integer getManagerId2() {
        return managerId2;
    }

    public void setManagerId2(Integer managerId2) {
        changedProperties.add("managerId2");
        this.managerId2 = managerId2;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        changedProperties.add("hiredate");
        this.hiredate = hiredate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        changedProperties.add("salary");
        this.salary = salary;
    }

    public Integer getDepartmentId1() {
        return departmentId1;
    }

    public void setDepartmentId1(Integer departmentId1) {
        changedProperties.add("departmentId1");
        this.departmentId1 = departmentId1;
    }

    public Integer getDepartmentId2() {
        return departmentId2;
    }

    public void setDepartmentId2(Integer departmentId2) {
        changedProperties.add("departmentId2");
        this.departmentId2 = departmentId2;
    }

    public Integer getAddressId1() {
        return addressId1;
    }

    public void setAddressId1(Integer addressId1) {
        changedProperties.add("addressId1");
        this.addressId1 = addressId1;
    }

    public Integer getAddressId2() {
        return addressId2;
    }

    public void setAddressId2(Integer addressId2) {
        changedProperties.add("addressId2");
        this.addressId2 = addressId2;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        changedProperties.add("version");
        this.version = version;
    }

}
