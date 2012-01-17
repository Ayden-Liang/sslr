/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.dsl.calculator;

import com.sonar.sslr.dsl.bytecode.ExecutableInstruction;

public class CalculatorPrinter implements ExecutableInstruction {

  private AbstractExpression value;
  private StringBuilder output;

  public CalculatorPrinter(StringBuilder output) {
    this.output = output;
  }

  public void addExpression(Calculator value) {
    this.value = value;
  }

  public void execute() {
    output.append(value.value());
  }

}