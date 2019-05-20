package visitor;

import ast.*;

public class PrintVisitor extends DefaultVisitor {
//	class Program { List<Definition> definitions; }
	public Object visit(Program node, Object param) {

		// super.visit(node, param);
		
		if (node.getDefinitions() != null)
			for (Definition child : node.getDefinitions())
				child.accept(this, param);

		return null;
	}

	//	class VarDefinition { String name;  Type type; }
	public Object visit(VarDefinition node, Object param) {

		// super.visit(node, param);
		System.out.println("var: ");
		if (node.getType() != null)
			node.getType().accept(this, param);
		System.out.println(";");
		return null;
	}

	//	class StructDefinition { Type structType;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {

		return super.visit(node, param);

//		if (node.getStructType() != null)
//			node.getStructType().accept(this, param);
//
//		if (node.getDefinition() != null)
//			for (Definition child : node.getDefinition())
//				child.accept(this, param);
//
//		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Expression> expression; }
	public Object visit(FuncDefinition node, Object param) {

		// super.visit(node, param);
		System.out.println(node.getName());
		if (node.getParams() != null)
			for (Definition child : node.getParams())
				child.accept(this, param);

		if (node.getDefs() != null)
			for (Definition child : node.getDefs())
				child.accept(this, param);

		if (node.getSentence() != null)
			for (Sentence child : node.getSentence())
				child.accept(this, param);

		return null;
	}

	//	class ArrayType { Expression expression;  Type type; }
	public Object visit(ArrayType node, Object param) {

		return super.visit(node, param);

//		if (node.getExpression() != null)
//			node.getExpression().accept(this, param);
//
//		if (node.getType() != null)
//			node.getType().accept(this, param);
//
//		return null;
	}

	//	class IntType {  }
	public Object visit(IntType node, Object param) {
		return null;
	}

	//	class RealType {  }
	public Object visit(RealType node, Object param) {
		return null;
	}

	//	class CharType {  }
	public Object visit(CharType node, Object param) {
		return null;
	}

	//	class StructType {  }
	public Object visit(StructType node, Object param) {
		return null;
	}

	//	class Print { Expression expression;  String value; }
	public Object visit(Print node, Object param) {

		// super.visit(node, param);
		System.out.println("print ");
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);

		return null;
	}

	//	class If { Expression condition;  List<Sentence> ifSentences;  List<Sentence> elseSentences; }
	public Object visit(If node, Object param) {

		// super.visit(node, param);
		System.out.println("if ");
		if (node.getCondition() != null)
			node.getCondition().accept(this, param);

		if (node.getIfSentences() != null)
			for (Sentence child : node.getIfSentences())
				child.accept(this, param);

		if (node.getElseSentences() != null)
			for (Sentence child : node.getElseSentences())
				child.accept(this, param);

		return null;
	}

	//	class Read { Expression expression; }
	public Object visit(Read node, Object param) {

		// super.visit(node, param);
		System.out.println("read ");
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);

		return null;
	}

	//	class While { Expression condition;  List<Sentence> sentences; }
	public Object visit(While node, Object param) {

		// super.visit(node, param);
		System.out.println("while ");
		if (node.getCondition() != null)
			node.getCondition().accept(this, param);

		if (node.getSentences() != null)
			for (Sentence child : node.getSentences())
				child.accept(this, param);

		return null;
	}

	//	class Assignment { Expression left;  Expression right; }
	public Object visit(Assignment node, Object param) {

		// super.visit(node, param);
		
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		System.out.println(" = ");
		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class Function { String name;  List<Expression> expressions; }
	public Object visit(Function node, Object param) {

		// super.visit(node, param);
		System.out.println(node.getName());
		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);

		return null;
	}

	//	class Return { Expression expression; }
	public Object visit(Return node, Object param) {

		return super.visit(node, param);

//		if (node.getExpression() != null)
//			node.getExpression().accept(this, param);
//
//		return null;
	}

	//	class ArithmeticExpression { Expression left;  String op;  Expression right; }
	public Object visit(ArithmeticExpression node, Object param) {

		// super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		System.out.println(" "+node.getOp()+" ");
		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class BooleanExpression { Expression left;  String op;  Expression right; }
	public Object visit(BooleanExpression node, Object param) {

		// super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		System.out.println(" "+node.getOp()+" ");
		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class ChildVariable { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {

		// super.visit(node, param);
		
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		System.out.println(node.getName());
		return null;
	}

	//	class Variable { String name; }
	public Object visit(Variable node, Object param) {
		System.out.println(node.getName());
		return null;
	}

	//	class Cast { Type targetType;  Expression value; }
	public Object visit(Cast node, Object param) {

		return super.visit(node, param);

//		if (node.getTargetType() != null)
//			node.getTargetType().accept(this, param);
//
//		if (node.getValue() != null)
//			node.getValue().accept(this, param);
//
//		return null;
	}

	//	class FunctionExpression { String name;  List<Expression> expressions; }
	public Object visit(FunctionExpression node, Object param) {

		// super.visit(node, param);
		System.out.println(node.getName());
		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);

		return null;
	}

	//	class ArrayExpression { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {

		// super.visit(node, param);
		System.out.println("array ");
		if (node.getName() != null)
			node.getName().accept(this, param);

		if (node.getIndex() != null)
			node.getIndex().accept(this, param);

		return null;
	}

	//	class IntConstant { String value; }
	public Object visit(IntConstant node, Object param) {
		System.out.println(node.getValue());
		return null;
	}

	//	class RealConstant { String value; }
	public Object visit(RealConstant node, Object param) {
		System.out.println(node.getValue());
		return null;
	}

	//	class CharConstant { String value; }
	public Object visit(CharConstant node, Object param) {
		System.out.println(node.getValue());
		return null;
	}
}
