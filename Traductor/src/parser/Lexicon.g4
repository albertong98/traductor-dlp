lexer grammar Lexicon
	;

INT_CONSTANT: [0-9]+;

LINE_COMMENT:		'//' .*? '\n'	-> skip;
MULTILINE_COMMENT:	'/*' .*? '*/'	-> skip;

WHITESPACE: [ \t\r\n]+ -> skip;

CHAR
	: '\'' (.|'\\n') '\'';
	
REAL
	:INT_CONSTANT '.' INT_CONSTANT;

STRUCTIDENT
	:[A-Z] [A-Za-z]*
	;

IDENT
	:[A-Za-z0-9]+;	