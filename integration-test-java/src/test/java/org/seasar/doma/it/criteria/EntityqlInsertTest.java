package org.seasar.doma.it.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.it.Dbms;
import org.seasar.doma.it.IntegrationTestEnvironment;
import org.seasar.doma.it.Run;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;

@ExtendWith(IntegrationTestEnvironment.class)
public class EntityqlInsertTest {

  private final Entityql entityql;

  public EntityqlInsertTest(Config config) {
    this.entityql = new Entityql(config);
  }

  @Test
  void test() {
    Department_ d = new Department_();

    Department department = new Department();
    department.setDepartmentId(99);
    department.setDepartmentNo(99);
    department.setDepartmentName("aaa");
    department.setLocation("bbb");

    Result<Department> result = entityql.insert(d, department).execute();
    assertEquals(department, result.getEntity());

    Department department2 =
        entityql.from(d).where(c -> c.eq(d.departmentId, department.getDepartmentId())).fetchOne();
    assertNotNull(department2);
    assertEquals("aaa", department2.getDepartmentName());
  }

  @Run(onlyIf = Dbms.POSTGRESQL)
  @Test
  public void uuid() {
    Book_ b = new Book_();
    UUID id = UUID.randomUUID();

    Book book = new Book();
    book.id = id;
    book.title = "BOOK TITLE";
    entityql.insert(b, book).execute();

    Book book2 = entityql.from(b).where(c -> c.eq(b.id, id)).fetchOne();
    assertEquals(id, book2.id);
    assertEquals("BOOK TITLE", book2.title);
  }
}
