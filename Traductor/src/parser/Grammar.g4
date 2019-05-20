grammar Grammar;
import Lexicon;

@parser::header {
    import ast.*;
}

start returns [Program ast]
	: defs EOF {$ast = new Program($defs.list);}
	;

defs returns [List<Definition> list = new ArrayList<Definition>()]
	:(def {$list.add($def.ast); })*
	;
	
def returns [Definition ast]
	:IDENT '('params ')'  ':' type '{' defs sentences '}' {$ast = new FuncDefinition($IDENT.text,$params.list,$defs.list,$sentences.list,$type.ast);}
	|IDENT '('params ')' '{' defs sentences '}' {$ast = new FuncDefinition($IDENT.text,$params.list,$defs.list,$sentences.list,$ctx);}
	|'struct' type '{' defs '}'';'	{$ast = new StructDefinition($type.ast,$defs.list); }
	|'var' IDENT ':' type ';' {$ast = new VarDefinition($IDENT,$type.ast); }
	|IDENT ':' type ';' {$ast = new Campo($IDENT,$type.ast); }
	|IDENT ':' type {$ast = new VarDefinition($IDENT,$type.ast); }	
	;
	
params returns [List<Definition> list  = new ArrayList<Definition>()]
	: (p1 = def {$list.add($p1.ast);} (','pn = def{$list.add($pn.ast);})*)* 
	;
	
type returns [Type ast]
	:'[' expr ']' type {$ast = new ArrayType($expr.ast,$type.ast);}
	|'int' {$ast = new IntType();}
	|'float' {$ast = new RealType();}
	|'char' {$ast = new CharType();}
	|STRUCTIDENT {$ast = new StructType($STRUCTIDENT);}
	;

sentences returns [List<Sentence> list = new ArrayList<Sentence>()]
	:(sentence { $list.add($sentence.ast); })*
	;	

sentence returns [Sentence ast]
	:'if' '(' expr  ')''{' sentences '}' {$ast = new If($expr.ast,$sentences.list,new ArrayList<Sentence>());}
	|'if' '(' expr  ')''{' ifSentences = sentences '}' 'else' '{' elseSentences = sentences '}' {$ast = new If($expr.ast,$ifSentences.list,$elseSentences.list);}
	|'read' expr ';' {$ast = new Read($expr.ast);}
	|'while' '(' expr ')''{' sentences '}' {$ast = new While($expr.ast,$sentences.list);}
	|expr '=' expr ';' {$ast = new Assignment($ctx.expr(0).ast,$ctx.expr(1).ast);}
	|'print' expr ';' {$ast = new Print($expr.ast,"");}
	|'printsp' expr ';' {$ast = new Print($expr.ast," ");}
	|'println' expr ';' {$ast = new Print($expr.ast,"\n");}
	|'return' expr ';' {$ast = new Return($expr.ast); }
	|'return' ';' {$ast = new Return($ctx); }
	|IDENT '(' lExpr ')' ';' {$ast = new Function($IDENT,$lExpr.list);}
	;

expressions returns [List<Expression> list = new ArrayList<Expression>()]
	:(expr { $list.add($expr.ast); })*
	; 
	
expr returns [Expression ast]
	:expr '[' expr ']' {$ast = new AccesoArray($ctx.expr(0).ast,$ctx.expr(1).ast);}
	|'('expr')' {$ast = $expr.ast;}
	|'cast' '<'type'>''('expr')' {$ast = new Cast($type.ast,$expr.ast); }
	|e =  expr '.' id = IDENT {$ast = new AccesoCampo($e.ast,$id);}
	|left = expr op = ('/'|'*') right = expr { $ast = new ArithmeticExpression($left.ast, $op, $right.ast); }
	|left = expr op = ('+'|'-') right = expr { $ast = new ArithmeticExpression($left.ast, $op, $right.ast); }
	|left = expr op = ('>' | '<' |'>='|'<=') right = expr { $ast = new BooleanExpression($left.ast, $op, $right.ast); }
	|left = expr op = ('==' |'!=' ) right = expr { $ast = new BooleanExpression($left.ast, $op, $right.ast); }
	|left = expr op = ('&&'|'||'| '!') right = expr { $ast = new BooleanExpression($left.ast, $op, $right.ast); }
	|REAL	{$ast = new RealConstant($REAL);}
	|INT_CONSTANT {$ast = new IntConstant($INT_CONSTANT);}
	|IDENT '('lExpr')'  { $ast = new FunctionExpression($IDENT,$lExpr.list);}
	|CHAR {$ast = new CharConstant($CHAR);}
    |IDENT	{$ast = new Variable($IDENT);}
	;
	
lExpr returns [List<Expression> list = new ArrayList<Expression>();]
	:(p1 = expr {$list.add($p1.ast);} (','pn = expr{$list.add($pn.ast);})*)* 
	;
