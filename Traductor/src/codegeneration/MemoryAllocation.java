package codegeneration;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import ast.*;
import visitor.*;

/**
 * Clase encargada de asignar direcciones a las variables.
 */
public class MemoryAllocation extends DefaultVisitor {

    /*
     * Poner aquí los visit necesarios. Si se ha usado VGen, solo hay que copiarlos
     * de la clase 'visitor/_PlantillaParaVisitors.txt'.
     */

    // public Object visit(Programa prog, Object param) {
    //      ...
    // }
//	class Program { List<Definition> definitions; }
	public Object visit(Program node, Object param) {

		//super.visit(node, param);
		int currentAddress = 0;
		
		if (node.getDefinitions() != null)
			for (Definition child : node.getDefinitions())
				child.accept(this, param);
		
		if (node.getDefinitions() != null)
			for (Definition child : node.getDefinitions())
				if(child instanceof VarDefinition) {
					((VarDefinition)child).setAddress(currentAddress);
					currentAddress += child.getType().getSize();
				}

		return null;
	}

	//	class VarDefinition { String name;  Type type; }
	public Object visit(VarDefinition node, Object param) {

		//super.visit(node, param);

		if (node.getType() != null)
			node.getType().accept(this, param);
		
		

		return null;
	}

	//	class StructDefinition { StructType structtype;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {

		// super.visit(node, param);
		int relativeAddress = 0;
		if (node.getStructtype() != null)
			node.getStructtype().accept(this, param);

		if (node.getDefinition() != null)
			for (Definition child : node.getDefinition())
				child.accept(this, param);
		
		if (node.getDefinition() != null) {
			for(Definition child:node.getDefinition()) {
				((Campo)child).setAddress(relativeAddress);
				node.getStructtype().addSize(child.getType().getSize());
				relativeAddress += child.getType().getSize();
			}
		}
		System.out.println(node.getType().getSize());
		estructuras.put(node.getStructtype().getName(), node.getStructtype());

		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Sentence> sentence;  Type type; }
	public Object visit(FuncDefinition node, Object param) {

		// super.visit(node, param);
		int currentAddress=0;
		
		if (node.getParams() != null)
			for (Definition child : node.getParams())
				child.accept(this, param);

		if (node.getDefs() != null)
			for (Definition child : node.getDefs())
				child.accept(this, param);

		if (node.getSentence() != null)
			for (Sentence child : node.getSentence())
				child.accept(this, param);

		if (node.getType() != null)
			node.getType().accept(this, param);
		
		if (node.getParams() != null && !node.getParams().isEmpty()){
			currentAddress = 4;
			for(int i=node.getParams().size()-1;i>=0;i--){
				VarDefinition child = (VarDefinition)node.getParams().get(i);
				child.setLocal(true);
				child.setAddress(currentAddress);
				currentAddress += child.getType().getSize();
			}
		}

		if(node.getDefs()!=null && !node.getDefs().isEmpty()){
			currentAddress = 0;
			for(Definition child : node.getDefs()){
				currentAddress -= child.getType().getSize();
				((VarDefinition)child).setLocal(true);
				((VarDefinition)child).setAddress(currentAddress);
			}
		}

		return null;
	}

	//	class Campo { String name;  Type type; }
	public Object visit(Campo node, Object param) {

		// super.visit(node, param);

		if (node.getType() != null)
			node.getType().accept(this, param);

		return null;
	}

	//	class ArrayType { Expression expression;  Type type; }
	public Object visit(ArrayType node, Object param) {

		// super.visit(node, param);

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
		StructType def = estructuras.get(node.getName());
		
		if(def!=null)
			node.addSize(def.getSize());
		
		return null;
	}

	//	class Print { Expression expression;  String value; }
	public Object visit(Print node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);

		return null;
	}

	//	class If { Expression condition;  List<Sentence> ifSentences;  List<Sentence> elseSentences; }
	public Object visit(If node, Object param) {

		// super.visit(node, param);

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

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);

		return null;
	}

	//	class While { Expression condition;  List<Sentence> sentences; }
	public Object visit(While node, Object param) {

		// super.visit(node, param);

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

		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class Function { String name;  List<Expression> expressions; }
	public Object visit(Function node, Object param) {

		// super.visit(node, param);

		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);

		return null;
	}

	//	class Return { Expression expression; }
	public Object visit(Return node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);

		return null;
	}

	//	class ArithmeticExpression { Expression left;  String op;  Expression right; }
	public Object visit(ArithmeticExpression node, Object param) {

		// super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);

		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class BooleanExpression { Expression left;  String op;  Expression right; }
	public Object visit(BooleanExpression node, Object param) {

		// super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);

		if (node.getRight() != null)
			node.getRight().accept(this, param);

		return null;
	}

	//	class AccesoCampo { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {

		// super.visit(node, param);

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

		// super.visit(node, param);

		if (node.getTargetType() != null)
			node.getTargetType().accept(this, param);

		if (node.getValue() != null)
			node.getValue().accept(this, param);

		return null;
	}

	//	class FunctionExpression { String name;  List<Expression> expressions; }
	public Object visit(FunctionExpression node, Object param) {

		// super.visit(node, param);

		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);

		return null;
	}

	//	class AccesoArray { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {

		// super.visit(node, param);

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
	
	private HashMap<String,StructType> estructuras = new HashMap<String,StructType>();
	

}
