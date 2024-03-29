package codegeneration;

import java.io.*;
import java.util.*;

import ast.*;
import visitor.*;

enum CodeFunction {
    ADDRESS, VALUE
}

public class CodeSelection extends DefaultVisitor {

    public CodeSelection(Writer writer, String sourceFile) {
        this.writer = new PrintWriter(writer);
        this.sourceFile = sourceFile;
        
        instrucciones.put("+", "add");
        instrucciones.put("-", "sub");
        instrucciones.put("/", "div");
        instrucciones.put("*", "mul");
        
        instrucciones.put("&&", "and");
        instrucciones.put("||", "or");
        
        instrucciones.put("==", "eq");
        instrucciones.put("!=", "neq");
        instrucciones.put("<", "lt");
        instrucciones.put(">", "gt");
        instrucciones.put(">=","ge");
        instrucciones.put("<=", "le");
    }

    /*
     * Poner aquí los visit necesarios. Si se ha usado VGen, solo hay que copiarlos
     * de la clase 'visitor/_PlantillaParaVisitors.txt'.
     */

    // Ejemplo:
    //
    // public Object visit(Programa node, Object param) {
    //      out("#source \"" + sourceFile + "\"");
    //      out("call main");
    //      out("halt");
    //
    //      super.visit(node, param); // Recorrer los hijos
    //      return null;
    // }
//	class Program { List<Definition> definitions; }
	public Object visit(Program node, Object param) {
		out("#source \"" + sourceFile + "\"");
		out("call main");
		out("halt");
		visitChildren(node.getDefinitions(), param);
		return null;
	}

	//	class VarDefinition { String name;  Type type; }
	public Object visit(VarDefinition node, Object param) {
		//if(node.isLocal())
			//out("#LOCAL " + node.getName() + ":" + node.getType().getMAPLName());
		//else
			//out("#GLOBAL " + node.getName() + ":" + node.getType().getMAPLName());
		
        return null;
	}

	//	class StructDefinition { StructType structtype;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {
		
