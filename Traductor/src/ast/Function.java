/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import java.util.*;
import visitor.*;

import org.antlr.v4.runtime.*;

//	function:sentence -> name:String  expressions:expression*

public class Function extends AbstractSentence {

	public Function(String name, List<Expression> expressions) {
		this.name = name;
		this.expressions = expressions;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expressions);
	}

	@SuppressWarnings("unchecked")
	public Function(Object name, Object expressions) {
		this.name = (name instanceof Token) ? ((Token)name).getText() : (String) name;
		this.expressions = (List<Expression>) expressions;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(name, expressions);
	}
	
	public void setDefinition(FuncDefinition definition) {
		this.definition = definition;
	}
	
	public FuncDefinition getDefinition() {
		return this.definition;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}
	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String name;
	private List<Expression> expressions;
	private FuncDefinition definition;

	public String toString() {
       return "{name:" + getName() + ", expressions:" + getExpressions() + "}";
   }
}
