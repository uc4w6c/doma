update EMPLOYEE set SALARY = /*staff.staffInfo.salary*/1000, VERSION = /*staff.version*/0 + 1 where EMPLOYEE_ID = /*staff.employeeId*/1 and VERSION = /*staff.version*/0