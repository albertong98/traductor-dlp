/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Program { List<Definition> definitions; }
	public Object visit(Program node, Object param) {
		visitChildren(node.getDefinitions(), param);
		return null;
	}

	//	class VarDefinition { String name;  Type type; }
	public Object visit(VarDefinition node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class StructDefinition { StructType structtype;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {
		if (node.getStructtype() != null)
			node.getStructtype().accept(this, param);
		visitChildren(node.getDefinition(), param);
		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Sentence> sentence;  Type type; }
	public Object visit(FuncDefinition node, Object param) {
		visitChildren(node.getParams(), param);
		visitChildren(node.getDefs(), param);
		visitChildren(node.getSentence(), param);
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class Campo { String name;  Type type; }
	public Object visit(Campo node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class ArrayType { Expression expression;  Type type; }
	public Object visit(ArrayType node, Object param) {
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
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
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		return null;
	}

	//	class If { Expression condition;  List<Sentence> ifSentences;  List<Sentence> elseSentences; }
	public Object visit(If node, Object param) {
		if (node.getCondition() != null)
			node.getCondition().accept(this, param);
		visitChildren(node.getIfSentences(), param);
		visitChildren(node.getElseSentences(), param);
		return null;
	}

	//	class Read { Expression expression; }
	public Object visit(Read node, Object param) {
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		return null;
	}

	//	class While { Expression condition;  List<Sentence> sentences; }
	public Object visit(While node, Object param) {
		if (node.getCondition() != null)
			node.getCondition().accept(this, param);
		visitChildren(node.getSentences(), param);
		return null;
	}

	//	class Assignment { Expression left;  Expression right; }
	public Object visit(Assignment node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class Function { String name;  List<Expression> expressions; }
	public Object visit(Function node, Object param) {
		visitChildren(node.getExpressions(), param);
		return null;
	}

	//	class Return { Expression expression; }
	public Object visit(Return node, Object param) {
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		return null;
	}

	//	class ArithmeticExpression { Expression left;  String op;  Expression right; }
	public Object visit(ArithmeticExpression node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class BooleanExpression { Expression left;  String op;  Expression right; }
	public Object visit(BooleanExpression node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class AccesoCampo { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		return null;
	}

	//	class Variable { String name; }
	public Object visit(Variable node, Object param) {
		return null;
	}

	//	class Cast { Type targetType;  Expression value; }
	public Object visit(Cast node, Object param) {
		if (node.getTargetType() != null)
			node.getTargetType().accept(this, param);
		if (node.getValue() != null)
			node.getValue().accept(this, param);
		return null;
	}

	//	class FunctionExpression { String name;  List<Expression> expressions; }
	public Object visit(FunctionExpression node, Object param) {
		visitChildren(node.getExpressions(), param);
		return null;
	}

	//	class AccesoArray { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {
		if (node.getName() != null)
			node.getName().accept(this, param);
		if (node.getIndex() != null)
			node.getIndex().accept(this, param);
		return null;
	}

	//	class IntConstant { String value; }
	public Object visit(IntConstant node, Object param) {
		return null;
	}

	//	class RealConstant { String value; }
	public Object visit(RealConstant node, Object param) {
		return null;
	}

	//	class CharConstant { String value; }
	public Object visit(CharConstant node, Object param) {
		return null;
	}

    // Método auxiliar -----------------------------
    protected void visitChildren(List<? extends AST> children, Object param) {
        if (children != null)
            for (AST child : children)
                child.accept(this, param);
    }
}
