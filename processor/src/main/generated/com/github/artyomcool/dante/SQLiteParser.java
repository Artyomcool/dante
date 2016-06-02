/*
 * Copyright (c)  2015-2016, Artyom Drozdov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// Generated from com/github/artyomcool/dante/SQLite.g4 by ANTLR 4.5.1

   package com.github.artyomcool.dante;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLiteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, SCOL=4, DOT=5, OPEN_PAR=6, CLOSE_PAR=7, COMMA=8, 
		ASSIGN=9, STAR=10, PLUS=11, MINUS=12, TILDE=13, PIPE2=14, DIV=15, MOD=16, 
		LT2=17, GT2=18, AMP=19, PIPE=20, LT=21, LT_EQ=22, GT=23, GT_EQ=24, EQ=25, 
		NOT_EQ1=26, NOT_EQ2=27, K_ABORT=28, K_ACTION=29, K_ADD=30, K_AFTER=31, 
		K_ALL=32, K_ALTER=33, K_ANALYZE=34, K_AND=35, K_AS=36, K_ASC=37, K_ATTACH=38, 
		K_AUTOINCREMENT=39, K_BEFORE=40, K_BEGIN=41, K_BETWEEN=42, K_BY=43, K_CASCADE=44, 
		K_CASE=45, K_CAST=46, K_CHECK=47, K_COLLATE=48, K_COLUMN=49, K_COMMIT=50, 
		K_CONFLICT=51, K_CONSTRAINT=52, K_CREATE=53, K_CROSS=54, K_CURRENT_DATE=55, 
		K_CURRENT_TIME=56, K_CURRENT_TIMESTAMP=57, K_DATABASE=58, K_DEFAULT=59, 
		K_DEFERRABLE=60, K_DEFERRED=61, K_DELETE=62, K_DESC=63, K_DETACH=64, K_DISTINCT=65, 
		K_DROP=66, K_EACH=67, K_ELSE=68, K_END=69, K_ESCAPE=70, K_EXCEPT=71, K_EXCLUSIVE=72, 
		K_EXISTS=73, K_EXPLAIN=74, K_FAIL=75, K_FOR=76, K_FOREIGN=77, K_FROM=78, 
		K_FULL=79, K_GLOB=80, K_GROUP=81, K_HAVING=82, K_IF=83, K_IGNORE=84, K_IMMEDIATE=85, 
		K_IN=86, K_INDEX=87, K_INDEXED=88, K_INITIALLY=89, K_INNER=90, K_INSERT=91, 
		K_INSTEAD=92, K_INTERSECT=93, K_INTO=94, K_IS=95, K_ISNULL=96, K_JOIN=97, 
		K_KEY=98, K_LEFT=99, K_LIKE=100, K_LIMIT=101, K_MATCH=102, K_NATURAL=103, 
		K_NO=104, K_NOT=105, K_NOTNULL=106, K_NULL=107, K_OF=108, K_OFFSET=109, 
		K_ON=110, K_OR=111, K_ORDER=112, K_OUTER=113, K_PLAN=114, K_PRAGMA=115, 
		K_PRIMARY=116, K_QUERY=117, K_RAISE=118, K_RECURSIVE=119, K_REFERENCES=120, 
		K_REGEXP=121, K_REINDEX=122, K_RELEASE=123, K_RENAME=124, K_REPLACE=125, 
		K_RESTRICT=126, K_RIGHT=127, K_ROLLBACK=128, K_ROW=129, K_SAVEPOINT=130, 
		K_SELECT=131, K_SET=132, K_TABLE=133, K_TEMP=134, K_TEMPORARY=135, K_THEN=136, 
		K_TO=137, K_TRANSACTION=138, K_TRIGGER=139, K_UNION=140, K_UNIQUE=141, 
		K_UPDATE=142, K_USING=143, K_VACUUM=144, K_VALUES=145, K_VIEW=146, K_VIRTUAL=147, 
		K_WHEN=148, K_WHERE=149, K_WITH=150, K_WITHOUT=151, IDENTIFIER=152, NUMERIC_LITERAL=153, 
		STRING_LITERAL=154, BLOB_LITERAL=155, SPACES=156, UNEXPECTED_CHAR=157;
	public static final int
		RULE_parse = 0, RULE_select_stmt = 1, RULE_select_or_values = 2, RULE_type_name = 3, 
		RULE_expr = 4, RULE_full_column_name = 5, RULE_raise_function = 6, RULE_ordering_term = 7, 
		RULE_common_table_expression = 8, RULE_result_column = 9, RULE_table_or_subquery = 10, 
		RULE_join_clause = 11, RULE_join_operator = 12, RULE_join_constraint = 13, 
		RULE_compound_operator = 14, RULE_signed_number = 15, RULE_literal_value = 16, 
		RULE_unary_operator = 17, RULE_error_message = 18, RULE_column_alias = 19, 
		RULE_keyword = 20, RULE_name = 21, RULE_function_name = 22, RULE_database_name = 23, 
		RULE_table_name = 24, RULE_column_name = 25, RULE_collation_name = 26, 
		RULE_index_name = 27, RULE_table_alias = 28, RULE_any_name = 29, RULE_bind_name = 30, 
		RULE_bind_parameter = 31;
	public static final String[] ruleNames = {
		"parse", "select_stmt", "select_or_values", "type_name", "expr", "full_column_name", 
		"raise_function", "ordering_term", "common_table_expression", "result_column", 
		"table_or_subquery", "join_clause", "join_operator", "join_constraint", 
		"compound_operator", "signed_number", "literal_value", "unary_operator", 
		"error_message", "column_alias", "keyword", "name", "function_name", "database_name", 
		"table_name", "column_name", "collation_name", "index_name", "table_alias", 
		"any_name", "bind_name", "bind_parameter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'@'", "'$'", "';'", "'.'", "'('", "')'", "','", "'='", "'*'", 
		"'+'", "'-'", "'~'", "'||'", "'/'", "'%'", "'<<'", "'>>'", "'&'", "'|'", 
		"'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "SCOL", "DOT", "OPEN_PAR", "CLOSE_PAR", "COMMA", 
		"ASSIGN", "STAR", "PLUS", "MINUS", "TILDE", "PIPE2", "DIV", "MOD", "LT2", 
		"GT2", "AMP", "PIPE", "LT", "LT_EQ", "GT", "GT_EQ", "EQ", "NOT_EQ1", "NOT_EQ2", 
		"K_ABORT", "K_ACTION", "K_ADD", "K_AFTER", "K_ALL", "K_ALTER", "K_ANALYZE", 
		"K_AND", "K_AS", "K_ASC", "K_ATTACH", "K_AUTOINCREMENT", "K_BEFORE", "K_BEGIN", 
		"K_BETWEEN", "K_BY", "K_CASCADE", "K_CASE", "K_CAST", "K_CHECK", "K_COLLATE", 
		"K_COLUMN", "K_COMMIT", "K_CONFLICT", "K_CONSTRAINT", "K_CREATE", "K_CROSS", 
		"K_CURRENT_DATE", "K_CURRENT_TIME", "K_CURRENT_TIMESTAMP", "K_DATABASE", 
		"K_DEFAULT", "K_DEFERRABLE", "K_DEFERRED", "K_DELETE", "K_DESC", "K_DETACH", 
		"K_DISTINCT", "K_DROP", "K_EACH", "K_ELSE", "K_END", "K_ESCAPE", "K_EXCEPT", 
		"K_EXCLUSIVE", "K_EXISTS", "K_EXPLAIN", "K_FAIL", "K_FOR", "K_FOREIGN", 
		"K_FROM", "K_FULL", "K_GLOB", "K_GROUP", "K_HAVING", "K_IF", "K_IGNORE", 
		"K_IMMEDIATE", "K_IN", "K_INDEX", "K_INDEXED", "K_INITIALLY", "K_INNER", 
		"K_INSERT", "K_INSTEAD", "K_INTERSECT", "K_INTO", "K_IS", "K_ISNULL", 
		"K_JOIN", "K_KEY", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", "K_NATURAL", 
		"K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_OF", "K_OFFSET", "K_ON", "K_OR", 
		"K_ORDER", "K_OUTER", "K_PLAN", "K_PRAGMA", "K_PRIMARY", "K_QUERY", "K_RAISE", 
		"K_RECURSIVE", "K_REFERENCES", "K_REGEXP", "K_REINDEX", "K_RELEASE", "K_RENAME", 
		"K_REPLACE", "K_RESTRICT", "K_RIGHT", "K_ROLLBACK", "K_ROW", "K_SAVEPOINT", 
		"K_SELECT", "K_SET", "K_TABLE", "K_TEMP", "K_TEMPORARY", "K_THEN", "K_TO", 
		"K_TRANSACTION", "K_TRIGGER", "K_UNION", "K_UNIQUE", "K_UPDATE", "K_USING", 
		"K_VACUUM", "K_VALUES", "K_VIEW", "K_VIRTUAL", "K_WHEN", "K_WHERE", "K_WITH", 
		"K_WITHOUT", "IDENTIFIER", "NUMERIC_LITERAL", "STRING_LITERAL", "BLOB_LITERAL", 
		"SPACES", "UNEXPECTED_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SQLite.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLiteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EOF() { return getToken(SQLiteParser.EOF, 0); }
		public TerminalNode K_GROUP() { return getToken(SQLiteParser.K_GROUP, 0); }
		public List<TerminalNode> K_BY() { return getTokens(SQLiteParser.K_BY); }
		public TerminalNode K_BY(int i) {
			return getToken(SQLiteParser.K_BY, i);
		}
		public TerminalNode K_ORDER() { return getToken(SQLiteParser.K_ORDER, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(SQLiteParser.K_LIMIT, 0); }
		public TerminalNode K_HAVING() { return getToken(SQLiteParser.K_HAVING, 0); }
		public TerminalNode K_OFFSET() { return getToken(SQLiteParser.K_OFFSET, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			expr(0);
			setState(79);
			_la = _input.LA(1);
			if (_la==K_GROUP) {
				{
				setState(65);
				match(K_GROUP);
				setState(66);
				match(K_BY);
				setState(67);
				expr(0);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(68);
					match(COMMA);
					setState(69);
					expr(0);
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(77);
				_la = _input.LA(1);
				if (_la==K_HAVING) {
					{
					setState(75);
					match(K_HAVING);
					setState(76);
					expr(0);
					}
				}

				}
			}

			setState(91);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(81);
				match(K_ORDER);
				setState(82);
				match(K_BY);
				setState(83);
				ordering_term();
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(84);
					match(COMMA);
					setState(85);
					ordering_term();
					}
					}
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(99);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(93);
				match(K_LIMIT);
				setState(94);
				expr(0);
				setState(97);
				_la = _input.LA(1);
				if (_la==COMMA || _la==K_OFFSET) {
					{
					setState(95);
					_la = _input.LA(1);
					if ( !(_la==COMMA || _la==K_OFFSET) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(96);
					expr(0);
					}
				}

				}
			}

			setState(101);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_stmtContext extends ParserRuleContext {
		public List<Select_or_valuesContext> select_or_values() {
			return getRuleContexts(Select_or_valuesContext.class);
		}
		public Select_or_valuesContext select_or_values(int i) {
			return getRuleContext(Select_or_valuesContext.class,i);
		}
		public TerminalNode K_WITH() { return getToken(SQLiteParser.K_WITH, 0); }
		public List<Common_table_expressionContext> common_table_expression() {
			return getRuleContexts(Common_table_expressionContext.class);
		}
		public Common_table_expressionContext common_table_expression(int i) {
			return getRuleContext(Common_table_expressionContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public TerminalNode K_ORDER() { return getToken(SQLiteParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(SQLiteParser.K_BY, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(SQLiteParser.K_LIMIT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_RECURSIVE() { return getToken(SQLiteParser.K_RECURSIVE, 0); }
		public TerminalNode K_OFFSET() { return getToken(SQLiteParser.K_OFFSET, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitSelect_stmt(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(103);
				match(K_WITH);
				setState(105);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(104);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(107);
				common_table_expression();
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(108);
					match(COMMA);
					setState(109);
					common_table_expression();
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(117);
			select_or_values();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_EXCEPT || _la==K_INTERSECT || _la==K_UNION) {
				{
				{
				setState(118);
				compound_operator();
				setState(119);
				select_or_values();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(126);
				match(K_ORDER);
				setState(127);
				match(K_BY);
				setState(128);
				ordering_term();
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(129);
					match(COMMA);
					setState(130);
					ordering_term();
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(144);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(138);
				match(K_LIMIT);
				setState(139);
				expr(0);
				setState(142);
				_la = _input.LA(1);
				if (_la==COMMA || _la==K_OFFSET) {
					{
					setState(140);
					_la = _input.LA(1);
					if ( !(_la==COMMA || _la==K_OFFSET) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(141);
					expr(0);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_or_valuesContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLiteParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(SQLiteParser.K_FROM, 0); }
		public TerminalNode K_WHERE() { return getToken(SQLiteParser.K_WHERE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_GROUP() { return getToken(SQLiteParser.K_GROUP, 0); }
		public TerminalNode K_BY() { return getToken(SQLiteParser.K_BY, 0); }
		public TerminalNode K_DISTINCT() { return getToken(SQLiteParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(SQLiteParser.K_ALL, 0); }
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_HAVING() { return getToken(SQLiteParser.K_HAVING, 0); }
		public TerminalNode K_VALUES() { return getToken(SQLiteParser.K_VALUES, 0); }
		public Select_or_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_or_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterSelect_or_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitSelect_or_values(this);
		}
	}

	public final Select_or_valuesContext select_or_values() throws RecognitionException {
		Select_or_valuesContext _localctx = new Select_or_valuesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select_or_values);
		int _la;
		try {
			setState(220);
			switch (_input.LA(1)) {
			case K_SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(K_SELECT);
				setState(148);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(147);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(150);
				result_column();
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(151);
					match(COMMA);
					setState(152);
					result_column();
					}
					}
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(170);
				_la = _input.LA(1);
				if (_la==K_FROM) {
					{
					setState(158);
					match(K_FROM);
					setState(168);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						setState(159);
						table_or_subquery();
						setState(164);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(160);
							match(COMMA);
							setState(161);
							table_or_subquery();
							}
							}
							setState(166);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					case 2:
						{
						setState(167);
						join_clause();
						}
						break;
					}
					}
				}

				setState(174);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(172);
					match(K_WHERE);
					setState(173);
					expr(0);
					}
				}

				setState(190);
				_la = _input.LA(1);
				if (_la==K_GROUP) {
					{
					setState(176);
					match(K_GROUP);
					setState(177);
					match(K_BY);
					setState(178);
					expr(0);
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(179);
						match(COMMA);
						setState(180);
						expr(0);
						}
						}
						setState(185);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(188);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(186);
						match(K_HAVING);
						setState(187);
						expr(0);
						}
					}

					}
				}

				}
				break;
			case K_VALUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				match(K_VALUES);
				setState(193);
				match(OPEN_PAR);
				setState(194);
				expr(0);
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(195);
					match(COMMA);
					setState(196);
					expr(0);
					}
					}
					setState(201);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(202);
				match(CLOSE_PAR);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(203);
					match(COMMA);
					setState(204);
					match(OPEN_PAR);
					setState(205);
					expr(0);
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(206);
						match(COMMA);
						setState(207);
						expr(0);
						}
						}
						setState(212);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(213);
					match(CLOSE_PAR);
					}
					}
					setState(219);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<Signed_numberContext> signed_number() {
			return getRuleContexts(Signed_numberContext.class);
		}
		public Signed_numberContext signed_number(int i) {
			return getRuleContext(Signed_numberContext.class,i);
		}
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitType_name(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(222);
					name();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(225); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(237);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(227);
				match(OPEN_PAR);
				setState(228);
				signed_number();
				setState(229);
				match(CLOSE_PAR);
				}
				break;
			case 2:
				{
				setState(231);
				match(OPEN_PAR);
				setState(232);
				signed_number();
				setState(233);
				match(COMMA);
				setState(234);
				signed_number();
				setState(235);
				match(CLOSE_PAR);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Bind_parameterContext bind_parameter() {
			return getRuleContext(Bind_parameterContext.class,0);
		}
		public Full_column_nameContext full_column_name() {
			return getRuleContext(Full_column_nameContext.class,0);
		}
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(SQLiteParser.K_DISTINCT, 0); }
		public TerminalNode K_CAST() { return getToken(SQLiteParser.K_CAST, 0); }
		public TerminalNode K_AS() { return getToken(SQLiteParser.K_AS, 0); }
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public TerminalNode K_EXISTS() { return getToken(SQLiteParser.K_EXISTS, 0); }
		public TerminalNode K_NOT() { return getToken(SQLiteParser.K_NOT, 0); }
		public TerminalNode K_CASE() { return getToken(SQLiteParser.K_CASE, 0); }
		public TerminalNode K_END() { return getToken(SQLiteParser.K_END, 0); }
		public List<TerminalNode> K_WHEN() { return getTokens(SQLiteParser.K_WHEN); }
		public TerminalNode K_WHEN(int i) {
			return getToken(SQLiteParser.K_WHEN, i);
		}
		public List<TerminalNode> K_THEN() { return getTokens(SQLiteParser.K_THEN); }
		public TerminalNode K_THEN(int i) {
			return getToken(SQLiteParser.K_THEN, i);
		}
		public TerminalNode K_ELSE() { return getToken(SQLiteParser.K_ELSE, 0); }
		public Raise_functionContext raise_function() {
			return getRuleContext(Raise_functionContext.class,0);
		}
		public TerminalNode K_IS() { return getToken(SQLiteParser.K_IS, 0); }
		public TerminalNode K_IN() { return getToken(SQLiteParser.K_IN, 0); }
		public TerminalNode K_LIKE() { return getToken(SQLiteParser.K_LIKE, 0); }
		public TerminalNode K_GLOB() { return getToken(SQLiteParser.K_GLOB, 0); }
		public TerminalNode K_MATCH() { return getToken(SQLiteParser.K_MATCH, 0); }
		public TerminalNode K_REGEXP() { return getToken(SQLiteParser.K_REGEXP, 0); }
		public TerminalNode K_AND() { return getToken(SQLiteParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(SQLiteParser.K_OR, 0); }
		public TerminalNode K_BETWEEN() { return getToken(SQLiteParser.K_BETWEEN, 0); }
		public TerminalNode K_COLLATE() { return getToken(SQLiteParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ESCAPE() { return getToken(SQLiteParser.K_ESCAPE, 0); }
		public TerminalNode K_ISNULL() { return getToken(SQLiteParser.K_ISNULL, 0); }
		public TerminalNode K_NOTNULL() { return getToken(SQLiteParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLiteParser.K_NULL, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(240);
				unary_operator();
				setState(241);
				expr(21);
				}
				break;
			case 2:
				{
				setState(243);
				literal_value();
				}
				break;
			case 3:
				{
				setState(244);
				bind_parameter();
				}
				break;
			case 4:
				{
				setState(245);
				full_column_name();
				}
				break;
			case 5:
				{
				setState(246);
				function_name();
				setState(247);
				match(OPEN_PAR);
				setState(260);
				switch (_input.LA(1)) {
				case T__0:
				case T__1:
				case T__2:
				case OPEN_PAR:
				case PLUS:
				case MINUS:
				case TILDE:
				case K_ABORT:
				case K_ACTION:
				case K_ADD:
				case K_AFTER:
				case K_ALL:
				case K_ALTER:
				case K_ANALYZE:
				case K_AND:
				case K_AS:
				case K_ASC:
				case K_ATTACH:
				case K_AUTOINCREMENT:
				case K_BEFORE:
				case K_BEGIN:
				case K_BETWEEN:
				case K_BY:
				case K_CASCADE:
				case K_CASE:
				case K_CAST:
				case K_CHECK:
				case K_COLLATE:
				case K_COLUMN:
				case K_COMMIT:
				case K_CONFLICT:
				case K_CONSTRAINT:
				case K_CREATE:
				case K_CROSS:
				case K_CURRENT_DATE:
				case K_CURRENT_TIME:
				case K_CURRENT_TIMESTAMP:
				case K_DATABASE:
				case K_DEFAULT:
				case K_DEFERRABLE:
				case K_DEFERRED:
				case K_DELETE:
				case K_DESC:
				case K_DETACH:
				case K_DISTINCT:
				case K_DROP:
				case K_EACH:
				case K_ELSE:
				case K_END:
				case K_ESCAPE:
				case K_EXCEPT:
				case K_EXCLUSIVE:
				case K_EXISTS:
				case K_EXPLAIN:
				case K_FAIL:
				case K_FOR:
				case K_FOREIGN:
				case K_FROM:
				case K_FULL:
				case K_GLOB:
				case K_GROUP:
				case K_HAVING:
				case K_IF:
				case K_IGNORE:
				case K_IMMEDIATE:
				case K_IN:
				case K_INDEX:
				case K_INDEXED:
				case K_INITIALLY:
				case K_INNER:
				case K_INSERT:
				case K_INSTEAD:
				case K_INTERSECT:
				case K_INTO:
				case K_IS:
				case K_ISNULL:
				case K_JOIN:
				case K_KEY:
				case K_LEFT:
				case K_LIKE:
				case K_LIMIT:
				case K_MATCH:
				case K_NATURAL:
				case K_NO:
				case K_NOT:
				case K_NOTNULL:
				case K_NULL:
				case K_OF:
				case K_OFFSET:
				case K_ON:
				case K_OR:
				case K_ORDER:
				case K_OUTER:
				case K_PLAN:
				case K_PRAGMA:
				case K_PRIMARY:
				case K_QUERY:
				case K_RAISE:
				case K_RECURSIVE:
				case K_REFERENCES:
				case K_REGEXP:
				case K_REINDEX:
				case K_RELEASE:
				case K_RENAME:
				case K_REPLACE:
				case K_RESTRICT:
				case K_RIGHT:
				case K_ROLLBACK:
				case K_ROW:
				case K_SAVEPOINT:
				case K_SELECT:
				case K_SET:
				case K_TABLE:
				case K_TEMP:
				case K_TEMPORARY:
				case K_THEN:
				case K_TO:
				case K_TRANSACTION:
				case K_TRIGGER:
				case K_UNION:
				case K_UNIQUE:
				case K_UPDATE:
				case K_USING:
				case K_VACUUM:
				case K_VALUES:
				case K_VIEW:
				case K_VIRTUAL:
				case K_WHEN:
				case K_WHERE:
				case K_WITH:
				case K_WITHOUT:
				case IDENTIFIER:
				case NUMERIC_LITERAL:
				case STRING_LITERAL:
				case BLOB_LITERAL:
					{
					setState(249);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(248);
						match(K_DISTINCT);
						}
						break;
					}
					setState(251);
					expr(0);
					setState(256);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(252);
						match(COMMA);
						setState(253);
						expr(0);
						}
						}
						setState(258);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case STAR:
					{
					setState(259);
					match(STAR);
					}
					break;
				case CLOSE_PAR:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(262);
				match(CLOSE_PAR);
				}
				break;
			case 6:
				{
				setState(264);
				match(OPEN_PAR);
				setState(265);
				expr(0);
				setState(266);
				match(CLOSE_PAR);
				}
				break;
			case 7:
				{
				setState(268);
				match(K_CAST);
				setState(269);
				match(OPEN_PAR);
				setState(270);
				expr(0);
				setState(271);
				match(K_AS);
				setState(272);
				type_name();
				setState(273);
				match(CLOSE_PAR);
				}
				break;
			case 8:
				{
				setState(279);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(276);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(275);
						match(K_NOT);
						}
					}

					setState(278);
					match(K_EXISTS);
					}
				}

				setState(281);
				match(OPEN_PAR);
				setState(282);
				select_stmt();
				setState(283);
				match(CLOSE_PAR);
				}
				break;
			case 9:
				{
				setState(285);
				match(K_CASE);
				setState(287);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(286);
					expr(0);
					}
					break;
				}
				setState(294); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(289);
					match(K_WHEN);
					setState(290);
					expr(0);
					setState(291);
					match(K_THEN);
					setState(292);
					expr(0);
					}
					}
					setState(296); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(300);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(298);
					match(K_ELSE);
					setState(299);
					expr(0);
					}
				}

				setState(302);
				match(K_END);
				}
				break;
			case 10:
				{
				setState(304);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(407);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(405);
					switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(307);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(308);
						match(PIPE2);
						setState(309);
						expr(21);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(310);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(311);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(312);
						expr(20);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(313);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(314);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(315);
						expr(19);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(316);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(317);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT2) | (1L << GT2) | (1L << AMP) | (1L << PIPE))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(318);
						expr(18);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(319);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(320);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << LT_EQ) | (1L << GT) | (1L << GT_EQ))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(321);
						expr(17);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(322);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(335);
						switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(323);
							match(ASSIGN);
							}
							break;
						case 2:
							{
							setState(324);
							match(EQ);
							}
							break;
						case 3:
							{
							setState(325);
							match(NOT_EQ1);
							}
							break;
						case 4:
							{
							setState(326);
							match(NOT_EQ2);
							}
							break;
						case 5:
							{
							setState(327);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(328);
							match(K_IS);
							setState(329);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(330);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(331);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(332);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(333);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(334);
							match(K_REGEXP);
							}
							break;
						}
						setState(337);
						expr(16);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(338);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(339);
						match(K_AND);
						setState(340);
						expr(15);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(341);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(342);
						match(K_OR);
						setState(343);
						expr(14);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(344);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(345);
						match(K_IS);
						setState(347);
						switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
						case 1:
							{
							setState(346);
							match(K_NOT);
							}
							break;
						}
						setState(349);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(350);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(352);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(351);
							match(K_NOT);
							}
						}

						setState(354);
						match(K_BETWEEN);
						setState(355);
						expr(0);
						setState(356);
						match(K_AND);
						setState(357);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(359);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(360);
						match(K_COLLATE);
						setState(361);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(362);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(364);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(363);
							match(K_NOT);
							}
						}

						setState(366);
						_la = _input.LA(1);
						if ( !(((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (K_GLOB - 80)) | (1L << (K_LIKE - 80)) | (1L << (K_MATCH - 80)) | (1L << (K_REGEXP - 80)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(367);
						expr(0);
						setState(370);
						switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
						case 1:
							{
							setState(368);
							match(K_ESCAPE);
							setState(369);
							expr(0);
							}
							break;
						}
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(372);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(377);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(373);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(374);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(375);
							match(K_NOT);
							setState(376);
							match(K_NULL);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(379);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(381);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(380);
							match(K_NOT);
							}
						}

						setState(383);
						match(K_IN);
						setState(403);
						switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
						case 1:
							{
							setState(384);
							match(OPEN_PAR);
							setState(394);
							switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
							case 1:
								{
								setState(385);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(386);
								expr(0);
								setState(391);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==COMMA) {
									{
									{
									setState(387);
									match(COMMA);
									setState(388);
									expr(0);
									}
									}
									setState(393);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(396);
							match(CLOSE_PAR);
							}
							break;
						case 2:
							{
							setState(400);
							switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
							case 1:
								{
								setState(397);
								database_name();
								setState(398);
								match(DOT);
								}
								break;
							}
							setState(402);
							table_name();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(409);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Full_column_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Full_column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_full_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterFull_column_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitFull_column_name(this);
		}
	}

	public final Full_column_nameContext full_column_name() throws RecognitionException {
		Full_column_nameContext _localctx = new Full_column_nameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_full_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(413);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(410);
					database_name();
					setState(411);
					match(DOT);
					}
					break;
				}
				setState(415);
				table_name();
				setState(416);
				match(DOT);
				}
				break;
			}
			setState(420);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Raise_functionContext extends ParserRuleContext {
		public TerminalNode K_RAISE() { return getToken(SQLiteParser.K_RAISE, 0); }
		public TerminalNode K_IGNORE() { return getToken(SQLiteParser.K_IGNORE, 0); }
		public Error_messageContext error_message() {
			return getRuleContext(Error_messageContext.class,0);
		}
		public TerminalNode K_ROLLBACK() { return getToken(SQLiteParser.K_ROLLBACK, 0); }
		public TerminalNode K_ABORT() { return getToken(SQLiteParser.K_ABORT, 0); }
		public TerminalNode K_FAIL() { return getToken(SQLiteParser.K_FAIL, 0); }
		public Raise_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterRaise_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitRaise_function(this);
		}
	}

	public final Raise_functionContext raise_function() throws RecognitionException {
		Raise_functionContext _localctx = new Raise_functionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_raise_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			match(K_RAISE);
			setState(423);
			match(OPEN_PAR);
			setState(428);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(424);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(425);
				_la = _input.LA(1);
				if ( !(_la==K_ABORT || _la==K_FAIL || _la==K_ROLLBACK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(426);
				match(COMMA);
				setState(427);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(430);
			match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_termContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_COLLATE() { return getToken(SQLiteParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(SQLiteParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(SQLiteParser.K_DESC, 0); }
		public Ordering_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterOrdering_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitOrdering_term(this);
		}
	}

	public final Ordering_termContext ordering_term() throws RecognitionException {
		Ordering_termContext _localctx = new Ordering_termContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			expr(0);
			setState(435);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(433);
				match(K_COLLATE);
				setState(434);
				collation_name();
				}
			}

			setState(438);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(437);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Common_table_expressionContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(SQLiteParser.K_AS, 0); }
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Common_table_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_common_table_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterCommon_table_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitCommon_table_expression(this);
		}
	}

	public final Common_table_expressionContext common_table_expression() throws RecognitionException {
		Common_table_expressionContext _localctx = new Common_table_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_common_table_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			table_name();
			setState(452);
			_la = _input.LA(1);
			if (_la==OPEN_PAR) {
				{
				setState(441);
				match(OPEN_PAR);
				setState(442);
				column_name();
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(443);
					match(COMMA);
					setState(444);
					column_name();
					}
					}
					setState(449);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(450);
				match(CLOSE_PAR);
				}
			}

			setState(454);
			match(K_AS);
			setState(455);
			match(OPEN_PAR);
			setState(456);
			select_stmt();
			setState(457);
			match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Result_columnContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(SQLiteParser.K_AS, 0); }
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitResult_column(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_result_column);
		int _la;
		try {
			setState(471);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(459);
				match(STAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(460);
				table_name();
				setState(461);
				match(DOT);
				setState(462);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(464);
				expr(0);
				setState(469);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(466);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(465);
						match(K_AS);
						}
					}

					setState(468);
					column_alias();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_or_subqueryContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode K_INDEXED() { return getToken(SQLiteParser.K_INDEXED, 0); }
		public TerminalNode K_BY() { return getToken(SQLiteParser.K_BY, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public TerminalNode K_NOT() { return getToken(SQLiteParser.K_NOT, 0); }
		public TerminalNode K_AS() { return getToken(SQLiteParser.K_AS, 0); }
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Table_or_subqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_or_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterTable_or_subquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitTable_or_subquery(this);
		}
	}

	public final Table_or_subqueryContext table_or_subquery() throws RecognitionException {
		Table_or_subqueryContext _localctx = new Table_or_subqueryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_table_or_subquery);
		int _la;
		try {
			setState(520);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(476);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(473);
					database_name();
					setState(474);
					match(DOT);
					}
					break;
				}
				setState(478);
				table_name();
				setState(483);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(480);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						setState(479);
						match(K_AS);
						}
						break;
					}
					setState(482);
					table_alias();
					}
					break;
				}
				setState(490);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(485);
					match(K_INDEXED);
					setState(486);
					match(K_BY);
					setState(487);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(488);
					match(K_NOT);
					setState(489);
					match(K_INDEXED);
					}
					break;
				case CLOSE_PAR:
				case COMMA:
				case K_CROSS:
				case K_EXCEPT:
				case K_GROUP:
				case K_INNER:
				case K_INTERSECT:
				case K_JOIN:
				case K_LEFT:
				case K_LIMIT:
				case K_NATURAL:
				case K_ON:
				case K_ORDER:
				case K_UNION:
				case K_USING:
				case K_WHERE:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(492);
				match(OPEN_PAR);
				setState(502);
				switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(493);
					table_or_subquery();
					setState(498);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(494);
						match(COMMA);
						setState(495);
						table_or_subquery();
						}
						}
						setState(500);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(501);
					join_clause();
					}
					break;
				}
				setState(504);
				match(CLOSE_PAR);
				setState(509);
				switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(506);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
					case 1:
						{
						setState(505);
						match(K_AS);
						}
						break;
					}
					setState(508);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(511);
				match(OPEN_PAR);
				setState(512);
				select_stmt();
				setState(513);
				match(CLOSE_PAR);
				setState(518);
				switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					{
					setState(515);
					switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
					case 1:
						{
						setState(514);
						match(K_AS);
						}
						break;
					}
					setState(517);
					table_alias();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_clauseContext extends ParserRuleContext {
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public List<Join_operatorContext> join_operator() {
			return getRuleContexts(Join_operatorContext.class);
		}
		public Join_operatorContext join_operator(int i) {
			return getRuleContext(Join_operatorContext.class,i);
		}
		public List<Join_constraintContext> join_constraint() {
			return getRuleContexts(Join_constraintContext.class);
		}
		public Join_constraintContext join_constraint(int i) {
			return getRuleContext(Join_constraintContext.class,i);
		}
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterJoin_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitJoin_clause(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_join_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			table_or_subquery();
			setState(529);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==K_CROSS || ((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (K_INNER - 90)) | (1L << (K_JOIN - 90)) | (1L << (K_LEFT - 90)) | (1L << (K_NATURAL - 90)))) != 0)) {
				{
				{
				setState(523);
				join_operator();
				setState(524);
				table_or_subquery();
				setState(525);
				join_constraint();
				}
				}
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_operatorContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(SQLiteParser.K_JOIN, 0); }
		public TerminalNode K_NATURAL() { return getToken(SQLiteParser.K_NATURAL, 0); }
		public TerminalNode K_LEFT() { return getToken(SQLiteParser.K_LEFT, 0); }
		public TerminalNode K_INNER() { return getToken(SQLiteParser.K_INNER, 0); }
		public TerminalNode K_CROSS() { return getToken(SQLiteParser.K_CROSS, 0); }
		public TerminalNode K_OUTER() { return getToken(SQLiteParser.K_OUTER, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterJoin_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitJoin_operator(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_join_operator);
		int _la;
		try {
			setState(545);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(532);
				match(COMMA);
				}
				break;
			case K_CROSS:
			case K_INNER:
			case K_JOIN:
			case K_LEFT:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(534);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(533);
					match(K_NATURAL);
					}
				}

				setState(542);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(536);
					match(K_LEFT);
					setState(538);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(537);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(540);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(541);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(544);
				match(K_JOIN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_constraintContext extends ParserRuleContext {
		public TerminalNode K_ON() { return getToken(SQLiteParser.K_ON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_USING() { return getToken(SQLiteParser.K_USING, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Join_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterJoin_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitJoin_constraint(this);
		}
	}

	public final Join_constraintContext join_constraint() throws RecognitionException {
		Join_constraintContext _localctx = new Join_constraintContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561);
			switch (_input.LA(1)) {
			case K_ON:
				{
				setState(547);
				match(K_ON);
				setState(548);
				expr(0);
				}
				break;
			case K_USING:
				{
				setState(549);
				match(K_USING);
				setState(550);
				match(OPEN_PAR);
				setState(551);
				column_name();
				setState(556);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(552);
					match(COMMA);
					setState(553);
					column_name();
					}
					}
					setState(558);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(559);
				match(CLOSE_PAR);
				}
				break;
			case CLOSE_PAR:
			case COMMA:
			case K_CROSS:
			case K_EXCEPT:
			case K_GROUP:
			case K_INNER:
			case K_INTERSECT:
			case K_JOIN:
			case K_LEFT:
			case K_LIMIT:
			case K_NATURAL:
			case K_ORDER:
			case K_UNION:
			case K_WHERE:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_operatorContext extends ParserRuleContext {
		public TerminalNode K_UNION() { return getToken(SQLiteParser.K_UNION, 0); }
		public TerminalNode K_ALL() { return getToken(SQLiteParser.K_ALL, 0); }
		public TerminalNode K_INTERSECT() { return getToken(SQLiteParser.K_INTERSECT, 0); }
		public TerminalNode K_EXCEPT() { return getToken(SQLiteParser.K_EXCEPT, 0); }
		public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterCompound_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitCompound_operator(this);
		}
	}

	public final Compound_operatorContext compound_operator() throws RecognitionException {
		Compound_operatorContext _localctx = new Compound_operatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_compound_operator);
		try {
			setState(568);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(563);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(564);
				match(K_UNION);
				setState(565);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(566);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(567);
				match(K_EXCEPT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLiteParser.NUMERIC_LITERAL, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitSigned_number(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(570);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(573);
			match(NUMERIC_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLiteParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SQLiteParser.STRING_LITERAL, 0); }
		public TerminalNode BLOB_LITERAL() { return getToken(SQLiteParser.BLOB_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLiteParser.K_NULL, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(SQLiteParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(SQLiteParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(SQLiteParser.K_CURRENT_TIMESTAMP, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitLiteral_value(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (K_NULL - 107)) | (1L << (NUMERIC_LITERAL - 107)) | (1L << (STRING_LITERAL - 107)) | (1L << (BLOB_LITERAL - 107)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(SQLiteParser.K_NOT, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitUnary_operator(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(577);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TILDE))) != 0) || _la==K_NOT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Error_messageContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(SQLiteParser.STRING_LITERAL, 0); }
		public Error_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterError_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitError_message(this);
		}
	}

	public final Error_messageContext error_message() throws RecognitionException {
		Error_messageContext _localctx = new Error_messageContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_error_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(579);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_aliasContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLiteParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SQLiteParser.STRING_LITERAL, 0); }
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitColumn_alias(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode K_ABORT() { return getToken(SQLiteParser.K_ABORT, 0); }
		public TerminalNode K_ACTION() { return getToken(SQLiteParser.K_ACTION, 0); }
		public TerminalNode K_ADD() { return getToken(SQLiteParser.K_ADD, 0); }
		public TerminalNode K_AFTER() { return getToken(SQLiteParser.K_AFTER, 0); }
		public TerminalNode K_ALL() { return getToken(SQLiteParser.K_ALL, 0); }
		public TerminalNode K_ALTER() { return getToken(SQLiteParser.K_ALTER, 0); }
		public TerminalNode K_ANALYZE() { return getToken(SQLiteParser.K_ANALYZE, 0); }
		public TerminalNode K_AND() { return getToken(SQLiteParser.K_AND, 0); }
		public TerminalNode K_AS() { return getToken(SQLiteParser.K_AS, 0); }
		public TerminalNode K_ASC() { return getToken(SQLiteParser.K_ASC, 0); }
		public TerminalNode K_ATTACH() { return getToken(SQLiteParser.K_ATTACH, 0); }
		public TerminalNode K_AUTOINCREMENT() { return getToken(SQLiteParser.K_AUTOINCREMENT, 0); }
		public TerminalNode K_BEFORE() { return getToken(SQLiteParser.K_BEFORE, 0); }
		public TerminalNode K_BEGIN() { return getToken(SQLiteParser.K_BEGIN, 0); }
		public TerminalNode K_BETWEEN() { return getToken(SQLiteParser.K_BETWEEN, 0); }
		public TerminalNode K_BY() { return getToken(SQLiteParser.K_BY, 0); }
		public TerminalNode K_CASCADE() { return getToken(SQLiteParser.K_CASCADE, 0); }
		public TerminalNode K_CASE() { return getToken(SQLiteParser.K_CASE, 0); }
		public TerminalNode K_CAST() { return getToken(SQLiteParser.K_CAST, 0); }
		public TerminalNode K_CHECK() { return getToken(SQLiteParser.K_CHECK, 0); }
		public TerminalNode K_COLLATE() { return getToken(SQLiteParser.K_COLLATE, 0); }
		public TerminalNode K_COLUMN() { return getToken(SQLiteParser.K_COLUMN, 0); }
		public TerminalNode K_COMMIT() { return getToken(SQLiteParser.K_COMMIT, 0); }
		public TerminalNode K_CONFLICT() { return getToken(SQLiteParser.K_CONFLICT, 0); }
		public TerminalNode K_CONSTRAINT() { return getToken(SQLiteParser.K_CONSTRAINT, 0); }
		public TerminalNode K_CREATE() { return getToken(SQLiteParser.K_CREATE, 0); }
		public TerminalNode K_CROSS() { return getToken(SQLiteParser.K_CROSS, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(SQLiteParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(SQLiteParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(SQLiteParser.K_CURRENT_TIMESTAMP, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLiteParser.K_DATABASE, 0); }
		public TerminalNode K_DEFAULT() { return getToken(SQLiteParser.K_DEFAULT, 0); }
		public TerminalNode K_DEFERRABLE() { return getToken(SQLiteParser.K_DEFERRABLE, 0); }
		public TerminalNode K_DEFERRED() { return getToken(SQLiteParser.K_DEFERRED, 0); }
		public TerminalNode K_DELETE() { return getToken(SQLiteParser.K_DELETE, 0); }
		public TerminalNode K_DESC() { return getToken(SQLiteParser.K_DESC, 0); }
		public TerminalNode K_DETACH() { return getToken(SQLiteParser.K_DETACH, 0); }
		public TerminalNode K_DISTINCT() { return getToken(SQLiteParser.K_DISTINCT, 0); }
		public TerminalNode K_DROP() { return getToken(SQLiteParser.K_DROP, 0); }
		public TerminalNode K_EACH() { return getToken(SQLiteParser.K_EACH, 0); }
		public TerminalNode K_ELSE() { return getToken(SQLiteParser.K_ELSE, 0); }
		public TerminalNode K_END() { return getToken(SQLiteParser.K_END, 0); }
		public TerminalNode K_ESCAPE() { return getToken(SQLiteParser.K_ESCAPE, 0); }
		public TerminalNode K_EXCEPT() { return getToken(SQLiteParser.K_EXCEPT, 0); }
		public TerminalNode K_EXCLUSIVE() { return getToken(SQLiteParser.K_EXCLUSIVE, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLiteParser.K_EXISTS, 0); }
		public TerminalNode K_EXPLAIN() { return getToken(SQLiteParser.K_EXPLAIN, 0); }
		public TerminalNode K_FAIL() { return getToken(SQLiteParser.K_FAIL, 0); }
		public TerminalNode K_FOR() { return getToken(SQLiteParser.K_FOR, 0); }
		public TerminalNode K_FOREIGN() { return getToken(SQLiteParser.K_FOREIGN, 0); }
		public TerminalNode K_FROM() { return getToken(SQLiteParser.K_FROM, 0); }
		public TerminalNode K_FULL() { return getToken(SQLiteParser.K_FULL, 0); }
		public TerminalNode K_GLOB() { return getToken(SQLiteParser.K_GLOB, 0); }
		public TerminalNode K_GROUP() { return getToken(SQLiteParser.K_GROUP, 0); }
		public TerminalNode K_HAVING() { return getToken(SQLiteParser.K_HAVING, 0); }
		public TerminalNode K_IF() { return getToken(SQLiteParser.K_IF, 0); }
		public TerminalNode K_IGNORE() { return getToken(SQLiteParser.K_IGNORE, 0); }
		public TerminalNode K_IMMEDIATE() { return getToken(SQLiteParser.K_IMMEDIATE, 0); }
		public TerminalNode K_IN() { return getToken(SQLiteParser.K_IN, 0); }
		public TerminalNode K_INDEX() { return getToken(SQLiteParser.K_INDEX, 0); }
		public TerminalNode K_INDEXED() { return getToken(SQLiteParser.K_INDEXED, 0); }
		public TerminalNode K_INITIALLY() { return getToken(SQLiteParser.K_INITIALLY, 0); }
		public TerminalNode K_INNER() { return getToken(SQLiteParser.K_INNER, 0); }
		public TerminalNode K_INSERT() { return getToken(SQLiteParser.K_INSERT, 0); }
		public TerminalNode K_INSTEAD() { return getToken(SQLiteParser.K_INSTEAD, 0); }
		public TerminalNode K_INTERSECT() { return getToken(SQLiteParser.K_INTERSECT, 0); }
		public TerminalNode K_INTO() { return getToken(SQLiteParser.K_INTO, 0); }
		public TerminalNode K_IS() { return getToken(SQLiteParser.K_IS, 0); }
		public TerminalNode K_ISNULL() { return getToken(SQLiteParser.K_ISNULL, 0); }
		public TerminalNode K_JOIN() { return getToken(SQLiteParser.K_JOIN, 0); }
		public TerminalNode K_KEY() { return getToken(SQLiteParser.K_KEY, 0); }
		public TerminalNode K_LEFT() { return getToken(SQLiteParser.K_LEFT, 0); }
		public TerminalNode K_LIKE() { return getToken(SQLiteParser.K_LIKE, 0); }
		public TerminalNode K_LIMIT() { return getToken(SQLiteParser.K_LIMIT, 0); }
		public TerminalNode K_MATCH() { return getToken(SQLiteParser.K_MATCH, 0); }
		public TerminalNode K_NATURAL() { return getToken(SQLiteParser.K_NATURAL, 0); }
		public TerminalNode K_NO() { return getToken(SQLiteParser.K_NO, 0); }
		public TerminalNode K_NOT() { return getToken(SQLiteParser.K_NOT, 0); }
		public TerminalNode K_NOTNULL() { return getToken(SQLiteParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLiteParser.K_NULL, 0); }
		public TerminalNode K_OF() { return getToken(SQLiteParser.K_OF, 0); }
		public TerminalNode K_OFFSET() { return getToken(SQLiteParser.K_OFFSET, 0); }
		public TerminalNode K_ON() { return getToken(SQLiteParser.K_ON, 0); }
		public TerminalNode K_OR() { return getToken(SQLiteParser.K_OR, 0); }
		public TerminalNode K_ORDER() { return getToken(SQLiteParser.K_ORDER, 0); }
		public TerminalNode K_OUTER() { return getToken(SQLiteParser.K_OUTER, 0); }
		public TerminalNode K_PLAN() { return getToken(SQLiteParser.K_PLAN, 0); }
		public TerminalNode K_PRAGMA() { return getToken(SQLiteParser.K_PRAGMA, 0); }
		public TerminalNode K_PRIMARY() { return getToken(SQLiteParser.K_PRIMARY, 0); }
		public TerminalNode K_QUERY() { return getToken(SQLiteParser.K_QUERY, 0); }
		public TerminalNode K_RAISE() { return getToken(SQLiteParser.K_RAISE, 0); }
		public TerminalNode K_RECURSIVE() { return getToken(SQLiteParser.K_RECURSIVE, 0); }
		public TerminalNode K_REFERENCES() { return getToken(SQLiteParser.K_REFERENCES, 0); }
		public TerminalNode K_REGEXP() { return getToken(SQLiteParser.K_REGEXP, 0); }
		public TerminalNode K_REINDEX() { return getToken(SQLiteParser.K_REINDEX, 0); }
		public TerminalNode K_RELEASE() { return getToken(SQLiteParser.K_RELEASE, 0); }
		public TerminalNode K_RENAME() { return getToken(SQLiteParser.K_RENAME, 0); }
		public TerminalNode K_REPLACE() { return getToken(SQLiteParser.K_REPLACE, 0); }
		public TerminalNode K_RESTRICT() { return getToken(SQLiteParser.K_RESTRICT, 0); }
		public TerminalNode K_RIGHT() { return getToken(SQLiteParser.K_RIGHT, 0); }
		public TerminalNode K_ROLLBACK() { return getToken(SQLiteParser.K_ROLLBACK, 0); }
		public TerminalNode K_ROW() { return getToken(SQLiteParser.K_ROW, 0); }
		public TerminalNode K_SAVEPOINT() { return getToken(SQLiteParser.K_SAVEPOINT, 0); }
		public TerminalNode K_SELECT() { return getToken(SQLiteParser.K_SELECT, 0); }
		public TerminalNode K_SET() { return getToken(SQLiteParser.K_SET, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLiteParser.K_TABLE, 0); }
		public TerminalNode K_TEMP() { return getToken(SQLiteParser.K_TEMP, 0); }
		public TerminalNode K_TEMPORARY() { return getToken(SQLiteParser.K_TEMPORARY, 0); }
		public TerminalNode K_THEN() { return getToken(SQLiteParser.K_THEN, 0); }
		public TerminalNode K_TO() { return getToken(SQLiteParser.K_TO, 0); }
		public TerminalNode K_TRANSACTION() { return getToken(SQLiteParser.K_TRANSACTION, 0); }
		public TerminalNode K_TRIGGER() { return getToken(SQLiteParser.K_TRIGGER, 0); }
		public TerminalNode K_UNION() { return getToken(SQLiteParser.K_UNION, 0); }
		public TerminalNode K_UNIQUE() { return getToken(SQLiteParser.K_UNIQUE, 0); }
		public TerminalNode K_UPDATE() { return getToken(SQLiteParser.K_UPDATE, 0); }
		public TerminalNode K_USING() { return getToken(SQLiteParser.K_USING, 0); }
		public TerminalNode K_VACUUM() { return getToken(SQLiteParser.K_VACUUM, 0); }
		public TerminalNode K_VALUES() { return getToken(SQLiteParser.K_VALUES, 0); }
		public TerminalNode K_VIEW() { return getToken(SQLiteParser.K_VIEW, 0); }
		public TerminalNode K_VIRTUAL() { return getToken(SQLiteParser.K_VIRTUAL, 0); }
		public TerminalNode K_WHEN() { return getToken(SQLiteParser.K_WHEN, 0); }
		public TerminalNode K_WHERE() { return getToken(SQLiteParser.K_WHERE, 0); }
		public TerminalNode K_WITH() { return getToken(SQLiteParser.K_WITH, 0); }
		public TerminalNode K_WITHOUT() { return getToken(SQLiteParser.K_WITHOUT, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583);
			_la = _input.LA(1);
			if ( !(((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (K_ABORT - 28)) | (1L << (K_ACTION - 28)) | (1L << (K_ADD - 28)) | (1L << (K_AFTER - 28)) | (1L << (K_ALL - 28)) | (1L << (K_ALTER - 28)) | (1L << (K_ANALYZE - 28)) | (1L << (K_AND - 28)) | (1L << (K_AS - 28)) | (1L << (K_ASC - 28)) | (1L << (K_ATTACH - 28)) | (1L << (K_AUTOINCREMENT - 28)) | (1L << (K_BEFORE - 28)) | (1L << (K_BEGIN - 28)) | (1L << (K_BETWEEN - 28)) | (1L << (K_BY - 28)) | (1L << (K_CASCADE - 28)) | (1L << (K_CASE - 28)) | (1L << (K_CAST - 28)) | (1L << (K_CHECK - 28)) | (1L << (K_COLLATE - 28)) | (1L << (K_COLUMN - 28)) | (1L << (K_COMMIT - 28)) | (1L << (K_CONFLICT - 28)) | (1L << (K_CONSTRAINT - 28)) | (1L << (K_CREATE - 28)) | (1L << (K_CROSS - 28)) | (1L << (K_CURRENT_DATE - 28)) | (1L << (K_CURRENT_TIME - 28)) | (1L << (K_CURRENT_TIMESTAMP - 28)) | (1L << (K_DATABASE - 28)) | (1L << (K_DEFAULT - 28)) | (1L << (K_DEFERRABLE - 28)) | (1L << (K_DEFERRED - 28)) | (1L << (K_DELETE - 28)) | (1L << (K_DESC - 28)) | (1L << (K_DETACH - 28)) | (1L << (K_DISTINCT - 28)) | (1L << (K_DROP - 28)) | (1L << (K_EACH - 28)) | (1L << (K_ELSE - 28)) | (1L << (K_END - 28)) | (1L << (K_ESCAPE - 28)) | (1L << (K_EXCEPT - 28)) | (1L << (K_EXCLUSIVE - 28)) | (1L << (K_EXISTS - 28)) | (1L << (K_EXPLAIN - 28)) | (1L << (K_FAIL - 28)) | (1L << (K_FOR - 28)) | (1L << (K_FOREIGN - 28)) | (1L << (K_FROM - 28)) | (1L << (K_FULL - 28)) | (1L << (K_GLOB - 28)) | (1L << (K_GROUP - 28)) | (1L << (K_HAVING - 28)) | (1L << (K_IF - 28)) | (1L << (K_IGNORE - 28)) | (1L << (K_IMMEDIATE - 28)) | (1L << (K_IN - 28)) | (1L << (K_INDEX - 28)) | (1L << (K_INDEXED - 28)) | (1L << (K_INITIALLY - 28)) | (1L << (K_INNER - 28)) | (1L << (K_INSERT - 28)))) != 0) || ((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (K_INSTEAD - 92)) | (1L << (K_INTERSECT - 92)) | (1L << (K_INTO - 92)) | (1L << (K_IS - 92)) | (1L << (K_ISNULL - 92)) | (1L << (K_JOIN - 92)) | (1L << (K_KEY - 92)) | (1L << (K_LEFT - 92)) | (1L << (K_LIKE - 92)) | (1L << (K_LIMIT - 92)) | (1L << (K_MATCH - 92)) | (1L << (K_NATURAL - 92)) | (1L << (K_NO - 92)) | (1L << (K_NOT - 92)) | (1L << (K_NOTNULL - 92)) | (1L << (K_NULL - 92)) | (1L << (K_OF - 92)) | (1L << (K_OFFSET - 92)) | (1L << (K_ON - 92)) | (1L << (K_OR - 92)) | (1L << (K_ORDER - 92)) | (1L << (K_OUTER - 92)) | (1L << (K_PLAN - 92)) | (1L << (K_PRAGMA - 92)) | (1L << (K_PRIMARY - 92)) | (1L << (K_QUERY - 92)) | (1L << (K_RAISE - 92)) | (1L << (K_RECURSIVE - 92)) | (1L << (K_REFERENCES - 92)) | (1L << (K_REGEXP - 92)) | (1L << (K_REINDEX - 92)) | (1L << (K_RELEASE - 92)) | (1L << (K_RENAME - 92)) | (1L << (K_REPLACE - 92)) | (1L << (K_RESTRICT - 92)) | (1L << (K_RIGHT - 92)) | (1L << (K_ROLLBACK - 92)) | (1L << (K_ROW - 92)) | (1L << (K_SAVEPOINT - 92)) | (1L << (K_SELECT - 92)) | (1L << (K_SET - 92)) | (1L << (K_TABLE - 92)) | (1L << (K_TEMP - 92)) | (1L << (K_TEMPORARY - 92)) | (1L << (K_THEN - 92)) | (1L << (K_TO - 92)) | (1L << (K_TRANSACTION - 92)) | (1L << (K_TRIGGER - 92)) | (1L << (K_UNION - 92)) | (1L << (K_UNIQUE - 92)) | (1L << (K_UPDATE - 92)) | (1L << (K_USING - 92)) | (1L << (K_VACUUM - 92)) | (1L << (K_VALUES - 92)) | (1L << (K_VIEW - 92)) | (1L << (K_VIRTUAL - 92)) | (1L << (K_WHEN - 92)) | (1L << (K_WHERE - 92)) | (1L << (K_WITH - 92)) | (1L << (K_WITHOUT - 92)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Database_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Database_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitDatabase_name(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(593);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collation_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Collation_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collation_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterCollation_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitCollation_name(this);
		}
	}

	public final Collation_nameContext collation_name() throws RecognitionException {
		Collation_nameContext _localctx = new Collation_nameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_collation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_aliasContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitTable_alias(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLiteParser.IDENTIFIER, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SQLiteParser.STRING_LITERAL, 0); }
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitAny_name(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_any_name);
		try {
			setState(608);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(601);
				match(IDENTIFIER);
				}
				break;
			case K_ABORT:
			case K_ACTION:
			case K_ADD:
			case K_AFTER:
			case K_ALL:
			case K_ALTER:
			case K_ANALYZE:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_ATTACH:
			case K_AUTOINCREMENT:
			case K_BEFORE:
			case K_BEGIN:
			case K_BETWEEN:
			case K_BY:
			case K_CASCADE:
			case K_CASE:
			case K_CAST:
			case K_CHECK:
			case K_COLLATE:
			case K_COLUMN:
			case K_COMMIT:
			case K_CONFLICT:
			case K_CONSTRAINT:
			case K_CREATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DATABASE:
			case K_DEFAULT:
			case K_DEFERRABLE:
			case K_DEFERRED:
			case K_DELETE:
			case K_DESC:
			case K_DETACH:
			case K_DISTINCT:
			case K_DROP:
			case K_EACH:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXCLUSIVE:
			case K_EXISTS:
			case K_EXPLAIN:
			case K_FAIL:
			case K_FOR:
			case K_FOREIGN:
			case K_FROM:
			case K_FULL:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IMMEDIATE:
			case K_IN:
			case K_INDEX:
			case K_INDEXED:
			case K_INITIALLY:
			case K_INNER:
			case K_INSERT:
			case K_INSTEAD:
			case K_INTERSECT:
			case K_INTO:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_KEY:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OF:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_PLAN:
			case K_PRAGMA:
			case K_PRIMARY:
			case K_QUERY:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REFERENCES:
			case K_REGEXP:
			case K_REINDEX:
			case K_RELEASE:
			case K_RENAME:
			case K_REPLACE:
			case K_RESTRICT:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_ROW:
			case K_SAVEPOINT:
			case K_SELECT:
			case K_SET:
			case K_TABLE:
			case K_TEMP:
			case K_TEMPORARY:
			case K_THEN:
			case K_TO:
			case K_TRANSACTION:
			case K_TRIGGER:
			case K_UNION:
			case K_UNIQUE:
			case K_UPDATE:
			case K_USING:
			case K_VACUUM:
			case K_VALUES:
			case K_VIEW:
			case K_VIRTUAL:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_WITHOUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(602);
				keyword();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(603);
				match(STRING_LITERAL);
				}
				break;
			case OPEN_PAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(604);
				match(OPEN_PAR);
				setState(605);
				any_name();
				setState(606);
				match(CLOSE_PAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bind_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLiteParser.IDENTIFIER, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public Bind_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bind_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterBind_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitBind_name(this);
		}
	}

	public final Bind_nameContext bind_name() throws RecognitionException {
		Bind_nameContext _localctx = new Bind_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_bind_name);
		try {
			setState(612);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(610);
				match(IDENTIFIER);
				}
				break;
			case K_ABORT:
			case K_ACTION:
			case K_ADD:
			case K_AFTER:
			case K_ALL:
			case K_ALTER:
			case K_ANALYZE:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_ATTACH:
			case K_AUTOINCREMENT:
			case K_BEFORE:
			case K_BEGIN:
			case K_BETWEEN:
			case K_BY:
			case K_CASCADE:
			case K_CASE:
			case K_CAST:
			case K_CHECK:
			case K_COLLATE:
			case K_COLUMN:
			case K_COMMIT:
			case K_CONFLICT:
			case K_CONSTRAINT:
			case K_CREATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DATABASE:
			case K_DEFAULT:
			case K_DEFERRABLE:
			case K_DEFERRED:
			case K_DELETE:
			case K_DESC:
			case K_DETACH:
			case K_DISTINCT:
			case K_DROP:
			case K_EACH:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXCLUSIVE:
			case K_EXISTS:
			case K_EXPLAIN:
			case K_FAIL:
			case K_FOR:
			case K_FOREIGN:
			case K_FROM:
			case K_FULL:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IMMEDIATE:
			case K_IN:
			case K_INDEX:
			case K_INDEXED:
			case K_INITIALLY:
			case K_INNER:
			case K_INSERT:
			case K_INSTEAD:
			case K_INTERSECT:
			case K_INTO:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_KEY:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OF:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_PLAN:
			case K_PRAGMA:
			case K_PRIMARY:
			case K_QUERY:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REFERENCES:
			case K_REGEXP:
			case K_REINDEX:
			case K_RELEASE:
			case K_RENAME:
			case K_REPLACE:
			case K_RESTRICT:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_ROW:
			case K_SAVEPOINT:
			case K_SELECT:
			case K_SET:
			case K_TABLE:
			case K_TEMP:
			case K_TEMPORARY:
			case K_THEN:
			case K_TO:
			case K_TRANSACTION:
			case K_TRIGGER:
			case K_UNION:
			case K_UNIQUE:
			case K_UPDATE:
			case K_USING:
			case K_VACUUM:
			case K_VALUES:
			case K_VIEW:
			case K_VIRTUAL:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_WITHOUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(611);
				keyword();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bind_parameterContext extends ParserRuleContext {
		public Bind_nameContext bind_name() {
			return getRuleContext(Bind_nameContext.class,0);
		}
		public Bind_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bind_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).enterBind_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLiteListener ) ((SQLiteListener)listener).exitBind_parameter(this);
		}
	}

	public final Bind_parameterContext bind_parameter() throws RecognitionException {
		Bind_parameterContext _localctx = new Bind_parameterContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_bind_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(615);
			bind_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 20);
		case 1:
			return precpred(_ctx, 19);
		case 2:
			return precpred(_ctx, 18);
		case 3:
			return precpred(_ctx, 17);
		case 4:
			return precpred(_ctx, 16);
		case 5:
			return precpred(_ctx, 15);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 9);
		case 11:
			return precpred(_ctx, 8);
		case 12:
			return precpred(_ctx, 7);
		case 13:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\u009f\u026c\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\3\2\3\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\5\2P\n\2\5"+
		"\2R\n\2\3\2\3\2\3\2\3\2\3\2\7\2Y\n\2\f\2\16\2\\\13\2\5\2^\n\2\3\2\3\2"+
		"\3\2\3\2\5\2d\n\2\5\2f\n\2\3\2\3\2\3\3\3\3\5\3l\n\3\3\3\3\3\3\3\7\3q\n"+
		"\3\f\3\16\3t\13\3\5\3v\n\3\3\3\3\3\3\3\3\3\7\3|\n\3\f\3\16\3\177\13\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3\u0086\n\3\f\3\16\3\u0089\13\3\5\3\u008b\n\3\3"+
		"\3\3\3\3\3\3\3\5\3\u0091\n\3\5\3\u0093\n\3\3\4\3\4\5\4\u0097\n\4\3\4\3"+
		"\4\3\4\7\4\u009c\n\4\f\4\16\4\u009f\13\4\3\4\3\4\3\4\3\4\7\4\u00a5\n\4"+
		"\f\4\16\4\u00a8\13\4\3\4\5\4\u00ab\n\4\5\4\u00ad\n\4\3\4\3\4\5\4\u00b1"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\7\4\u00b8\n\4\f\4\16\4\u00bb\13\4\3\4\3\4\5\4"+
		"\u00bf\n\4\5\4\u00c1\n\4\3\4\3\4\3\4\3\4\3\4\7\4\u00c8\n\4\f\4\16\4\u00cb"+
		"\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00d3\n\4\f\4\16\4\u00d6\13\4\3\4\3"+
		"\4\7\4\u00da\n\4\f\4\16\4\u00dd\13\4\5\4\u00df\n\4\3\5\6\5\u00e2\n\5\r"+
		"\5\16\5\u00e3\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00f0\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00fc\n\6\3\6\3\6\3\6\7\6\u0101"+
		"\n\6\f\6\16\6\u0104\13\6\3\6\5\6\u0107\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0117\n\6\3\6\5\6\u011a\n\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\5\6\u0122\n\6\3\6\3\6\3\6\3\6\3\6\6\6\u0129\n\6\r\6\16"+
		"\6\u012a\3\6\3\6\5\6\u012f\n\6\3\6\3\6\3\6\5\6\u0134\n\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0152\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6\u015e\n\6\3\6\3\6\3\6\5\6\u0163\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6\u016f\n\6\3\6\3\6\3\6\3\6\5\6\u0175\n\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6\u017c\n\6\3\6\3\6\5\6\u0180\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6\u0188\n\6\f\6\16\6\u018b\13\6\5\6\u018d\n\6\3\6\3\6\3\6\3\6"+
		"\5\6\u0193\n\6\3\6\5\6\u0196\n\6\7\6\u0198\n\6\f\6\16\6\u019b\13\6\3\7"+
		"\3\7\3\7\5\7\u01a0\n\7\3\7\3\7\3\7\5\7\u01a5\n\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\5\b\u01af\n\b\3\b\3\b\3\t\3\t\3\t\5\t\u01b6\n\t\3\t\5\t\u01b9"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\7\n\u01c0\n\n\f\n\16\n\u01c3\13\n\3\n\3\n\5\n"+
		"\u01c7\n\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u01d5\n\13\3\13\5\13\u01d8\n\13\5\13\u01da\n\13\3\f\3\f\3\f\5\f\u01df"+
		"\n\f\3\f\3\f\5\f\u01e3\n\f\3\f\5\f\u01e6\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u01ed"+
		"\n\f\3\f\3\f\3\f\3\f\7\f\u01f3\n\f\f\f\16\f\u01f6\13\f\3\f\5\f\u01f9\n"+
		"\f\3\f\3\f\5\f\u01fd\n\f\3\f\5\f\u0200\n\f\3\f\3\f\3\f\3\f\5\f\u0206\n"+
		"\f\3\f\5\f\u0209\n\f\5\f\u020b\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u0212\n\r\f"+
		"\r\16\r\u0215\13\r\3\16\3\16\5\16\u0219\n\16\3\16\3\16\5\16\u021d\n\16"+
		"\3\16\3\16\5\16\u0221\n\16\3\16\5\16\u0224\n\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u022d\n\17\f\17\16\17\u0230\13\17\3\17\3\17\5\17\u0234"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\5\20\u023b\n\20\3\21\5\21\u023e\n\21\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0263\n\37\3 \3 \5 \u0267\n \3"+
		"!\3!\3!\3!\2\3\n\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@\2\20\4\2\n\noo\4\2\"\"CC\4\2\f\f\21\22\3\2\r\16\3\2\23"+
		"\26\3\2\27\32\6\2RRffhh{{\5\2\36\36MM\u0082\u0082\4\2\'\'AA\5\29;mm\u009b"+
		"\u009d\4\2\r\17kk\4\2\u009a\u009a\u009c\u009c\3\2\36\u0099\3\2\3\5\u02ca"+
		"\2B\3\2\2\2\4u\3\2\2\2\6\u00de\3\2\2\2\b\u00e1\3\2\2\2\n\u0133\3\2\2\2"+
		"\f\u01a4\3\2\2\2\16\u01a8\3\2\2\2\20\u01b2\3\2\2\2\22\u01ba\3\2\2\2\24"+
		"\u01d9\3\2\2\2\26\u020a\3\2\2\2\30\u020c\3\2\2\2\32\u0223\3\2\2\2\34\u0233"+
		"\3\2\2\2\36\u023a\3\2\2\2 \u023d\3\2\2\2\"\u0241\3\2\2\2$\u0243\3\2\2"+
		"\2&\u0245\3\2\2\2(\u0247\3\2\2\2*\u0249\3\2\2\2,\u024b\3\2\2\2.\u024d"+
		"\3\2\2\2\60\u024f\3\2\2\2\62\u0251\3\2\2\2\64\u0253\3\2\2\2\66\u0255\3"+
		"\2\2\28\u0257\3\2\2\2:\u0259\3\2\2\2<\u0262\3\2\2\2>\u0266\3\2\2\2@\u0268"+
		"\3\2\2\2BQ\5\n\6\2CD\7S\2\2DE\7-\2\2EJ\5\n\6\2FG\7\n\2\2GI\5\n\6\2HF\3"+
		"\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KO\3\2\2\2LJ\3\2\2\2MN\7T\2\2NP\5"+
		"\n\6\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QC\3\2\2\2QR\3\2\2\2R]\3\2\2\2ST\7"+
		"r\2\2TU\7-\2\2UZ\5\20\t\2VW\7\n\2\2WY\5\20\t\2XV\3\2\2\2Y\\\3\2\2\2ZX"+
		"\3\2\2\2Z[\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2]S\3\2\2\2]^\3\2\2\2^e\3\2\2\2"+
		"_`\7g\2\2`c\5\n\6\2ab\t\2\2\2bd\5\n\6\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2"+
		"e_\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\7\2\2\3h\3\3\2\2\2ik\7\u0098\2\2jl\7"+
		"y\2\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mr\5\22\n\2no\7\n\2\2oq\5\22\n\2pn"+
		"\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2sv\3\2\2\2tr\3\2\2\2ui\3\2\2\2u"+
		"v\3\2\2\2vw\3\2\2\2w}\5\6\4\2xy\5\36\20\2yz\5\6\4\2z|\3\2\2\2{x\3\2\2"+
		"\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u008a\3\2\2\2\177}\3\2\2\2\u0080"+
		"\u0081\7r\2\2\u0081\u0082\7-\2\2\u0082\u0087\5\20\t\2\u0083\u0084\7\n"+
		"\2\2\u0084\u0086\5\20\t\2\u0085\u0083\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u008a\u0080\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0092\3\2\2\2\u008c"+
		"\u008d\7g\2\2\u008d\u0090\5\n\6\2\u008e\u008f\t\2\2\2\u008f\u0091\5\n"+
		"\6\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092"+
		"\u008c\3\2\2\2\u0092\u0093\3\2\2\2\u0093\5\3\2\2\2\u0094\u0096\7\u0085"+
		"\2\2\u0095\u0097\t\3\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u009d\5\24\13\2\u0099\u009a\7\n\2\2\u009a\u009c\5"+
		"\24\13\2\u009b\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00ac\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00aa\7P"+
		"\2\2\u00a1\u00a6\5\26\f\2\u00a2\u00a3\7\n\2\2\u00a3\u00a5\5\26\f\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00ab\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\5\30\r\2\u00aa"+
		"\u00a1\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a0\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00af\7\u0097\2\2\u00af"+
		"\u00b1\5\n\6\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00c0\3\2"+
		"\2\2\u00b2\u00b3\7S\2\2\u00b3\u00b4\7-\2\2\u00b4\u00b9\5\n\6\2\u00b5\u00b6"+
		"\7\n\2\2\u00b6\u00b8\5\n\6\2\u00b7\u00b5\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00be\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bc\u00bd\7T\2\2\u00bd\u00bf\5\n\6\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00b2\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00df\3\2\2\2\u00c2\u00c3\7\u0093\2\2\u00c3\u00c4\7\b\2\2\u00c4"+
		"\u00c9\5\n\6\2\u00c5\u00c6\7\n\2\2\u00c6\u00c8\5\n\6\2\u00c7\u00c5\3\2"+
		"\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00db\7\t\2\2\u00cd\u00ce\7\n"+
		"\2\2\u00ce\u00cf\7\b\2\2\u00cf\u00d4\5\n\6\2\u00d0\u00d1\7\n\2\2\u00d1"+
		"\u00d3\5\n\6\2\u00d2\u00d0\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00d8\7\t\2\2\u00d8\u00da\3\2\2\2\u00d9\u00cd\3\2\2\2\u00da\u00dd\3\2"+
		"\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u0094\3\2\2\2\u00de\u00c2\3\2\2\2\u00df\7\3\2\2\2"+
		"\u00e0\u00e2\5,\27\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e1"+
		"\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00ef\3\2\2\2\u00e5\u00e6\7\b\2\2\u00e6"+
		"\u00e7\5 \21\2\u00e7\u00e8\7\t\2\2\u00e8\u00f0\3\2\2\2\u00e9\u00ea\7\b"+
		"\2\2\u00ea\u00eb\5 \21\2\u00eb\u00ec\7\n\2\2\u00ec\u00ed\5 \21\2\u00ed"+
		"\u00ee\7\t\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00e5\3\2\2\2\u00ef\u00e9\3\2"+
		"\2\2\u00ef\u00f0\3\2\2\2\u00f0\t\3\2\2\2\u00f1\u00f2\b\6\1\2\u00f2\u00f3"+
		"\5$\23\2\u00f3\u00f4\5\n\6\27\u00f4\u0134\3\2\2\2\u00f5\u0134\5\"\22\2"+
		"\u00f6\u0134\5@!\2\u00f7\u0134\5\f\7\2\u00f8\u00f9\5.\30\2\u00f9\u0106"+
		"\7\b\2\2\u00fa\u00fc\7C\2\2\u00fb\u00fa\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u0102\5\n\6\2\u00fe\u00ff\7\n\2\2\u00ff\u0101\5\n"+
		"\6\2\u0100\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\u0107\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0107\7\f"+
		"\2\2\u0106\u00fb\3\2\2\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0109\7\t\2\2\u0109\u0134\3\2\2\2\u010a\u010b\7\b"+
		"\2\2\u010b\u010c\5\n\6\2\u010c\u010d\7\t\2\2\u010d\u0134\3\2\2\2\u010e"+
		"\u010f\7\60\2\2\u010f\u0110\7\b\2\2\u0110\u0111\5\n\6\2\u0111\u0112\7"+
		"&\2\2\u0112\u0113\5\b\5\2\u0113\u0114\7\t\2\2\u0114\u0134\3\2\2\2\u0115"+
		"\u0117\7k\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u011a\7K\2\2\u0119\u0116\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u011c\7\b\2\2\u011c\u011d\5\4\3\2\u011d\u011e\7\t"+
		"\2\2\u011e\u0134\3\2\2\2\u011f\u0121\7/\2\2\u0120\u0122\5\n\6\2\u0121"+
		"\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0128\3\2\2\2\u0123\u0124\7\u0096"+
		"\2\2\u0124\u0125\5\n\6\2\u0125\u0126\7\u008a\2\2\u0126\u0127\5\n\6\2\u0127"+
		"\u0129\3\2\2\2\u0128\u0123\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012d\7F\2\2\u012d"+
		"\u012f\5\n\6\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2"+
		"\2\2\u0130\u0131\7G\2\2\u0131\u0134\3\2\2\2\u0132\u0134\5\16\b\2\u0133"+
		"\u00f1\3\2\2\2\u0133\u00f5\3\2\2\2\u0133\u00f6\3\2\2\2\u0133\u00f7\3\2"+
		"\2\2\u0133\u00f8\3\2\2\2\u0133\u010a\3\2\2\2\u0133\u010e\3\2\2\2\u0133"+
		"\u0119\3\2\2\2\u0133\u011f\3\2\2\2\u0133\u0132\3\2\2\2\u0134\u0199\3\2"+
		"\2\2\u0135\u0136\f\26\2\2\u0136\u0137\7\20\2\2\u0137\u0198\5\n\6\27\u0138"+
		"\u0139\f\25\2\2\u0139\u013a\t\4\2\2\u013a\u0198\5\n\6\26\u013b\u013c\f"+
		"\24\2\2\u013c\u013d\t\5\2\2\u013d\u0198\5\n\6\25\u013e\u013f\f\23\2\2"+
		"\u013f\u0140\t\6\2\2\u0140\u0198\5\n\6\24\u0141\u0142\f\22\2\2\u0142\u0143"+
		"\t\7\2\2\u0143\u0198\5\n\6\23\u0144\u0151\f\21\2\2\u0145\u0152\7\13\2"+
		"\2\u0146\u0152\7\33\2\2\u0147\u0152\7\34\2\2\u0148\u0152\7\35\2\2\u0149"+
		"\u0152\7a\2\2\u014a\u014b\7a\2\2\u014b\u0152\7k\2\2\u014c\u0152\7X\2\2"+
		"\u014d\u0152\7f\2\2\u014e\u0152\7R\2\2\u014f\u0152\7h\2\2\u0150\u0152"+
		"\7{\2\2\u0151\u0145\3\2\2\2\u0151\u0146\3\2\2\2\u0151\u0147\3\2\2\2\u0151"+
		"\u0148\3\2\2\2\u0151\u0149\3\2\2\2\u0151\u014a\3\2\2\2\u0151\u014c\3\2"+
		"\2\2\u0151\u014d\3\2\2\2\u0151\u014e\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0198\5\n\6\22\u0154\u0155\f"+
		"\20\2\2\u0155\u0156\7%\2\2\u0156\u0198\5\n\6\21\u0157\u0158\f\17\2\2\u0158"+
		"\u0159\7q\2\2\u0159\u0198\5\n\6\20\u015a\u015b\f\b\2\2\u015b\u015d\7a"+
		"\2\2\u015c\u015e\7k\2\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e"+
		"\u015f\3\2\2\2\u015f\u0198\5\n\6\t\u0160\u0162\f\7\2\2\u0161\u0163\7k"+
		"\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u0165\7,\2\2\u0165\u0166\5\n\6\2\u0166\u0167\7%\2\2\u0167\u0168\5\n\6"+
		"\b\u0168\u0198\3\2\2\2\u0169\u016a\f\13\2\2\u016a\u016b\7\62\2\2\u016b"+
		"\u0198\5\66\34\2\u016c\u016e\f\n\2\2\u016d\u016f\7k\2\2\u016e\u016d\3"+
		"\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\t\b\2\2\u0171"+
		"\u0174\5\n\6\2\u0172\u0173\7H\2\2\u0173\u0175\5\n\6\2\u0174\u0172\3\2"+
		"\2\2\u0174\u0175\3\2\2\2\u0175\u0198\3\2\2\2\u0176\u017b\f\t\2\2\u0177"+
		"\u017c\7b\2\2\u0178\u017c\7l\2\2\u0179\u017a\7k\2\2\u017a\u017c\7m\2\2"+
		"\u017b\u0177\3\2\2\2\u017b\u0178\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u0198"+
		"\3\2\2\2\u017d\u017f\f\6\2\2\u017e\u0180\7k\2\2\u017f\u017e\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0195\7X\2\2\u0182\u018c\7\b"+
		"\2\2\u0183\u018d\5\4\3\2\u0184\u0189\5\n\6\2\u0185\u0186\7\n\2\2\u0186"+
		"\u0188\5\n\6\2\u0187\u0185\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2"+
		"\2\2\u0189\u018a\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018c"+
		"\u0183\3\2\2\2\u018c\u0184\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\3\2"+
		"\2\2\u018e\u0196\7\t\2\2\u018f\u0190\5\60\31\2\u0190\u0191\7\7\2\2\u0191"+
		"\u0193\3\2\2\2\u0192\u018f\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\3\2"+
		"\2\2\u0194\u0196\5\62\32\2\u0195\u0182\3\2\2\2\u0195\u0192\3\2\2\2\u0196"+
		"\u0198\3\2\2\2\u0197\u0135\3\2\2\2\u0197\u0138\3\2\2\2\u0197\u013b\3\2"+
		"\2\2\u0197\u013e\3\2\2\2\u0197\u0141\3\2\2\2\u0197\u0144\3\2\2\2\u0197"+
		"\u0154\3\2\2\2\u0197\u0157\3\2\2\2\u0197\u015a\3\2\2\2\u0197\u0160\3\2"+
		"\2\2\u0197\u0169\3\2\2\2\u0197\u016c\3\2\2\2\u0197\u0176\3\2\2\2\u0197"+
		"\u017d\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a\3\2"+
		"\2\2\u019a\13\3\2\2\2\u019b\u0199\3\2\2\2\u019c\u019d\5\60\31\2\u019d"+
		"\u019e\7\7\2\2\u019e\u01a0\3\2\2\2\u019f\u019c\3\2\2\2\u019f\u01a0\3\2"+
		"\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\5\62\32\2\u01a2\u01a3\7\7\2\2\u01a3"+
		"\u01a5\3\2\2\2\u01a4\u019f\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\u01a7\5\64\33\2\u01a7\r\3\2\2\2\u01a8\u01a9\7x\2\2\u01a9\u01ae"+
		"\7\b\2\2\u01aa\u01af\7V\2\2\u01ab\u01ac\t\t\2\2\u01ac\u01ad\7\n\2\2\u01ad"+
		"\u01af\5&\24\2\u01ae\u01aa\3\2\2\2\u01ae\u01ab\3\2\2\2\u01af\u01b0\3\2"+
		"\2\2\u01b0\u01b1\7\t\2\2\u01b1\17\3\2\2\2\u01b2\u01b5\5\n\6\2\u01b3\u01b4"+
		"\7\62\2\2\u01b4\u01b6\5\66\34\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2"+
		"\2\u01b6\u01b8\3\2\2\2\u01b7\u01b9\t\n\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9"+
		"\3\2\2\2\u01b9\21\3\2\2\2\u01ba\u01c6\5\62\32\2\u01bb\u01bc\7\b\2\2\u01bc"+
		"\u01c1\5\64\33\2\u01bd\u01be\7\n\2\2\u01be\u01c0\5\64\33\2\u01bf\u01bd"+
		"\3\2\2\2\u01c0\u01c3\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2"+
		"\u01c4\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c4\u01c5\7\t\2\2\u01c5\u01c7\3\2"+
		"\2\2\u01c6\u01bb\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8"+
		"\u01c9\7&\2\2\u01c9\u01ca\7\b\2\2\u01ca\u01cb\5\4\3\2\u01cb\u01cc\7\t"+
		"\2\2\u01cc\23\3\2\2\2\u01cd\u01da\7\f\2\2\u01ce\u01cf\5\62\32\2\u01cf"+
		"\u01d0\7\7\2\2\u01d0\u01d1\7\f\2\2\u01d1\u01da\3\2\2\2\u01d2\u01d7\5\n"+
		"\6\2\u01d3\u01d5\7&\2\2\u01d4\u01d3\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5"+
		"\u01d6\3\2\2\2\u01d6\u01d8\5(\25\2\u01d7\u01d4\3\2\2\2\u01d7\u01d8\3\2"+
		"\2\2\u01d8\u01da\3\2\2\2\u01d9\u01cd\3\2\2\2\u01d9\u01ce\3\2\2\2\u01d9"+
		"\u01d2\3\2\2\2\u01da\25\3\2\2\2\u01db\u01dc\5\60\31\2\u01dc\u01dd\7\7"+
		"\2\2\u01dd\u01df\3\2\2\2\u01de\u01db\3\2\2\2\u01de\u01df\3\2\2\2\u01df"+
		"\u01e0\3\2\2\2\u01e0\u01e5\5\62\32\2\u01e1\u01e3\7&\2\2\u01e2\u01e1\3"+
		"\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6\5:\36\2\u01e5"+
		"\u01e2\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01ec\3\2\2\2\u01e7\u01e8\7Z"+
		"\2\2\u01e8\u01e9\7-\2\2\u01e9\u01ed\58\35\2\u01ea\u01eb\7k\2\2\u01eb\u01ed"+
		"\7Z\2\2\u01ec\u01e7\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"\u020b\3\2\2\2\u01ee\u01f8\7\b\2\2\u01ef\u01f4\5\26\f\2\u01f0\u01f1\7"+
		"\n\2\2\u01f1\u01f3\5\26\f\2\u01f2\u01f0\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4"+
		"\u01f2\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f9\3\2\2\2\u01f6\u01f4\3\2"+
		"\2\2\u01f7\u01f9\5\30\r\2\u01f8\u01ef\3\2\2\2\u01f8\u01f7\3\2\2\2\u01f9"+
		"\u01fa\3\2\2\2\u01fa\u01ff\7\t\2\2\u01fb\u01fd\7&\2\2\u01fc\u01fb\3\2"+
		"\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u0200\5:\36\2\u01ff"+
		"\u01fc\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u020b\3\2\2\2\u0201\u0202\7\b"+
		"\2\2\u0202\u0203\5\4\3\2\u0203\u0208\7\t\2\2\u0204\u0206\7&\2\2\u0205"+
		"\u0204\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0209\5:"+
		"\36\2\u0208\u0205\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020b\3\2\2\2\u020a"+
		"\u01de\3\2\2\2\u020a\u01ee\3\2\2\2\u020a\u0201\3\2\2\2\u020b\27\3\2\2"+
		"\2\u020c\u0213\5\26\f\2\u020d\u020e\5\32\16\2\u020e\u020f\5\26\f\2\u020f"+
		"\u0210\5\34\17\2\u0210\u0212\3\2\2\2\u0211\u020d\3\2\2\2\u0212\u0215\3"+
		"\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\31\3\2\2\2\u0215"+
		"\u0213\3\2\2\2\u0216\u0224\7\n\2\2\u0217\u0219\7i\2\2\u0218\u0217\3\2"+
		"\2\2\u0218\u0219\3\2\2\2\u0219\u0220\3\2\2\2\u021a\u021c\7e\2\2\u021b"+
		"\u021d\7s\2\2\u021c\u021b\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u0221\3\2"+
		"\2\2\u021e\u0221\7\\\2\2\u021f\u0221\78\2\2\u0220\u021a\3\2\2\2\u0220"+
		"\u021e\3\2\2\2\u0220\u021f\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u0222\3\2"+
		"\2\2\u0222\u0224\7c\2\2\u0223\u0216\3\2\2\2\u0223\u0218\3\2\2\2\u0224"+
		"\33\3\2\2\2\u0225\u0226\7p\2\2\u0226\u0234\5\n\6\2\u0227\u0228\7\u0091"+
		"\2\2\u0228\u0229\7\b\2\2\u0229\u022e\5\64\33\2\u022a\u022b\7\n\2\2\u022b"+
		"\u022d\5\64\33\2\u022c\u022a\3\2\2\2\u022d\u0230\3\2\2\2\u022e\u022c\3"+
		"\2\2\2\u022e\u022f\3\2\2\2\u022f\u0231\3\2\2\2\u0230\u022e\3\2\2\2\u0231"+
		"\u0232\7\t\2\2\u0232\u0234\3\2\2\2\u0233\u0225\3\2\2\2\u0233\u0227\3\2"+
		"\2\2\u0233\u0234\3\2\2\2\u0234\35\3\2\2\2\u0235\u023b\7\u008e\2\2\u0236"+
		"\u0237\7\u008e\2\2\u0237\u023b\7\"\2\2\u0238\u023b\7_\2\2\u0239\u023b"+
		"\7I\2\2\u023a\u0235\3\2\2\2\u023a\u0236\3\2\2\2\u023a\u0238\3\2\2\2\u023a"+
		"\u0239\3\2\2\2\u023b\37\3\2\2\2\u023c\u023e\t\5\2\2\u023d\u023c\3\2\2"+
		"\2\u023d\u023e\3\2\2\2\u023e\u023f\3\2\2\2\u023f\u0240\7\u009b\2\2\u0240"+
		"!\3\2\2\2\u0241\u0242\t\13\2\2\u0242#\3\2\2\2\u0243\u0244\t\f\2\2\u0244"+
		"%\3\2\2\2\u0245\u0246\7\u009c\2\2\u0246\'\3\2\2\2\u0247\u0248\t\r\2\2"+
		"\u0248)\3\2\2\2\u0249\u024a\t\16\2\2\u024a+\3\2\2\2\u024b\u024c\5<\37"+
		"\2\u024c-\3\2\2\2\u024d\u024e\5<\37\2\u024e/\3\2\2\2\u024f\u0250\5<\37"+
		"\2\u0250\61\3\2\2\2\u0251\u0252\5<\37\2\u0252\63\3\2\2\2\u0253\u0254\5"+
		"<\37\2\u0254\65\3\2\2\2\u0255\u0256\5<\37\2\u0256\67\3\2\2\2\u0257\u0258"+
		"\5<\37\2\u02589\3\2\2\2\u0259\u025a\5<\37\2\u025a;\3\2\2\2\u025b\u0263"+
		"\7\u009a\2\2\u025c\u0263\5*\26\2\u025d\u0263\7\u009c\2\2\u025e\u025f\7"+
		"\b\2\2\u025f\u0260\5<\37\2\u0260\u0261\7\t\2\2\u0261\u0263\3\2\2\2\u0262"+
		"\u025b\3\2\2\2\u0262\u025c\3\2\2\2\u0262\u025d\3\2\2\2\u0262\u025e\3\2"+
		"\2\2\u0263=\3\2\2\2\u0264\u0267\7\u009a\2\2\u0265\u0267\5*\26\2\u0266"+
		"\u0264\3\2\2\2\u0266\u0265\3\2\2\2\u0267?\3\2\2\2\u0268\u0269\t\17\2\2"+
		"\u0269\u026a\5> \2\u026aA\3\2\2\2VJOQZ]cekru}\u0087\u008a\u0090\u0092"+
		"\u0096\u009d\u00a6\u00aa\u00ac\u00b0\u00b9\u00be\u00c0\u00c9\u00d4\u00db"+
		"\u00de\u00e3\u00ef\u00fb\u0102\u0106\u0116\u0119\u0121\u012a\u012e\u0133"+
		"\u0151\u015d\u0162\u016e\u0174\u017b\u017f\u0189\u018c\u0192\u0195\u0197"+
		"\u0199\u019f\u01a4\u01ae\u01b5\u01b8\u01c1\u01c6\u01d4\u01d7\u01d9\u01de"+
		"\u01e2\u01e5\u01ec\u01f4\u01f8\u01fc\u01ff\u0205\u0208\u020a\u0213\u0218"+
		"\u021c\u0220\u0223\u022e\u0233\u023a\u023d\u0262\u0266";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}