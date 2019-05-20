/**
 * @generated VGen (for ANTLR) 1.4.0
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Program node, Object param);
	public Object visit(VarDefinition node, Object param);
	public Object visit(StructDefinition node, Object param);
	public Object visit(FuncDefinition node, Object param);
	public Object visit(Campo node, Object param);
	public Object visit(ArrayType node, Object param);
	public Object visit(IntType node, Object param);
	public Object visit(RealType node, Object param);
	public Object visit(CharType node, Object param);
	public Object visit(StructType node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(If node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(While node, Object param);
	public Object visit(Assignment node, Object param);
	public Object visit(Function node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(ArithmeticExpression node, Object param);
	public Object visit(BooleanExpression node, Object param);
	public Object visit(AccesoCampo node, Object param);
	public Object visit(Variable node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(FunctionExpression node, Object param);
	public Object visit(AccesoArray node, Object param);
	public Object visit(IntConstant node, Object param);
	public Object visit(RealConstant node, Object param);
	public Object visit(CharConstant node, Object param);
}
