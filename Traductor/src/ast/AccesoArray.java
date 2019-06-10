/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	accesoArray:expression -> name:expression  index:expression

public class AccesoArray extends AbstractExpression {

	public AccesoArray(Expression name, Expression index) {
		this.name = name;
		this.index = index;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(name, index);
	}

	public AccesoArray(Object name, Object index) {
		this.name = (Expression) ((name instanceof ParserRuleContext) ? getAST(name) : name);
		this.index = (Expression) ((index instanceof ParserRuleContext) ? getAST(index) : index);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(name, index);
	}

	public Expression getName() {
		return name;
	}
	public void setName(Expression name) {
		this.name = name;
	}

	public Expression getIndex() {
		return index;
	}
	
	public void setIndex(Expression index) {
		this.index = index;
	}
	
	public VarDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(VarDefinition definition) {
		this.definition = definition;
	}
	
	public Definition searchDefinition() {
		if(name instanceof AccesoArray)
		return ((AccesoArray)name).searchDefinition();
	else if(name instanceof Variable)
		return ((Variable)name).getDefinition();
	else if(name instanceof AccesoCampo)
		return ((AccesoCampo)name).getDefinition();
		
		return null;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}
	
	private Expression name;
	private Expression index;
	
	private VarDefinition definition;
	
	public String toString() {
       return name.toString();
   }
}
