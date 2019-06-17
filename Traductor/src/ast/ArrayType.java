/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	arrayType:type -> expression:expression  type:type

public class ArrayType extends AbstractType {

	public ArrayType(Expression expression, Type type) {
		this.expression = expression;
		this.type = type;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expression, type);
	}

	public ArrayType(Object expression, Object type) {
		this.expression = (Expression) ((expression instanceof ParserRuleContext) ? getAST(expression) : expression);
		this.type = (Type) ((type instanceof ParserRuleContext) ? getAST(type) : type);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(expression, type);
	}

	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getSize() {
		if(expression!=null)
			return (type.getSize())*(Integer.parseInt(((IntConstant)expression).getValue()));
		return 0;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression expression;
	private Type type;

	public String toString() {
       return type.toString();
   }

	@Override
	public char getSuffix() {
		return type.getSuffix();
	}

	@Override
	public String getMAPLName() {
		return type.getMAPLName();
	}
}
