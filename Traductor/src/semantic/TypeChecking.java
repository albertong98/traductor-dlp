package semantic;

import java.util.*;

import ast.*;
import main.*;
import visitor.*;

public class TypeChecking extends DefaultVisitor {

    public TypeChecking(ErrorManager errorManager) {
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

		if (node.getType() != null)
			node.getType().accept(this, param);
		

		return null;
	}

	//	class StructDefinition { StructType structtype;  List<Definition> definition; }
	public Object visit(StructDefinition node, Object param) {

		// super.visit(node, param);

		if (node.getStructtype() != null)
			node.getStructtype().accept(this, param);

		if (node.getDefinition() != null)
			for (Definition child : node.getDefinition())
				child.accept(this, param);

		return null;
	}

	//	class FuncDefinition { String name;  List<Definition> params;  List<Definition> defs;  List<Sentence> sentence;  Type type; }
	public Object visit(FuncDefinition node, Object param) {

		// super.visit(node, param);

		if (node.getParams() != null)
			for (Definition child : node.getParams()) {
				predicado(tipoSimple(child.getType()),"Los parametros deben de ser de tipo simple",node);
				child.accept(this, param);
			}

		if (node.getDefs() != null)
			for (Definition child : node.getDefs())
				child.accept(this, param);

		if (node.getSentence() != null)
			for (Sentence child : node.getSentence())
				child.accept(this, param);

		if (node.getType() != null) {
			predicado(tipoSimple(node.getType()),"la funcion no retorna un tipo simple",node);
			node.getType().accept(this, param);
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
		return null;
	}

	//	class Print { Expression expression;  String value; }
	public Object visit(Print node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		
		predicado(tipoSimple(node.getExpression().getType()),"la expresi�n debe ser de tipo simple",node);

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
		
		predicado(node.getCondition().getType() instanceof IntType,"No es una condicion entera",node);
		
		return null;
	}

	//	class Read { Expression expression; }
	public Object visit(Read node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		
		predicado(tipoSimple(node.getExpression().getType()),"la expresi�n debe ser de tipo simple",node);
		predicado(node.getExpression().isModificable(),"la expresi�n debe ser modificable",node);

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
		
		predicado(node.getCondition().getType() instanceof IntType,"No es una condicion entera",node);

		return null;
	}

	//	class Assignment { Expression left;  Expression right; }
	public Object visit(Assignment node, Object param) {

		//super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);

		if (node.getRight() != null)
			node.getRight().accept(this, param);
		
		predicado(mismoTipo(node.getLeft(),node.getRight()), "Las expresiones deben ser del mismo tipo",node);
		predicado(node.getLeft().isModificable(), "La expresion no es modificable",node.getLeft());

		return null;
	}

	//	class Function { String name;  List<Expression> expressions; }
	public Object visit(Function node, Object param) {

		// super.visit(node, param);

		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);
		predicado(node.getExpressions().size() == node.getDefinition().getParams().size(),"No coincide el n�mero de parametros",node);
		predicado(comprobarTipos(node.getDefinition().getParams(),node.getExpressions()),"No coinciden los tipos de los parametros");
		return null;
	}

	//	class Return { Expression expression; }
	public Object visit(Return node, Object param) {

		// super.visit(node, param);

		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		
		predicado(node.getExpression().getType().getClass().equals(node.getFuncDefinition().getType().getClass()),"La expresion no es del tipo de retorno de la funcion");

		return null;
	}

	//	class ArithmeticExpression { Expression left;  String op;  Expression right; }
	public Object visit(ArithmeticExpression node, Object param) {

		//super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);

		if (node.getRight() != null)
			node.getRight().accept(this, param);
		
		predicado(mismoTipo(node.getLeft(),node.getRight()),"Los operandos deben de ser del mismo tipo",node);
		predicado(tipoSimple(node.getLeft().getType()),"Los operandos deben ser de tipo simple",node);
		node.setType(node.getLeft().getType());
		node.setModificable(false);
		
		return null;
	}

