/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.impl.events;

import org.apache.commons.lang.ClassUtils;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.GrammarDecorator;
import com.sonar.sslr.api.Rule;
import com.sonar.sslr.impl.matcher.Matcher;
import com.sonar.sslr.impl.matcher.RuleImpl;

public class EventAdapterDecorator<GRAMMAR extends Grammar> implements GrammarDecorator<GRAMMAR> {
	private ParsingEventListener parsingEventListener;
	
	public EventAdapterDecorator(ParsingEventListener parsingEventListener) {
		this.parsingEventListener = parsingEventListener;
	}
	
	private void decorateMatcher(Matcher matcher) {
		for (int i = 0; i < matcher.getChildren().length; i++) {
			decorateMatcher(matcher.getChildren()[i]); /* Recursive */
			
			if (ClassUtils.isAssignable(matcher.getChildren()[i].getClass(), RuleImpl.class)) {
				matcher.setChild(i, new RuleImplAdapter(parsingEventListener, (RuleImpl)matcher.getChildren()[i]));
			}
			else if (ClassUtils.isAssignable(matcher.getChildren()[i].getClass(), Matcher.class)) {
				matcher.setChild(i, new MatcherAdapter(parsingEventListener, matcher.getChildren()[i]));
			}
		}
	}
	
	public void decorate(GRAMMAR grammar) {
		RuleImpl root = (RuleImpl)grammar.getRootRule();
		
		/* Change the root of the grammar, using reflection */
		try {
			grammar.getClass().getField(root.getName()).set(grammar, new RuleImplAdapter(parsingEventListener, root));
		} catch (Exception e) {
			throw new RuntimeException("Unable to decorate the root rule " + grammar.getClass().getName() + "." + root.getName() + " while adding the event support.");
		}
		
		decorateMatcher(root); /* Change the whole tree, recursively! */
	}
	
}