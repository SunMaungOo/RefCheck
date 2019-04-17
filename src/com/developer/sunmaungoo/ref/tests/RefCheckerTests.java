package com.developer.sunmaungoo.ref.tests;

import com.developer.sunmaungoo.ref.RefChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RefCheckerTests {
	
	@Test
	public void hasReferenceVariableTest1() {

		String className = "MyClass";

		String line = "MyClass variableName";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceVariableTest2() {

		String className = "MyClass";

		String line = "// MyClass variableName";

		RefChecker checker = new RefChecker();

		assertFalse(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceVariableTest3() {

		String className = "MyClass";

		String line = "//MyClass variableName";

		RefChecker checker = new RefChecker();

		assertFalse(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceReturnTest1() {

		String className = "MyClass";

		String line = "public MyClass get(Other parameter)";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceParameterTest1() {

		String className = "MyClass";

		String line = "public Other get(MyClass parameter)";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceParameterTest2() {

		String className = "MyClass";

		String line = "public Other get( MyClass parameter)";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceExtendsTest1() {

		String className = "MyClass";

		String line = "class Other extends MyClass";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceExtendsTest2() {

		String className = "MyClass";

		String line = "class Other extends OtherClass,MyClass";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));

	}

	@Test
	public void hasReferenceExtendsTest3() {

		String className = "MyClass";

		String line = "class Other extends OtherClass,MyClass{";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceImplementsTest1() {

		String className = "MyClass";

		String line = "class Other implements MyClass";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceImplementsTest2() {

		String className = "MyClass";

		String line = "class Other implements OtherClass,MyClass";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}

	@Test
	public void hasReferenceImplementsTest3() {

		String className = "MyClass";

		String line = "class Other implements OtherClass,MyClass{";

		RefChecker checker = new RefChecker();

		assertTrue(checker.hasReferenceLine(className, line));
	}
}
