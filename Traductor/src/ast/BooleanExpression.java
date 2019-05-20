/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

import visitor.*;

import org.antlr.v4.runtime.*;

//	booleanExpression:expression -> left:expression  op:String  right:expression

public class BooleanExpression extends AbstractExpression {

	public BooleanExpression(Expression left, String op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(left, right);
	}

	public BooleanExpression(Object left, Object op, Object right) {
		this.left = (Expression) ((left instanceof ParserRuleContext) ? getAST(left) : left);
		this.op = (op instanceof Token) ? ((Token)op).getText() : (String) op;
		this.right = (Expression) ((right instanceof ParserRuleContext) ? getAST(right) : right);

       // Lo siguiente se puede borrar si no se quiere la posicion en el fichero.
       // Obtiene la linea/columna a partir de las de los hijos.
       setPositions(left, op, right);
	}

	public Expression getLeft() {
		return left;
	}
	public void setLeft(Expression left) {
		this.left = left;
	}

	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}

	public Expression getRight() {
		return right;
	}
	public void setRight(Expression right) {
		this.right = right;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expression left;
	private String op;
	private Expression right;

	public String toString() {
       return "{left:" + getLeft() + ", op:" + getOp() + ", right:" + getRight() + "}";
   }
}
