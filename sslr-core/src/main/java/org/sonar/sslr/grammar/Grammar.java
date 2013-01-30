/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.sslr.grammar;

import com.sonar.sslr.api.Rule;

/**
 * Grammar is a collection of rules.
 * To create a new instance of grammar use {@link LexerlessGrammarBuilder} or {@link LexerfulGrammarBuilder}.
 *
 * <p>This interface is not intended to be implemented by clients.</p>
 *
 * @since 1.18
 */
public interface Grammar {

  Rule rule(GrammarRuleKey ruleKey);

}
