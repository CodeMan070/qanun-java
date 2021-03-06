package com.mina.qanun;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mina
 */
public class Environment {

	private Map<String, Object> variblesValues = new HashMap<>();
	private Map<String, Object> constantValues = new HashMap<>();
	private Environment enclosing;

	public Environment() {
		this.enclosing = null;
	}

	public Environment(Environment enclosing) {
		this.enclosing = enclosing;
	}

	void define(Token name, Object value) {
		checkIfAlreadyDefined(name);
		variblesValues.put(name.getLexeme(), value);
	}

	Object get(Token name) {
		if (variblesValues.containsKey(name.getLexeme())) {
			return variblesValues.get(name.getLexeme());
		}
		if (constantValues.containsKey(name.getLexeme())) {
			return constantValues.get(name.getLexeme());
		}
		// if variable isn't found in current environement we make a
		// recursive call to the outer scope if there one if not it throws RuntiemeError
		if (enclosing != null) {
			return enclosing.get(name);
		}
		throw new RuntimeError(name,
				"Error: Undefined variable or undefined constant '" + name.getLexeme() + "'.");
	}

	// this seems kinda wrong or maybe not we w'll see
	Object getAt(int distance, Token name) {
		Environment environment = ancestor(distance);
		if (environment.variblesValues.containsKey(name.getLexeme())) {
			return environment.variblesValues.get(name.getLexeme());
		}
		if (environment.constantValues.containsKey(name.getLexeme())) {
			return environment.constantValues.get(name.getLexeme());
		}
		throw new RuntimeError(name,
				"Error: Undefined variable or undefined constant '" + name.getLexeme() + "'.");
	}

	void assignAt(int distance, Token name, Object value) {
		Environment environment = ancestor(distance);
		if (environment.variblesValues.containsKey(name.getLexeme())) {
			environment.variblesValues.put(name.getLexeme(), value);
		}
		if (environment.constantValues.containsKey(name.getLexeme())) {
			throw new RuntimeError(name, "Assignment of constant variable '" + name.getLexeme() + "'");
		}
	}

	Environment ancestor(int distance) {
		Environment environment = this;
		for (int i = 0; i < distance; i++) {
			environment = environment.enclosing;
		}

		return environment;
	}

	void assign(Token name, Object value) {
		if (constantValues.containsKey(name.getLexeme())) {
			throw new RuntimeError(name, "Assignment of constant variable '" + name.getLexeme() + "'");
		}
		if (variblesValues.containsKey(name.getLexeme())) {
			variblesValues.put(name.getLexeme(), value);
			return;
		}
		if (enclosing != null) {
			enclosing.assign(name, value);
			return;
		}
		throw new RuntimeError(name, "Undefined variable '" + name.getLexeme() + "'");
	}

	void defineConstant(Token name, Object value) {
		checkIfAlreadyDefined(name);
		constantValues.put(name.getLexeme(), value);
	}

	public Environment getEnclosing() {
		return enclosing;
	}

	private void checkIfAlreadyDefined(Token name) {
		if (constantValues.containsKey(name.getLexeme())) {
			throw new RuntimeError(name, "Error : redeclaration of [ " + name.getLexeme() + " ]");
		}
		if (variblesValues.containsKey(name.getLexeme())) {
			throw new RuntimeError(name, "Error : redeclaration of [ " + name.getLexeme() + " ]");
		}
	}
}
