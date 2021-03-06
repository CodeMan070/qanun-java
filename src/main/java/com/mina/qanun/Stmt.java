package com.mina.qanun;

import java.util.List;

import java.util.Map;

abstract class Stmt {

	interface Visitor<R> {

		R visitBlockStmt(Block stmt);

		R visitExpressionStmt(Expression stmt);

		R visitFunctionStmt(Function stmt);

		R visitClassStmt(Class stmt);

		R visitModuleStmt(Module stmt);

		R visitIfStmt(If stmt);

		R visitReturnStmt(Return stmt);

		R visitVarStmt(Var stmt);

		R visitValStmt(Val stmt);

		R visitWhileStmt(While stmt);

		R visitForStmt(For stmt);

		R visitForEachStmt(ForEach stmt);

		R visitBreakStmt(Break stmt);

		R visitContinueStmt(Continue stmt);

		R visitSwitchStmt(Switch stmt);

		R visitImportStmt(Import stmt);
	}

	static class Block extends Stmt {

		Block(List<Stmt> statements) {
			this.statements = statements;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitBlockStmt(this);
		}

		final List<Stmt> statements;
	}

	static class Expression extends Stmt {

		Expression(Expr expression) {
			this.expression = expression;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitExpressionStmt(this);
		}

		final Expr expression;
	}

	static class Function extends Stmt {

		Function(Token name, Expr.AnonymousFun anonFun) {
			this.name = name;
			this.anonFun = anonFun;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitFunctionStmt(this);
		}

		final Token name;
		final Expr.AnonymousFun anonFun;
	}

	static class Class extends Stmt {

		Class(Token name, Expr.Variable superClass, List<Stmt.Function> methods, List<Stmt.Function> staticMethods) {
			this.name = name;
			this.superClass = superClass;
			this.methods = methods;
			this.staticMethods = staticMethods;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitClassStmt(this);
		}

		final Token name;
		final Expr.Variable superClass;
		final List<Stmt.Function> methods;
		final List<Stmt.Function> staticMethods;
	}

	static class Module extends Stmt {

		Module(Token name, List<Stmt.Class> classes, List<Stmt.Function> functions, List<Stmt.Var> variables, List<Stmt.Val> constants) {
			this.name = name;
			this.classes = classes;
			this.functions = functions;
			this.variables = variables;
			this.constants = constants;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitModuleStmt(this);
		}

		final Token name;
		final List<Stmt.Class> classes;
		final List<Stmt.Function> functions;
		final List<Stmt.Var> variables;
		final List<Stmt.Val> constants;
	}

	static class If extends Stmt {

		If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
			this.condition = condition;
			this.thenBranch = thenBranch;
			this.elseBranch = elseBranch;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitIfStmt(this);
		}

		final Expr condition;
		final Stmt thenBranch;
		final Stmt elseBranch;
	}

	static class Return extends Stmt {

		Return(Token keyword, Expr value) {
			this.keyword = keyword;
			this.value = value;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitReturnStmt(this);
		}

		final Token keyword;
		final Expr value;
	}

	static class Var extends Stmt {

		Var(Token name, Expr initializer) {
			this.name = name;
			this.initializer = initializer;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitVarStmt(this);
		}

		final Token name;
		final Expr initializer;
	}

	static class Val extends Stmt {

		Val(Token name, Expr initializer) {
			this.name = name;
			this.initializer = initializer;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitValStmt(this);
		}

		final Token name;
		final Expr initializer;
	}

	static class While extends Stmt {

		While(Expr condition, Stmt body) {
			this.condition = condition;
			this.body = body;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitWhileStmt(this);
		}

		final Expr condition;
		final Stmt body;
	}

	static class For extends Stmt {

		For(Stmt init, Expr condition, Expr increment, Stmt body) {
			this.init = init;
			this.condition = condition;
			this.increment = increment;
			this.body = body;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitForStmt(this);
		}

		final Stmt init;
		final Expr condition;
		final Expr increment;
		final Stmt body;
	}

	static class ForEach extends Stmt {

		ForEach(Stmt init, Expr iterable, Stmt body) {
			this.init = init;
			this.iterable = iterable;
			this.body = body;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitForEachStmt(this);
		}

		final Stmt init;
		final Expr iterable;
		final Stmt body;
	}

	static class Break extends Stmt {

		Break(Token name) {
			this.name = name;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitBreakStmt(this);
		}

		final Token name;
	}

	static class Continue extends Stmt {

		Continue(Token name) {
			this.name = name;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitContinueStmt(this);
		}

		final Token name;
	}

	static class Switch extends Stmt {

		Switch(Expr expression, List<Object> values, List<List<Stmt>> actions) {
			this.expression = expression;
			this.values = values;
			this.actions = actions;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitSwitchStmt(this);
		}

		final Expr expression;
		final List<Object> values;
		final List<List<Stmt>> actions;
	}

	static class Import extends Stmt {

		Import(Token keyword, Expr path) {
			this.keyword = keyword;
			this.path = path;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitImportStmt(this);
		}

		final Token keyword;
		final Expr path;
	}

	abstract <R> R accept(Visitor<R> visitor);
}
