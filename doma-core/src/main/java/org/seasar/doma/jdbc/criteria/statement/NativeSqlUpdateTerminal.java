package org.seasar.doma.jdbc.criteria.statement;

import java.util.Objects;
import java.util.function.Consumer;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.PreparedSql;
import org.seasar.doma.jdbc.command.Command;
import org.seasar.doma.jdbc.command.UpdateCommand;
import org.seasar.doma.jdbc.criteria.context.Options;
import org.seasar.doma.jdbc.criteria.context.UpdateContext;
import org.seasar.doma.jdbc.criteria.declaration.UpdateDeclaration;
import org.seasar.doma.jdbc.criteria.declaration.WhereDeclaration;
import org.seasar.doma.jdbc.criteria.query.CriteriaQuery;
import org.seasar.doma.jdbc.criteria.query.UpdateBuilder;

public class NativeSqlUpdateTerminal extends AbstractStatement<Integer> {

  private final UpdateDeclaration declaration;

  public NativeSqlUpdateTerminal(Config config, UpdateDeclaration declaration) {
    super(Objects.requireNonNull(config));
    Objects.requireNonNull(declaration);
    this.declaration = declaration;
  }

  public Statement<Integer> where(Consumer<WhereDeclaration> block) {
    Objects.requireNonNull(block);
    declaration.where(block);
    return this;
  }

  @Override
  protected Command<Integer> createCommand() {
    UpdateContext context = declaration.getContext();
    Options options = context.getOptions();
    UpdateBuilder builder =
        new UpdateBuilder(
            config, context, createCommenter(options.comment()), options.sqlLogType());
    PreparedSql sql = builder.build();
    CriteriaQuery query = new CriteriaQuery(config, sql, getClass().getName(), EXECUTE_METHOD_NAME);
    return new UpdateCommand(query);
  }
}