		//out("#TYPE " + node.getStructtype().getName() + ":" + node.toString());
		visitChildren(node.getDefinition(),param);
		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Sentence> sentence;  Type type; }
	public Object visit(FuncDefinition node, Object param) {
		out("#FUNC " + node.getName());
		out(node.getName()+":");
		out("enter "+node.getDefsSize());
		for(Definition child : node.getParams()){
			child.accept(this, CodeFunction.ADDRESS);
		}
		for(Definition child : node.getDefs()){
			child.accept(this, CodeFunction.ADDRESS);
		}
		visitChildren(node.getSentence(),param);
		if(node.getType() == null)
			out(String.format("ret 0,%1$s,%2$s",node.getDefsSize(),node.getParamSize()));
		return null;
	}

	//	class Campo { String name;  Type type; }
	public Object visit(Campo node, Object param) {
		//out("#GLOBAL " + node.getName() + ":" + node.getType().getMAPLName());
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
		return null;
	}

	//	class Print { Expression expression;  String value; }
	public Object visit(Print node, Object param) {
		out("#line " + node.getEnd().getLine());
        node.getExpression().accept(this, CodeFunction.VALUE);
        out("out", node.getExpression().getType());
		return null;
	}

	//	class If { Expression condition;  List<Sentence> ifSentences;  List<Sentence> elseSentences; }
	public Object visit(If node, Object param) {
		// super.visit(node, param);
		out("#line " + node.getEnd().getLine());
		node.getCondition().accept(this, param);
		out("jz else");
		if(node.getIfSentences()!=null)
			for(Sentence child:node.getIfSentences())
				child.accept(this, param);
		out("jmp finif");
		out("else:");
		if(node.getElseSentences()!=null)
			for(Sentence child:node.getElseSentences())
				child.accept(this, param);
		out("finif:");	

		return null;
	}

	//	class Read { Expression expression; }
	public Object visit(Read node, Object param) {
		out("#line " + node.getEnd().getLine());
		node.getExpression().accept(this,CodeFunction.ADDRESS);
		out("in");
		out("store",node.getExpression().getType());
		return null;
	}

	//	class While { Expression condition;  List<Sentence> sentences; }
	public Object visit(While node, Object param) {
		out("#line " + node.getEnd().getLine());
		int nBucle = this.nBucle;
		++this.nBucle;
		out("inicio_bucle"+nBucle+":");
		node.getCondition().accept(this,param);
		out("jz final"+nBucle);
		if(node.getSentences()!=null)
			for(Sentence child:node.getSentences())
				child.accept(this, param);
		out("jmp inicio_bucle"+nBucle);
		out("final"+nBucle+":");
		
		return null;
	}

	//	class Assignment { Expression left;  Expression right; }
	public Object visit(Assignment node, Object param) {
		out("#line " + node.getEnd().getLine());
		node.getLeft().accept(this, CodeFunction.ADDRESS);
		node.getRight().accept(this, CodeFunction.VALUE);
		out("store", node.getLeft().getType());
		return null;
	}

	//	class Function { String name;  List<Expression> expressions; }
	public Object visit(Function node, Object param) {

		// super.visit(node, param);
		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);
		out("call "+node.getName());
		if(node.getDefinition().getType() != null)
			out("pop",node.getDefinition().getType());

		return null;
	}

	//	class Return { Expression expression; }
	public Object visit(Return node, Object param) {
		// super.visit(node, param);
		if (node.getExpression() != null)
			node.getExpression().accept(this, CodeFunction.VALUE);
		FuncDefinition funcDef = node.getFuncDefinition();
		out(String.format("ret %1$s,%2$s,%3$s",funcDef.getType().getSize(),funcDef.getDefsSize(),funcDef.getParamSize())); 
		return null;
	}

	//	class ArithmeticExpression { Expression left;  String op;  Expression right; }
	public Object visit(ArithmeticExpression node, Object param) {
		assert(param == CodeFunction.VALUE);
		node.getLeft().accept(this, CodeFunction.VALUE);
		node.getRight().accept(this,CodeFunction.VALUE);
		out(instrucciones.get(node.getOp()), node.getType());
		return null;
	}

	//	class BooleanExpression { Expression left;  String op;  Expression right; }
	public Object visit(BooleanExpression node, Object param) {
		assert(param == CodeFunction.VALUE);
		node.getLeft().accept(this, CodeFunction.VALUE);
		node.getRight().accept(this,CodeFunction.VALUE);
		out(instrucciones.get(node.getOp()),node.getType());
		return null;
	}

	//	class AccesoCampo { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {
		if(((CodeFunction)param) == CodeFunction.VALUE) {
			visit(node, CodeFunction.ADDRESS);
	        out("load", node.getType());
		}else {
			assert(param == CodeFunction.ADDRESS);
			Definition def = node.searchDefinition();
			if(def instanceof VarDefinition)
				out("pusha " +((VarDefinition)def).getAddress());
			else if(def instanceof Campo)
				out("pusha " +((Campo)def).getAddress());
			out("push "+node.getDefinition().getAddress());
			out("add");
		}
		return null;
	}

	//	class Variable { String name; }
	public Object visit(Variable node, Object param) {
		if(((CodeFunction)param) == CodeFunction.VALUE) {
			visit(node, CodeFunction.ADDRESS);
	        out("load", node.getType());
		}else{
			assert(param == CodeFunction.ADDRESS);
			if(node.getDefinition().isLocal()) {
				out("pusha bp");
				out("push "+node.getDefinition().getAddress());
				out("add");
			}else
				out("pusha " + node.getDefinition().getAddress());
		}
		return null;
	}

	//	class Cast { Type targetType;  Expression value; }
	public Object visit(Cast node, Object param) {
		node.getValue().accept(this, CodeFunction.VALUE);
		out(node.getValue().getType().getSuffix() + "2" + node.getTargetType().getSuffix());
		return null;
	}

	//	class FunctionExpression { String name;  List<Expression> expressions; }
	public Object visit(FunctionExpression node, Object param) {

		// super.visit(node, param);
		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);
		out("call "+node.getName());
		return null;
	}

	//	class AccesoArray { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {
		if (((CodeFunction) param) == CodeFunction.VALUE) {
	        visit(node, CodeFunction.ADDRESS);
	        out("load", node.getType());
	     }else if(((CodeFunction) param) == CodeFunction.ADDRESS){ // Funcion.DIRECCION
	    	assert (param == CodeFunction.ADDRESS);
	     	node.getName().accept(this, CodeFunction.ADDRESS);
	     	node.getIndex().accept(this, CodeFunction.VALUE);
	     	out("push " + node.getType().getSize());
	     	out("mul");
	     	out("add");
	     	//node.getName().accept(this, CodeFunction.ADDRESS);
	     }
		 return null;
	}

	//	class IntConstant { String value; }
	public Object visit(IntConstant node, Object param) {
		assert(param == CodeFunction.VALUE);
		out("push "+node.getValue());
		return null;
	}

	//	class RealConstant { String value; }
	public Object visit(RealConstant node, Object param) {
		assert(param == CodeFunction.VALUE);
		out("pushf "+node.getValue());
		return null;
	}

	//	class CharConstant { String value; }
	public Object visit(CharConstant node, Object param) {
		assert(param == CodeFunction.VALUE);
		out("pushb "+node.getValue().codePointAt(0));
		return null;
	}

    // Método auxiliar recomendado (opcional) -------------
    private void out(String instruction) {
        writer.println(instruction);
    }
    
    private void out(String instruccion, Type tipo) {
        out(instruccion + tipo.getSuffix());
    }

    private PrintWriter writer;
    private String sourceFile;
	private int nBucle;
	
    private Map<String,String> instrucciones = new HashMap<String,String>();
}
