/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package ast;

public abstract class AbstractExpression extends AbstractAST implements Expression {
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
	
	public boolean isModificable() {
		return this.modificable;
	}
	
	private Type type;
	private boolean modificable;
	
}
