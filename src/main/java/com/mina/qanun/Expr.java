package com.mina.qanun;

import java.util.List;

import java.util.Map;

abstract class Expr {

	interface Visitor<R> {

		R visitAssignExpr(Assign expr);

		R visitBinaryExpr(Binary expr);

		R visitCallExpr(Call expr);

		R visitGetExpr(Get expr);

		R visitSetExpr(Set expr);

		R visitThisExpr(This expr);

		R visitSuperExpr(Super expr);

		R visitGroupingExpr(Grouping expr);

		R visitListAccessorExpr(ListAccessor expr);

		R visitListMutatorExpr(ListMutator expr);

		R visitLiteralExpr(Literal expr);

		R visitQanunListExpr(QanunList expr);

		R visitLogicalExpr(Logical expr);

		R visitAnonymousFunExpr(AnonymousFun expr);

		R visitUnaryExpr(Unary expr);

		R visitVariableExpr(Variable expr);

		R visitConditionalTernaryExpr(ConditionalTernary expr);
	}

	static class Assign extends Expr {

		Assign(Token name, Expr value, Token equalSign) {
			this.name = name;
			this.value = value;
			this.equalSign = equalSign;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitAssignExpr(this);
		}

		final Token name;
		final Expr value;
		final Token equalSign;
	}

	static class Binary extends Expr {

		Binary(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitBinaryExpr(this);
		}

		final Expr left;
		final Token operator;
		final Expr right;
	}

	static class Call extends Expr {

		Call(Expr callee, Token paren, List<Expr> arguments) {
			this.callee = callee;
			this.paren = paren;
			this.arguments = arguments;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitCallExpr(this);
		}

		final Expr callee;
		final Token paren;
		final List<Expr> arguments;
	}

	static class Get extends Expr {

		Get(Expr object, Token name) {
			this.object = object;
			this.name = name;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitGetExpr(this);
		}

		final Expr object;
		final Token name;
	}

	static class Set extends Expr {

		Set(Expr object, Token name, Expr value) {
			this.object = object;
			this.name = name;
			this.value = value;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitSetExpr(this);
		}

		final Expr object;
		final Token name;
		final Expr value;
	}

	static class This extends Expr {

		This(Token keyword) {
			this.keyword = keyword;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitThisExpr(this);
		}

		final Token keyword;
	}

	static class Super extends Expr {

		Super(Token keyword, Token method) {
			this.keyword = keyword;
			this.method = method;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitSuperExpr(this);
		}

		final Token keyword;
		final Token method;
	}

	static class Grouping extends Expr {

		Grouping(Expr expression) {
			this.expression = expression;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitGroupingExpr(this);
		}

		final Expr expression;
	}

	static class ListAccessor extends Expr {

		ListAccessor(Expr object, Token name, Expr index) {
			this.object = object;
			this.name = name;
			this.index = index;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitListAccessorExpr(this);
		}

		final Expr object;
		final Token name;
		final Expr index;
	}

	static class ListMutator extends Expr {

		ListMutator(Expr object, Token name, Expr value) {
			this.object = object;
			this.name = name;
			this.value = value;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitListMutatorExpr(this);
		}

		final Expr object;
		final Token name;
		final Expr value;
	}

	static class Literal extends Expr {

		Literal(Object value) {
			this.value = value;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitLiteralExpr(this);
		}

		final Object value;
	}

	static class QanunList extends Expr {

		QanunList(List<Expr> list) {
			this.list = list;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitQanunListExpr(this);
		}

		final List<Expr> list;
	}

	static class Logical extends Expr {

		Logical(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitLogicalExpr(this);
		}

		final Expr left;
		final Token operator;
		final Expr right;
	}

	static class AnonymousFun extends Expr {

		AnonymousFun(List<Token> params, List<Stmt> body) {
			this.params = params;
			this.body = body;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitAnonymousFunExpr(this);
		}

		final List<Token> params;
		final List<Stmt> body;
	}

	static class Unary extends Expr {

		Unary(Token operator, Expr right, boolean isPostFix) {
			this.operator = operator;
			this.right = right;
			this.isPostFix = isPostFix;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitUnaryExpr(this);
		}

		final Token operator;
		final Expr right;
		final boolean isPostFix;
	}

	static class Variable extends Expr {

		Variable(Token name) {
			this.name = name;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitVariableExpr(this);
		}

		final Token name;
	}

	static class ConditionalTernary extends Expr {

		ConditionalTernary(Expr condition, Expr trueCondition, Expr falseCondition) {
			this.condition = condition;
			this.trueCondition = trueCondition;
			this.falseCondition = falseCondition;
		}

		@Override
		<R> R accept(Visitor<R> visitor) {
			return visitor.visitConditionalTernaryExpr(this);
		}

		final Expr condition;
		final Expr trueCondition;
		final Expr falseCondition;
	}

	abstract <R> R accept(Visitor<R> visitor);
}
