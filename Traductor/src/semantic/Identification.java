package semantic;

import java.util.*;

import ast.*;
import main.*;
import visitor.*;

public class Identification extends DefaultVisitor {

    public Identification(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    /*
     * Poner aquí los visit necesarios.
     * Si se ha usado VGen, solo hay que copiarlos de la clase 'visitor/_PlantillaParaVisitors.txt'.
     */

    // public Object visit(Programa prog, Object param) {
    //      ...
    // }

    // ...
    // ...
    // ...
//	class Program { List<Definition> definitions;  Main main; }
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
		if (node.getType() != null)
			node.getType().accept(this, param);
		VarDefinition variable = variables.getFromTop(node.getName());
		predicado(variable == null, "Variable ya definida "+node.getName(),node);
		variables.put(node.getName(), node);
		return null;
	}
	
	//	class Campo { String name;  Type type; }
	public Object visit(Campo node, Object param) {

		// super.visit(node, param);

		if (node.getType() != null)
			node.getType().accept(this, param); 
		
		Campo campo = campos.getFromTop(node.getName());
		predicado(campo == null, "Campo ya definido "+node.getName(),node);
		campos.put(node.getName(), node);
		return null;
	}


	//	class StructDefinition { StructType structType;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {
	//	super.visit(node, param);

//		if (node.getStructtype() != null)
//			node.getStructtype().accept(this, param);
		
		campos.set();
		if (node.getDefinition() != null)
			for (Definition child : node.getDefinition())
				child.accept(this, param);
		
		StructDefinition definition = estructuras.get(node.getStructtype().getName());
		predicado(definition == null, "Estructura ya definida " + node.getStructtype().getName(),node);
		estructuras.put(node.getStructtype().getName(), node);
		
		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Expression> expression; }
	public Object visit(FuncDefinition node, Object param) {

		// super.visit(node, param);
		variables.set();

		if (node.getParams() != null)
			for (Definition child : node.getParams())
				child.accept(this, param);

		if (node.getDefs() != null)
			for (Definition child : node.getDefs())
				child.accept(this, param);

		if (node.getSentence() != null){
			for (Sentence child : node.getSentence()){
				child.accept(this, param);
				if(child instanceof Return)
					((Return)child).setFuncDefinition(node);
			}
		}
		FuncDefinition definition = funciones.get(node.getName());
		predicado(definition == null,"Funcion ya definida: "+node.getName(),node);
		funciones.put(node.getName(), node);
		variables.reset();
		
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
		StructDefinition definition = estructuras.get(node.getName());
		predicado(definition != null,"Estructura no definida " + node.getName(),node);
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
		
		FuncDefinition definition = funciones.get(node.getName());
		predicado(definition != null, "Funcion no definida "+node.getName(),node);
		node.setDefinition(definition);
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

	//	class Variable { String name;  }
	public Object visit(Variable node, Object param) {

		super.visit(node, param);
		VarDefinition definition = variables.getFromAny(node.getName());
		predicado(definition != null, "Variable no definida: "+node.getName(),node);
		node.setDefinition(definition);
		return null;
	}
	
	//	class AccesoCampo { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		
		Campo definition = campos.getFromAny(node.getName());
		predicado(definition != null, "Campo no definido: "+node.getName(),node);
		node.setDefinition(definition);
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
		
		FuncDefinition definition = funciones.get(node.getName());
		predicado(definition != null, "Funcion no definida "+node.getName(),node);
		node.setDefinition(definition);
		return null;
	}

	//	class AccesoArray { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {

		// super.visit(node, param);

		if (node.getName() != null)
			node.getName().accept(this, param);

		if (node.getIndex() != null)
			node.getIndex().accept(this, param);
		
//		VarDefinition definition = variables.getFromTop(node.toString());
//		predicado(definition != null, "Variable no definida "+node.getName(),node);
//		node.setDefinition(definition);
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

    /**
     * predicado. Método auxiliar para implementar los predicados. Borrar si no se quiere usar.
     *
     * Ejemplos de uso (suponiendo que existe un método "esPrimitivo"):
     *
     *      1. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo pimitivo", expr.getStart());
     *      2. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo pimitivo", expr);
     *      3. predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo");
     *
     * NOTA: El método getStart() (ejemplo 1) indica la linea/columna del fichero fuente donde estaba el nodo
     * (y así poder dar información más detallada de la posición del error). Si se usa VGen, dicho método
     * habrá sido generado en todos los nodos del AST.
     * No es obligatorio llamar a getStart() (ejemplo 2), ya que si se pasa el nodo, se usará por defecto dicha
     * posición.
     * Si no se quiere imprimir la posición del fichero, se puede omitir el tercer argumento (ejemplo 3).
     *
     * @param condicion     Debe cumplirse para que no se produzca un error
     * @param mensajeError  Se imprime si no se cumple la condición
     * @param posicionError Fila y columna del fichero donde se ha producido el error.
     */
    private void predicado(boolean condicion, String mensajeError, Position posicionError) {
        if (!condicion)
            errorManager.notify("Comprobaci�n de tipos", mensajeError, posicionError);
    }

    private void predicado(boolean condicion, String mensajeError, AST node) {
        predicado(condicion, mensajeError, node.getStart());
    }

    private void predicado(boolean condicion, String mensajeError) {
        predicado(condicion, mensajeError, (Position) null);
    }


    private ErrorManager errorManager;
    private ContextMap<String,VarDefinition> variables = new ContextMap<String,VarDefinition>();
    private HashMap<String,FuncDefinition> funciones = new HashMap<String,FuncDefinition>();
    private HashMap<String,StructDefinition> estructuras = new HashMap<String,StructDefinition>();
    private ContextMap<String,Campo> campos = new ContextMap<String,Campo>();
}
