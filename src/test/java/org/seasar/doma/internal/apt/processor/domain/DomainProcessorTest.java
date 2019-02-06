package org.seasar.doma.internal.apt.processor.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.seasar.doma.internal.apt.CompilerSupport;
import org.seasar.doma.internal.apt.lombok.Value;
import org.seasar.doma.internal.apt.processor.DomainProcessor;
import org.seasar.doma.message.Message;

public class DomainProcessorTest extends CompilerSupport {

  @BeforeEach
  protected void setUp() throws Exception {
    addOption("-Adoma.test=true");
  }

  @Test
  public void testSalary() throws Exception {
    Class<?> target = Salary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testPrimitiveValue() throws Exception {
    Class<?> target = PrimitiveValueDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testEnum() throws Exception {
    Class<?> target = EnumDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testUnsupportedValueType() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(UnsupportedValueTypeDomain.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4102);
  }

  @Test
  public void testConstrutorNotFound() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(ConstrutorNotFoundDomain.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4103);
  }

  @Test
  public void testAccessorNotFound() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(AccessorNotFoundDomain.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4104);
  }

  @Test
  public void testInner() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer.class);
    compile();
    assertGeneratedSource(Outer.Inner.class);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testInner_deep() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer_deepInner.class);
    compile();
    assertGeneratedSource(Outer_deepInner.Middle.Inner.class);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testInner_nonStatic() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer_nonStaticInner.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4275);
  }

  @Test
  public void testInner_nonPublic() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer_nonPublicInner.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4275);
  }

  @Test
  public void testInner_illegalName() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer__illegalName.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4277);
  }

  @Test
  public void testMiddle_nonPublic() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(Outer_nonPublicMiddle.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4275);
  }

  @Test
  public void testPackagePrivate() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(PackagePrivateDomain.class);
    compile();
    assertTrue(getCompiledResult());
  }

  @Test
  public void testJobType() throws Exception {
    Class<?> target = JobType.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4184);
  }

  @Test
  public void testAbstractDomain() throws Exception {
    Class<?> target = AbstractDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4132);
  }

  @Test
  public void testOfSalary() throws Exception {
    Class<?> target = OfSalary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testOfPrimitiveValue() throws Exception {
    Class<?> target = OfPrimitiveValueDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testOfEnum() throws Exception {
    Class<?> target = OfEnumDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testOfJobType() throws Exception {
    Class<?> target = OfJobType.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testOfPrimitiveValueType() throws Exception {
    Class<?> target = OfPrimitiveValueType.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testOfAbstractDomain() throws Exception {
    Class<?> target = OfAbstractDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testGenericDomain() throws Exception {
    Class<?> target = SpecificDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testVersionCheckSuppressed() throws Exception {
    addOption("-Adoma.version.validation=false");
    Class<?> target = VersionCheckSuppressedDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testParametarizedSalary() throws Exception {
    Class<?> target = ParametarizedSalary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testParametarizedOfSalary() throws Exception {
    Class<?> target = ParametarizedOfSalary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testIllegalSizeParametarizedOfSalary() throws Exception {
    Class<?> target = IllegalSizeParametarizedOfSalary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertMessage(Message.DOMA4106);
  }

  @Test
  public void testIllegalTypeParametarizedOfSalary() throws Exception {
    Class<?> target = IllegalTypeParametarizedOfSalary.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertMessage(Message.DOMA4106);
  }

  @Test
  public void testNullRejection() throws Exception {
    Class<?> target = NullRejectionDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testIllegalAcceptNullDomain() throws Exception {
    Class<?> target = IllegalAcceptNullDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertMessage(Message.DOMA4251);
  }

  @Test
  public void testObjectDomain() throws Exception {
    Class<?> target = ObjectDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testInterface() throws Exception {
    Class<?> target = InterfaceDomain.class;
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testInterfaceFactoryOfAttributeMustNotBeNew() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(InterfaceNew.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4268);
  }

  @Test
  public void testInterfaceInner() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(InterfaceOuter.class);
    compile();
    assertGeneratedSource(InterfaceOuter.Inner.class);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testAnnotationMustNotBeDomainClass() throws Exception {
    DomainProcessor processor = new DomainProcessor();
    addProcessor(processor);
    addCompilationUnit(AnnotationDomain.class);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4105);
  }

  @Test
  public void testLombokValue() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValue.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertGeneratedSource(target);
    assertTrue(getCompiledResult());
  }

  @Test
  public void testLombokValueStaticConstructor() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueStaticConstructor.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4428);
  }

  @Test
  public void testLombokValueNoField() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueNoField.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4430);
  }

  @Test
  public void testLombokValueTwoFields() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueTwoFields.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4431);
  }

  @Test
  public void testLombokValueTypeNotAssignable() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueTypeNotAssignable.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4432);
  }

  @Test
  public void testLombokValueAccessorMethod() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueAccessorMethod.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4429);
  }

  @Test
  public void testLombokValueAccessorMethod_boolean() throws Exception {
    addOption("-Adoma.lombok.Value=" + Value.class.getName());
    DomainProcessor processor = new DomainProcessor();
    Class<?> target = LombokValueAccessorMethod_boolean.class;
    addProcessor(processor);
    addCompilationUnit(target);
    compile();
    assertFalse(getCompiledResult());
    assertMessage(Message.DOMA4429);
  }
}