	//	class BooleanExpression { Expression left;  String op;  Expression right; }
	public Object visit(BooleanExpression node, Object param) {

//		super.visit(node, param);

		if (node.getLeft() != null)
			node.getLeft().accept(this, param);

		if (node.getRight() != null)
			node.getRight().accept(this, param);
		
		//predicado(mismoTipo(node.getLeft(),node.getRight()),"Los operandos deben de ser del mismo tipo",node);
		predicado(tipoSimple(node.getLeft().getType()),"Los operandos deben ser de tipo simple");
		node.setType(node.getLeft().getType());
		node.setModificable(false);
		return null;
	}

	//	class AccesoCampo { Expression expression;  String name; }
	public Object visit(AccesoCampo node, Object param) {

//		super.visit(node, param);
//
		if (node.getExpression() != null)
			node.getExpression().accept(this, param);
		Definition definition = node.searchDefinition();
		predicado(node.getExpression().getType() instanceof StructType,"Debe ser de tipo Struct",node);
		node.setModificable(true);
		if(definition !=null)
			node.setType(node.getDefinition().getType());
		
		return null;
	}

	//	class Variable { String name; }
	public Object visit(Variable node, Object param) {
		super.visit(node, param);
		node.setModificable(true);
		node.setType(node.getDefinition().getType());
		return null;
	}

	//	class Cast { Type targetType;  Expression value; }
	public Object visit(Cast node, Object param) {

		//super.visit(node, param);

		if (node.getTargetType() != null)
			node.getTargetType().accept(this, param);

		if (node.getValue() != null)
			node.getValue().accept(this, param);
		
		predicado(!(node.getTargetType().getClass().equals(node.getValue().getType().getClass())),"El tipo destino debe ser distinto al tipo del elemento",node);
		node.setModificable(false);
		node.setType(node.getTargetType());
		return null;
	}

	//	class FunctionExpression { String name;  List<Expression> expressions; }
	public Object visit(FunctionExpression node, Object param) {

		//super.visit(node, param);

		if (node.getExpressions() != null)
			for (Expression child : node.getExpressions())
				child.accept(this, param);
		
		predicado(node.getExpressions().size() == node.getDefinition().getParams().size(),"No coincide el n�mero de parametros",node);
		predicado(comprobarTipos(node.getDefinition().getParams(),node.getExpressions()),"No coinciden los tipos de los parametros");
		node.setType(node.getDefinition().getType());
		node.setModificable(false);

		return null;
	}

	//	class AccesoArray { Expression name;  Expression index; }
	public Object visit(AccesoArray node, Object param) {

		//super.visit(node, param);

		if (node.getName() != null)
			node.getName().accept(this, param);

		if (node.getIndex() != null)
			node.getIndex().accept(this, param);
		
		Definition definition = node.searchDefinition();
		predicado(node.getIndex().getType() instanceof IntType, "El indice debe ser entero",node);
		predicado(definition != null && definition.getType() instanceof ArrayType,"La variable debe ser de tipo array",node);
		
		Type type = null;
		if(node.getName().getType() instanceof ArrayType)
			type=((ArrayType)node.getName().getType()).getType();
		node.setType(type);
		node.setModificable(true);

		return null;
	}

	//	class IntConstant { String value; }
	public Object visit(IntConstant node, Object param) {
		node.setModificable(false);
		node.setType(new IntType());
		return null;
	}

	//	class RealConstant { String value; }
	public Object visit(RealConstant node, Object param) {
		node.setModificable(false);
		node.setType(new RealType());
		return null;
	}

	//	class CharConstant { String value; }
	public Object visit(CharConstant node, Object param) {
		node.setModificable(false);
		node.setType(new CharType());
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


    private boolean mismoTipo(Expression a, Expression b) {
        if(a.getType()!=null && b.getType()!=null)
        	return (a.getType().getClass() == b.getType().getClass());
        return false;
    }
    
    private boolean tipoSimple(Type tipo) {
    	return (tipo instanceof IntType || tipo instanceof RealType || tipo instanceof CharType);
    }
    
    private boolean comprobarTipos(List<Definition> params,List<Expression> args) {
    	int menor = Math.min(params.size(), args.size());
    	for(int i = 0;i<menor;i++)
    		if(!(params.get(i).getType().getClass().equals(args.get(i).getType().getClass())))
    				return false;
    	return true;
    }

    private ErrorManager errorManager;
}
