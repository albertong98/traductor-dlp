/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	cast:expression -> targetType:type  value:expression

public class Cast extends AbstractExpression {

	public Cast(Type targetType, Expression value) {
		this.targetType = targetType;
		this.value = value;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(targetType, value);
	}

	public Cast(Object targetType, Object value) {
		this.targetType = (Type) ((targetType instanceof ParserRuleContext) ? getAST(targetType) : targetType);
		this.value = (Expression) ((value instanceof ParserRuleContext) ? getAST(value) : value);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(targetType, value);
	}

	public Type getTargetType() {
		return targetType;
	}
	public void setTargetType(Type targetType) {
		this.targetType = targetType;
	}

	public Expression getValue() {
		return value;
	}
	public void setValue(Expression value) {
		this.value = value;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Type targetType;
	private Expression value;

	public String toString() {
       return "{targetType:" + getTargetType() + ", value:" + getValue() + "}";
   }
}
