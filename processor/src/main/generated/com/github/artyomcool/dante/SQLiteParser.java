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
		RULE_index_name = 27, RULE_table_alias = 28, RULE_any_name = 29, RULE_bind_parameter = 30;
	public static final String[] ruleNames = {
		"parse", "select_stmt", "select_or_values", "type_name", "expr", "full_column_name", 
		"raise_function", "ordering_term", "common_table_expression", "result_column", 
		"table_or_subquery", "join_clause", "join_operator", "join_constraint", 
		"compound_operator", "signed_number", "literal_value", "unary_operator", 
		"error_message", "column_alias", "keyword", "name", "function_name", "database_name", 
		"table_name", "column_name", "collation_name", "index_name", "table_alias", 
		"any_name", "bind_parameter"
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SQLiteParser.EOF, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			expr(0);
			setState(63);
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
			setState(77);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(65);
				match(K_WITH);
				setState(67);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(66);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(69);
				common_table_expression();
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(70);
					match(COMMA);
					setState(71);
					common_table_expression();
					}
					}
					setState(76);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(79);
			select_or_values();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_EXCEPT || _la==K_INTERSECT || _la==K_UNION) {
				{
				{
				setState(80);
				compound_operator();
				setState(81);
				select_or_values();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(88);
				match(K_ORDER);
				setState(89);
				match(K_BY);
				setState(90);
				ordering_term();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(91);
					match(COMMA);
					setState(92);
					ordering_term();
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(106);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(100);
				match(K_LIMIT);
				setState(101);
				expr(0);
				setState(104);
				_la = _input.LA(1);
				if (_la==COMMA || _la==K_OFFSET) {
					{
					setState(102);
					_la = _input.LA(1);
					if ( !(_la==COMMA || _la==K_OFFSET) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(103);
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
			setState(182);
			switch (_input.LA(1)) {
			case K_SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(K_SELECT);
				setState(110);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(109);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(112);
				result_column();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(113);
					match(COMMA);
					setState(114);
					result_column();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				_la = _input.LA(1);
				if (_la==K_FROM) {
					{
					setState(120);
					match(K_FROM);
					setState(130);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						setState(121);
						table_or_subquery();
						setState(126);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(122);
							match(COMMA);
							setState(123);
							table_or_subquery();
							}
							}
							setState(128);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					case 2:
						{
						setState(129);
						join_clause();
						}
						break;
					}
					}
				}

				setState(136);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(134);
					match(K_WHERE);
					setState(135);
					expr(0);
					}
				}

				setState(152);
				_la = _input.LA(1);
				if (_la==K_GROUP) {
					{
					setState(138);
					match(K_GROUP);
					setState(139);
					match(K_BY);
					setState(140);
					expr(0);
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(141);
						match(COMMA);
						setState(142);
						expr(0);
						}
						}
						setState(147);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(150);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(148);
						match(K_HAVING);
						setState(149);
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
				setState(154);
				match(K_VALUES);
				setState(155);
				match(OPEN_PAR);
				setState(156);
				expr(0);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(157);
					match(COMMA);
					setState(158);
					expr(0);
					}
					}
					setState(163);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(164);
				match(CLOSE_PAR);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(165);
					match(COMMA);
					setState(166);
					match(OPEN_PAR);
					setState(167);
					expr(0);
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(168);
						match(COMMA);
						setState(169);
						expr(0);
						}
						}
						setState(174);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(175);
					match(CLOSE_PAR);
					}
					}
					setState(181);
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
			setState(185); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(184);
					name();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(187); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(199);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(189);
				match(OPEN_PAR);
				setState(190);
				signed_number();
				setState(191);
				match(CLOSE_PAR);
				}
				break;
			case 2:
				{
				setState(193);
				match(OPEN_PAR);
				setState(194);
				signed_number();
				setState(195);
				match(COMMA);
				setState(196);
				signed_number();
				setState(197);
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
			setState(267);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(202);
				unary_operator();
				setState(203);
				expr(21);
				}
				break;
			case 2:
				{
				setState(205);
				literal_value();
				}
				break;
			case 3:
				{
				setState(206);
				bind_parameter();
				}
				break;
			case 4:
				{
				setState(207);
				full_column_name();
				}
				break;
			case 5:
				{
				setState(208);
				function_name();
				setState(209);
				match(OPEN_PAR);
				setState(222);
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
					setState(211);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						setState(210);
						match(K_DISTINCT);
						}
						break;
					}
					setState(213);
					expr(0);
					setState(218);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(214);
						match(COMMA);
						setState(215);
						expr(0);
						}
						}
						setState(220);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case STAR:
					{
					setState(221);
					match(STAR);
					}
					break;
				case CLOSE_PAR:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(224);
				match(CLOSE_PAR);
				}
				break;
			case 6:
				{
				setState(226);
				match(OPEN_PAR);
				setState(227);
				expr(0);
				setState(228);
				match(CLOSE_PAR);
				}
				break;
			case 7:
				{
				setState(230);
				match(K_CAST);
				setState(231);
				match(OPEN_PAR);
				setState(232);
				expr(0);
				setState(233);
				match(K_AS);
				setState(234);
				type_name();
				setState(235);
				match(CLOSE_PAR);
				}
				break;
			case 8:
				{
				setState(241);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(238);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(237);
						match(K_NOT);
						}
					}

					setState(240);
					match(K_EXISTS);
					}
				}

				setState(243);
				match(OPEN_PAR);
				setState(244);
				select_stmt();
				setState(245);
				match(CLOSE_PAR);
				}
				break;
			case 9:
				{
				setState(247);
				match(K_CASE);
				setState(249);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(248);
					expr(0);
					}
					break;
				}
				setState(256); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(251);
					match(K_WHEN);
					setState(252);
					expr(0);
					setState(253);
					match(K_THEN);
					setState(254);
					expr(0);
					}
					}
					setState(258); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(262);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(260);
					match(K_ELSE);
					setState(261);
					expr(0);
					}
				}

				setState(264);
				match(K_END);
				}
				break;
			case 10:
				{
				setState(266);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(369);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(367);
					switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(269);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(270);
						match(PIPE2);
						setState(271);
						expr(21);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(273);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(274);
						expr(20);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(275);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(276);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(277);
						expr(19);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(279);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT2) | (1L << GT2) | (1L << AMP) | (1L << PIPE))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(280);
						expr(18);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(282);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << LT_EQ) | (1L << GT) | (1L << GT_EQ))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(283);
						expr(17);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(284);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(297);
						switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
						case 1:
							{
							setState(285);
							match(ASSIGN);
							}
							break;
						case 2:
							{
							setState(286);
							match(EQ);
							}
							break;
						case 3:
							{
							setState(287);
							match(NOT_EQ1);
							}
							break;
						case 4:
							{
							setState(288);
							match(NOT_EQ2);
							}
							break;
						case 5:
							{
							setState(289);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(290);
							match(K_IS);
							setState(291);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(292);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(293);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(294);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(295);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(296);
							match(K_REGEXP);
							}
							break;
						}
						setState(299);
						expr(16);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(300);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(301);
						match(K_AND);
						setState(302);
						expr(15);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(303);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(304);
						match(K_OR);
						setState(305);
						expr(14);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(306);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(307);
						match(K_IS);
						setState(309);
						switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
						case 1:
							{
							setState(308);
							match(K_NOT);
							}
							break;
						}
						setState(311);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(312);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(314);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(313);
							match(K_NOT);
							}
						}

						setState(316);
						match(K_BETWEEN);
						setState(317);
						expr(0);
						setState(318);
						match(K_AND);
						setState(319);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(321);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(322);
						match(K_COLLATE);
						setState(323);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(324);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(326);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(325);
							match(K_NOT);
							}
						}

						setState(328);
						_la = _input.LA(1);
						if ( !(((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (K_GLOB - 80)) | (1L << (K_LIKE - 80)) | (1L << (K_MATCH - 80)) | (1L << (K_REGEXP - 80)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(329);
						expr(0);
						setState(332);
						switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(330);
							match(K_ESCAPE);
							setState(331);
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
						setState(334);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(339);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(335);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(336);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(337);
							match(K_NOT);
							setState(338);
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
						setState(341);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(343);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(342);
							match(K_NOT);
							}
						}

						setState(345);
						match(K_IN);
						setState(365);
						switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
						case 1:
							{
							setState(346);
							match(OPEN_PAR);
							setState(356);
							switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
							case 1:
								{
								setState(347);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(348);
								expr(0);
								setState(353);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==COMMA) {
									{
									{
									setState(349);
									match(COMMA);
									setState(350);
									expr(0);
									}
									}
									setState(355);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(358);
							match(CLOSE_PAR);
							}
							break;
						case 2:
							{
							setState(362);
							switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
							case 1:
								{
								setState(359);
								database_name();
								setState(360);
								match(DOT);
								}
								break;
							}
							setState(364);
							table_name();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(371);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
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
			setState(380);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(375);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(372);
					database_name();
					setState(373);
					match(DOT);
					}
					break;
				}
				setState(377);
				table_name();
				setState(378);
				match(DOT);
				}
				break;
			}
			setState(382);
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
			setState(384);
			match(K_RAISE);
			setState(385);
			match(OPEN_PAR);
			setState(390);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(386);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(387);
				_la = _input.LA(1);
				if ( !(_la==K_ABORT || _la==K_FAIL || _la==K_ROLLBACK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(388);
				match(COMMA);
				setState(389);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(392);
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
			setState(394);
			expr(0);
			setState(397);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(395);
				match(K_COLLATE);
				setState(396);
				collation_name();
				}
			}

			setState(400);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(399);
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
			setState(402);
			table_name();
			setState(414);
			_la = _input.LA(1);
			if (_la==OPEN_PAR) {
				{
				setState(403);
				match(OPEN_PAR);
				setState(404);
				column_name();
				setState(409);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(405);
					match(COMMA);
					setState(406);
					column_name();
					}
					}
					setState(411);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(412);
				match(CLOSE_PAR);
				}
			}

			setState(416);
			match(K_AS);
			setState(417);
			match(OPEN_PAR);
			setState(418);
			select_stmt();
			setState(419);
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
			setState(433);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				match(STAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				table_name();
				setState(423);
				match(DOT);
				setState(424);
				match(STAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(426);
				expr(0);
				setState(431);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(428);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(427);
						match(K_AS);
						}
					}

					setState(430);
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
			setState(482);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					{
					setState(435);
					database_name();
					setState(436);
					match(DOT);
					}
					break;
				}
				setState(440);
				table_name();
				setState(445);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(442);
					switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
					case 1:
						{
						setState(441);
						match(K_AS);
						}
						break;
					}
					setState(444);
					table_alias();
					}
					break;
				}
				setState(452);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(447);
					match(K_INDEXED);
					setState(448);
					match(K_BY);
					setState(449);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(450);
					match(K_NOT);
					setState(451);
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
				setState(454);
				match(OPEN_PAR);
				setState(464);
				switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(455);
					table_or_subquery();
					setState(460);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(456);
						match(COMMA);
						setState(457);
						table_or_subquery();
						}
						}
						setState(462);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(463);
					join_clause();
					}
					break;
				}
				setState(466);
				match(CLOSE_PAR);
				setState(471);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(468);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						setState(467);
						match(K_AS);
						}
						break;
					}
					setState(470);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(473);
				match(OPEN_PAR);
				setState(474);
				select_stmt();
				setState(475);
				match(CLOSE_PAR);
				setState(480);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(477);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						setState(476);
						match(K_AS);
						}
						break;
					}
					setState(479);
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
			setState(484);
			table_or_subquery();
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==K_CROSS || ((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (K_INNER - 90)) | (1L << (K_JOIN - 90)) | (1L << (K_LEFT - 90)) | (1L << (K_NATURAL - 90)))) != 0)) {
				{
				{
				setState(485);
				join_operator();
				setState(486);
				table_or_subquery();
				setState(487);
				join_constraint();
				}
				}
				setState(493);
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
			setState(507);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(494);
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
				setState(496);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(495);
					match(K_NATURAL);
					}
				}

				setState(504);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(498);
					match(K_LEFT);
					setState(500);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(499);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(502);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(503);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(506);
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
			setState(523);
			switch (_input.LA(1)) {
			case K_ON:
				{
				setState(509);
				match(K_ON);
				setState(510);
				expr(0);
				}
				break;
			case K_USING:
				{
				setState(511);
				match(K_USING);
				setState(512);
				match(OPEN_PAR);
				setState(513);
				column_name();
				setState(518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(514);
					match(COMMA);
					setState(515);
					column_name();
					}
					}
					setState(520);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(521);
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
			setState(530);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(525);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(526);
				match(K_UNION);
				setState(527);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(528);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(529);
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
			setState(533);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(532);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(535);
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
			setState(537);
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
			setState(539);
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
			setState(541);
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
			setState(543);
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
			setState(545);
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
			setState(547);
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
			setState(549);
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
			setState(551);
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
			setState(553);
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
			setState(555);
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
			setState(557);
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
			setState(559);
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
			setState(561);
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
			setState(570);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(563);
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
				setState(564);
				keyword();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(565);
				match(STRING_LITERAL);
				}
				break;
			case OPEN_PAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(566);
				match(OPEN_PAR);
				setState(567);
				any_name();
				setState(568);
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

	public static class Bind_parameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLiteParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 60, RULE_bind_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(573);
			match(IDENTIFIER);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\u009f\u0242\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\3\3\3\5\3F\n\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\5\3P\n\3"+
		"\3\3\3\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\3\3\3\3\3\3\3\3\7\3`\n\3"+
		"\f\3\16\3c\13\3\5\3e\n\3\3\3\3\3\3\3\3\3\5\3k\n\3\5\3m\n\3\3\4\3\4\5\4"+
		"q\n\4\3\4\3\4\3\4\7\4v\n\4\f\4\16\4y\13\4\3\4\3\4\3\4\3\4\7\4\177\n\4"+
		"\f\4\16\4\u0082\13\4\3\4\5\4\u0085\n\4\5\4\u0087\n\4\3\4\3\4\5\4\u008b"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\7\4\u0092\n\4\f\4\16\4\u0095\13\4\3\4\3\4\5\4"+
		"\u0099\n\4\5\4\u009b\n\4\3\4\3\4\3\4\3\4\3\4\7\4\u00a2\n\4\f\4\16\4\u00a5"+
		"\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00ad\n\4\f\4\16\4\u00b0\13\4\3\4\3"+
		"\4\7\4\u00b4\n\4\f\4\16\4\u00b7\13\4\5\4\u00b9\n\4\3\5\6\5\u00bc\n\5\r"+
		"\5\16\5\u00bd\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00ca\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00d6\n\6\3\6\3\6\3\6\7\6\u00db"+
		"\n\6\f\6\16\6\u00de\13\6\3\6\5\6\u00e1\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00f1\n\6\3\6\5\6\u00f4\n\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\5\6\u00fc\n\6\3\6\3\6\3\6\3\6\3\6\6\6\u0103\n\6\r\6\16"+
		"\6\u0104\3\6\3\6\5\6\u0109\n\6\3\6\3\6\3\6\5\6\u010e\n\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u012c\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6\u0138\n\6\3\6\3\6\3\6\5\6\u013d\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6\u0149\n\6\3\6\3\6\3\6\3\6\5\6\u014f\n\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6\u0156\n\6\3\6\3\6\5\6\u015a\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6\u0162\n\6\f\6\16\6\u0165\13\6\5\6\u0167\n\6\3\6\3\6\3\6\3\6"+
		"\5\6\u016d\n\6\3\6\5\6\u0170\n\6\7\6\u0172\n\6\f\6\16\6\u0175\13\6\3\7"+
		"\3\7\3\7\5\7\u017a\n\7\3\7\3\7\3\7\5\7\u017f\n\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\5\b\u0189\n\b\3\b\3\b\3\t\3\t\3\t\5\t\u0190\n\t\3\t\5\t\u0193"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\7\n\u019a\n\n\f\n\16\n\u019d\13\n\3\n\3\n\5\n"+
		"\u01a1\n\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u01af\n\13\3\13\5\13\u01b2\n\13\5\13\u01b4\n\13\3\f\3\f\3\f\5\f\u01b9"+
		"\n\f\3\f\3\f\5\f\u01bd\n\f\3\f\5\f\u01c0\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u01c7"+
		"\n\f\3\f\3\f\3\f\3\f\7\f\u01cd\n\f\f\f\16\f\u01d0\13\f\3\f\5\f\u01d3\n"+
		"\f\3\f\3\f\5\f\u01d7\n\f\3\f\5\f\u01da\n\f\3\f\3\f\3\f\3\f\5\f\u01e0\n"+
		"\f\3\f\5\f\u01e3\n\f\5\f\u01e5\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u01ec\n\r\f"+
		"\r\16\r\u01ef\13\r\3\16\3\16\5\16\u01f3\n\16\3\16\3\16\5\16\u01f7\n\16"+
		"\3\16\3\16\5\16\u01fb\n\16\3\16\5\16\u01fe\n\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u0207\n\17\f\17\16\17\u020a\13\17\3\17\3\17\5\17\u020e"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\5\20\u0215\n\20\3\21\5\21\u0218\n\21\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u023d\n\37\3 \3 \3 \3 \2\3\n!\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\20\4"+
		"\2\n\noo\4\2\"\"CC\4\2\f\f\21\22\3\2\r\16\3\2\23\26\3\2\27\32\6\2RRff"+
		"hh{{\5\2\36\36MM\u0082\u0082\4\2\'\'AA\5\29;mm\u009b\u009d\4\2\r\17kk"+
		"\4\2\u009a\u009a\u009c\u009c\3\2\36\u0099\3\2\3\5\u0299\2@\3\2\2\2\4O"+
		"\3\2\2\2\6\u00b8\3\2\2\2\b\u00bb\3\2\2\2\n\u010d\3\2\2\2\f\u017e\3\2\2"+
		"\2\16\u0182\3\2\2\2\20\u018c\3\2\2\2\22\u0194\3\2\2\2\24\u01b3\3\2\2\2"+
		"\26\u01e4\3\2\2\2\30\u01e6\3\2\2\2\32\u01fd\3\2\2\2\34\u020d\3\2\2\2\36"+
		"\u0214\3\2\2\2 \u0217\3\2\2\2\"\u021b\3\2\2\2$\u021d\3\2\2\2&\u021f\3"+
		"\2\2\2(\u0221\3\2\2\2*\u0223\3\2\2\2,\u0225\3\2\2\2.\u0227\3\2\2\2\60"+
		"\u0229\3\2\2\2\62\u022b\3\2\2\2\64\u022d\3\2\2\2\66\u022f\3\2\2\28\u0231"+
		"\3\2\2\2:\u0233\3\2\2\2<\u023c\3\2\2\2>\u023e\3\2\2\2@A\5\n\6\2AB\7\2"+
		"\2\3B\3\3\2\2\2CE\7\u0098\2\2DF\7y\2\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2G"+
		"L\5\22\n\2HI\7\n\2\2IK\5\22\n\2JH\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2"+
		"\2MP\3\2\2\2NL\3\2\2\2OC\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QW\5\6\4\2RS\5\36"+
		"\20\2ST\5\6\4\2TV\3\2\2\2UR\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2Xd\3"+
		"\2\2\2YW\3\2\2\2Z[\7r\2\2[\\\7-\2\2\\a\5\20\t\2]^\7\n\2\2^`\5\20\t\2_"+
		"]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2ca\3\2\2\2dZ\3\2\2\2"+
		"de\3\2\2\2el\3\2\2\2fg\7g\2\2gj\5\n\6\2hi\t\2\2\2ik\5\n\6\2jh\3\2\2\2"+
		"jk\3\2\2\2km\3\2\2\2lf\3\2\2\2lm\3\2\2\2m\5\3\2\2\2np\7\u0085\2\2oq\t"+
		"\3\2\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2rw\5\24\13\2st\7\n\2\2tv\5\24\13\2"+
		"us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\u0086\3\2\2\2yw\3\2\2\2z\u0084"+
		"\7P\2\2{\u0080\5\26\f\2|}\7\n\2\2}\177\5\26\f\2~|\3\2\2\2\177\u0082\3"+
		"\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0085\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0083\u0085\5\30\r\2\u0084{\3\2\2\2\u0084\u0083\3\2\2\2\u0085"+
		"\u0087\3\2\2\2\u0086z\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008a\3\2\2\2"+
		"\u0088\u0089\7\u0097\2\2\u0089\u008b\5\n\6\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u009a\3\2\2\2\u008c\u008d\7S\2\2\u008d\u008e\7-\2"+
		"\2\u008e\u0093\5\n\6\2\u008f\u0090\7\n\2\2\u0090\u0092\5\n\6\2\u0091\u008f"+
		"\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0098\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7T\2\2\u0097\u0099\5\n"+
		"\6\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a"+
		"\u008c\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00b9\3\2\2\2\u009c\u009d\7\u0093"+
		"\2\2\u009d\u009e\7\b\2\2\u009e\u00a3\5\n\6\2\u009f\u00a0\7\n\2\2\u00a0"+
		"\u00a2\5\n\6\2\u00a1\u009f\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6"+
		"\u00b5\7\t\2\2\u00a7\u00a8\7\n\2\2\u00a8\u00a9\7\b\2\2\u00a9\u00ae\5\n"+
		"\6\2\u00aa\u00ab\7\n\2\2\u00ab\u00ad\5\n\6\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2"+
		"\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7\t\2\2\u00b2\u00b4\3\2\2\2\u00b3"+
		"\u00a7\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8n\3\2\2\2\u00b8\u009c"+
		"\3\2\2\2\u00b9\7\3\2\2\2\u00ba\u00bc\5,\27\2\u00bb\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c9\3\2"+
		"\2\2\u00bf\u00c0\7\b\2\2\u00c0\u00c1\5 \21\2\u00c1\u00c2\7\t\2\2\u00c2"+
		"\u00ca\3\2\2\2\u00c3\u00c4\7\b\2\2\u00c4\u00c5\5 \21\2\u00c5\u00c6\7\n"+
		"\2\2\u00c6\u00c7\5 \21\2\u00c7\u00c8\7\t\2\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00bf\3\2\2\2\u00c9\u00c3\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\t\3\2\2\2"+
		"\u00cb\u00cc\b\6\1\2\u00cc\u00cd\5$\23\2\u00cd\u00ce\5\n\6\27\u00ce\u010e"+
		"\3\2\2\2\u00cf\u010e\5\"\22\2\u00d0\u010e\5> \2\u00d1\u010e\5\f\7\2\u00d2"+
		"\u00d3\5.\30\2\u00d3\u00e0\7\b\2\2\u00d4\u00d6\7C\2\2\u00d5\u00d4\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00dc\5\n\6\2\u00d8"+
		"\u00d9\7\n\2\2\u00d9\u00db\5\n\6\2\u00da\u00d8\3\2\2\2\u00db\u00de\3\2"+
		"\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e1\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00e1\7\f\2\2\u00e0\u00d5\3\2\2\2\u00e0\u00df\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\7\t\2\2\u00e3"+
		"\u010e\3\2\2\2\u00e4\u00e5\7\b\2\2\u00e5\u00e6\5\n\6\2\u00e6\u00e7\7\t"+
		"\2\2\u00e7\u010e\3\2\2\2\u00e8\u00e9\7\60\2\2\u00e9\u00ea\7\b\2\2\u00ea"+
		"\u00eb\5\n\6\2\u00eb\u00ec\7&\2\2\u00ec\u00ed\5\b\5\2\u00ed\u00ee\7\t"+
		"\2\2\u00ee\u010e\3\2\2\2\u00ef\u00f1\7k\2\2\u00f0\u00ef\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\7K\2\2\u00f3\u00f0\3\2"+
		"\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\7\b\2\2\u00f6"+
		"\u00f7\5\4\3\2\u00f7\u00f8\7\t\2\2\u00f8\u010e\3\2\2\2\u00f9\u00fb\7/"+
		"\2\2\u00fa\u00fc\5\n\6\2\u00fb\u00fa\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u0102\3\2\2\2\u00fd\u00fe\7\u0096\2\2\u00fe\u00ff\5\n\6\2\u00ff\u0100"+
		"\7\u008a\2\2\u0100\u0101\5\n\6\2\u0101\u0103\3\2\2\2\u0102\u00fd\3\2\2"+
		"\2\u0103\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0108"+
		"\3\2\2\2\u0106\u0107\7F\2\2\u0107\u0109\5\n\6\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\7G\2\2\u010b\u010e\3\2"+
		"\2\2\u010c\u010e\5\16\b\2\u010d\u00cb\3\2\2\2\u010d\u00cf\3\2\2\2\u010d"+
		"\u00d0\3\2\2\2\u010d\u00d1\3\2\2\2\u010d\u00d2\3\2\2\2\u010d\u00e4\3\2"+
		"\2\2\u010d\u00e8\3\2\2\2\u010d\u00f3\3\2\2\2\u010d\u00f9\3\2\2\2\u010d"+
		"\u010c\3\2\2\2\u010e\u0173\3\2\2\2\u010f\u0110\f\26\2\2\u0110\u0111\7"+
		"\20\2\2\u0111\u0172\5\n\6\27\u0112\u0113\f\25\2\2\u0113\u0114\t\4\2\2"+
		"\u0114\u0172\5\n\6\26\u0115\u0116\f\24\2\2\u0116\u0117\t\5\2\2\u0117\u0172"+
		"\5\n\6\25\u0118\u0119\f\23\2\2\u0119\u011a\t\6\2\2\u011a\u0172\5\n\6\24"+
		"\u011b\u011c\f\22\2\2\u011c\u011d\t\7\2\2\u011d\u0172\5\n\6\23\u011e\u012b"+
		"\f\21\2\2\u011f\u012c\7\13\2\2\u0120\u012c\7\33\2\2\u0121\u012c\7\34\2"+
		"\2\u0122\u012c\7\35\2\2\u0123\u012c\7a\2\2\u0124\u0125\7a\2\2\u0125\u012c"+
		"\7k\2\2\u0126\u012c\7X\2\2\u0127\u012c\7f\2\2\u0128\u012c\7R\2\2\u0129"+
		"\u012c\7h\2\2\u012a\u012c\7{\2\2\u012b\u011f\3\2\2\2\u012b\u0120\3\2\2"+
		"\2\u012b\u0121\3\2\2\2\u012b\u0122\3\2\2\2\u012b\u0123\3\2\2\2\u012b\u0124"+
		"\3\2\2\2\u012b\u0126\3\2\2\2\u012b\u0127\3\2\2\2\u012b\u0128\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u0172\5\n"+
		"\6\22\u012e\u012f\f\20\2\2\u012f\u0130\7%\2\2\u0130\u0172\5\n\6\21\u0131"+
		"\u0132\f\17\2\2\u0132\u0133\7q\2\2\u0133\u0172\5\n\6\20\u0134\u0135\f"+
		"\b\2\2\u0135\u0137\7a\2\2\u0136\u0138\7k\2\2\u0137\u0136\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0172\5\n\6\t\u013a\u013c\f\7"+
		"\2\2\u013b\u013d\7k\2\2\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013e\3\2\2\2\u013e\u013f\7,\2\2\u013f\u0140\5\n\6\2\u0140\u0141\7%\2"+
		"\2\u0141\u0142\5\n\6\b\u0142\u0172\3\2\2\2\u0143\u0144\f\13\2\2\u0144"+
		"\u0145\7\62\2\2\u0145\u0172\5\66\34\2\u0146\u0148\f\n\2\2\u0147\u0149"+
		"\7k\2\2\u0148\u0147\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014b\t\b\2\2\u014b\u014e\5\n\6\2\u014c\u014d\7H\2\2\u014d\u014f\5\n"+
		"\6\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0172\3\2\2\2\u0150"+
		"\u0155\f\t\2\2\u0151\u0156\7b\2\2\u0152\u0156\7l\2\2\u0153\u0154\7k\2"+
		"\2\u0154\u0156\7m\2\2\u0155\u0151\3\2\2\2\u0155\u0152\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0156\u0172\3\2\2\2\u0157\u0159\f\6\2\2\u0158\u015a\7k\2\2\u0159"+
		"\u0158\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u016f\7X"+
		"\2\2\u015c\u0166\7\b\2\2\u015d\u0167\5\4\3\2\u015e\u0163\5\n\6\2\u015f"+
		"\u0160\7\n\2\2\u0160\u0162\5\n\6\2\u0161\u015f\3\2\2\2\u0162\u0165\3\2"+
		"\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0167\3\2\2\2\u0165"+
		"\u0163\3\2\2\2\u0166\u015d\3\2\2\2\u0166\u015e\3\2\2\2\u0166\u0167\3\2"+
		"\2\2\u0167\u0168\3\2\2\2\u0168\u0170\7\t\2\2\u0169\u016a\5\60\31\2\u016a"+
		"\u016b\7\7\2\2\u016b\u016d\3\2\2\2\u016c\u0169\3\2\2\2\u016c\u016d\3\2"+
		"\2\2\u016d\u016e\3\2\2\2\u016e\u0170\5\62\32\2\u016f\u015c\3\2\2\2\u016f"+
		"\u016c\3\2\2\2\u0170\u0172\3\2\2\2\u0171\u010f\3\2\2\2\u0171\u0112\3\2"+
		"\2\2\u0171\u0115\3\2\2\2\u0171\u0118\3\2\2\2\u0171\u011b\3\2\2\2\u0171"+
		"\u011e\3\2\2\2\u0171\u012e\3\2\2\2\u0171\u0131\3\2\2\2\u0171\u0134\3\2"+
		"\2\2\u0171\u013a\3\2\2\2\u0171\u0143\3\2\2\2\u0171\u0146\3\2\2\2\u0171"+
		"\u0150\3\2\2\2\u0171\u0157\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2"+
		"\2\2\u0173\u0174\3\2\2\2\u0174\13\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177"+
		"\5\60\31\2\u0177\u0178\7\7\2\2\u0178\u017a\3\2\2\2\u0179\u0176\3\2\2\2"+
		"\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\5\62\32\2\u017c\u017d"+
		"\7\7\2\2\u017d\u017f\3\2\2\2\u017e\u0179\3\2\2\2\u017e\u017f\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0181\5\64\33\2\u0181\r\3\2\2\2\u0182\u0183\7x\2"+
		"\2\u0183\u0188\7\b\2\2\u0184\u0189\7V\2\2\u0185\u0186\t\t\2\2\u0186\u0187"+
		"\7\n\2\2\u0187\u0189\5&\24\2\u0188\u0184\3\2\2\2\u0188\u0185\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u018b\7\t\2\2\u018b\17\3\2\2\2\u018c\u018f\5\n\6"+
		"\2\u018d\u018e\7\62\2\2\u018e\u0190\5\66\34\2\u018f\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u0193\t\n\2\2\u0192\u0191\3\2"+
		"\2\2\u0192\u0193\3\2\2\2\u0193\21\3\2\2\2\u0194\u01a0\5\62\32\2\u0195"+
		"\u0196\7\b\2\2\u0196\u019b\5\64\33\2\u0197\u0198\7\n\2\2\u0198\u019a\5"+
		"\64\33\2\u0199\u0197\3\2\2\2\u019a\u019d\3\2\2\2\u019b\u0199\3\2\2\2\u019b"+
		"\u019c\3\2\2\2\u019c\u019e\3\2\2\2\u019d\u019b\3\2\2\2\u019e\u019f\7\t"+
		"\2\2\u019f\u01a1\3\2\2\2\u01a0\u0195\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\u01a3\7&\2\2\u01a3\u01a4\7\b\2\2\u01a4\u01a5\5\4"+
		"\3\2\u01a5\u01a6\7\t\2\2\u01a6\23\3\2\2\2\u01a7\u01b4\7\f\2\2\u01a8\u01a9"+
		"\5\62\32\2\u01a9\u01aa\7\7\2\2\u01aa\u01ab\7\f\2\2\u01ab\u01b4\3\2\2\2"+
		"\u01ac\u01b1\5\n\6\2\u01ad\u01af\7&\2\2\u01ae\u01ad\3\2\2\2\u01ae\u01af"+
		"\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b2\5(\25\2\u01b1\u01ae\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b4\3\2\2\2\u01b3\u01a7\3\2\2\2\u01b3\u01a8\3\2"+
		"\2\2\u01b3\u01ac\3\2\2\2\u01b4\25\3\2\2\2\u01b5\u01b6\5\60\31\2\u01b6"+
		"\u01b7\7\7\2\2\u01b7\u01b9\3\2\2\2\u01b8\u01b5\3\2\2\2\u01b8\u01b9\3\2"+
		"\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bf\5\62\32\2\u01bb\u01bd\7&\2\2\u01bc"+
		"\u01bb\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c0\5:"+
		"\36\2\u01bf\u01bc\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c6\3\2\2\2\u01c1"+
		"\u01c2\7Z\2\2\u01c2\u01c3\7-\2\2\u01c3\u01c7\58\35\2\u01c4\u01c5\7k\2"+
		"\2\u01c5\u01c7\7Z\2\2\u01c6\u01c1\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7"+
		"\3\2\2\2\u01c7\u01e5\3\2\2\2\u01c8\u01d2\7\b\2\2\u01c9\u01ce\5\26\f\2"+
		"\u01ca\u01cb\7\n\2\2\u01cb\u01cd\5\26\f\2\u01cc\u01ca\3\2\2\2\u01cd\u01d0"+
		"\3\2\2\2\u01ce\u01cc\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d3\3\2\2\2\u01d0"+
		"\u01ce\3\2\2\2\u01d1\u01d3\5\30\r\2\u01d2\u01c9\3\2\2\2\u01d2\u01d1\3"+
		"\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d9\7\t\2\2\u01d5\u01d7\7&\2\2\u01d6"+
		"\u01d5\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01da\5:"+
		"\36\2\u01d9\u01d6\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01e5\3\2\2\2\u01db"+
		"\u01dc\7\b\2\2\u01dc\u01dd\5\4\3\2\u01dd\u01e2\7\t\2\2\u01de\u01e0\7&"+
		"\2\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u01e3\5:\36\2\u01e2\u01df\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e5\3\2"+
		"\2\2\u01e4\u01b8\3\2\2\2\u01e4\u01c8\3\2\2\2\u01e4\u01db\3\2\2\2\u01e5"+
		"\27\3\2\2\2\u01e6\u01ed\5\26\f\2\u01e7\u01e8\5\32\16\2\u01e8\u01e9\5\26"+
		"\f\2\u01e9\u01ea\5\34\17\2\u01ea\u01ec\3\2\2\2\u01eb\u01e7\3\2\2\2\u01ec"+
		"\u01ef\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\31\3\2\2"+
		"\2\u01ef\u01ed\3\2\2\2\u01f0\u01fe\7\n\2\2\u01f1\u01f3\7i\2\2\u01f2\u01f1"+
		"\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01fa\3\2\2\2\u01f4\u01f6\7e\2\2\u01f5"+
		"\u01f7\7s\2\2\u01f6\u01f5\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01fb\3\2"+
		"\2\2\u01f8\u01fb\7\\\2\2\u01f9\u01fb\78\2\2\u01fa\u01f4\3\2\2\2\u01fa"+
		"\u01f8\3\2\2\2\u01fa\u01f9\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fc\3\2"+
		"\2\2\u01fc\u01fe\7c\2\2\u01fd\u01f0\3\2\2\2\u01fd\u01f2\3\2\2\2\u01fe"+
		"\33\3\2\2\2\u01ff\u0200\7p\2\2\u0200\u020e\5\n\6\2\u0201\u0202\7\u0091"+
		"\2\2\u0202\u0203\7\b\2\2\u0203\u0208\5\64\33\2\u0204\u0205\7\n\2\2\u0205"+
		"\u0207\5\64\33\2\u0206\u0204\3\2\2\2\u0207\u020a\3\2\2\2\u0208\u0206\3"+
		"\2\2\2\u0208\u0209\3\2\2\2\u0209\u020b\3\2\2\2\u020a\u0208\3\2\2\2\u020b"+
		"\u020c\7\t\2\2\u020c\u020e\3\2\2\2\u020d\u01ff\3\2\2\2\u020d\u0201\3\2"+
		"\2\2\u020d\u020e\3\2\2\2\u020e\35\3\2\2\2\u020f\u0215\7\u008e\2\2\u0210"+
		"\u0211\7\u008e\2\2\u0211\u0215\7\"\2\2\u0212\u0215\7_\2\2\u0213\u0215"+
		"\7I\2\2\u0214\u020f\3\2\2\2\u0214\u0210\3\2\2\2\u0214\u0212\3\2\2\2\u0214"+
		"\u0213\3\2\2\2\u0215\37\3\2\2\2\u0216\u0218\t\5\2\2\u0217\u0216\3\2\2"+
		"\2\u0217\u0218\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\7\u009b\2\2\u021a"+
		"!\3\2\2\2\u021b\u021c\t\13\2\2\u021c#\3\2\2\2\u021d\u021e\t\f\2\2\u021e"+
		"%\3\2\2\2\u021f\u0220\7\u009c\2\2\u0220\'\3\2\2\2\u0221\u0222\t\r\2\2"+
		"\u0222)\3\2\2\2\u0223\u0224\t\16\2\2\u0224+\3\2\2\2\u0225\u0226\5<\37"+
		"\2\u0226-\3\2\2\2\u0227\u0228\5<\37\2\u0228/\3\2\2\2\u0229\u022a\5<\37"+
		"\2\u022a\61\3\2\2\2\u022b\u022c\5<\37\2\u022c\63\3\2\2\2\u022d\u022e\5"+
		"<\37\2\u022e\65\3\2\2\2\u022f\u0230\5<\37\2\u0230\67\3\2\2\2\u0231\u0232"+
		"\5<\37\2\u02329\3\2\2\2\u0233\u0234\5<\37\2\u0234;\3\2\2\2\u0235\u023d"+
		"\7\u009a\2\2\u0236\u023d\5*\26\2\u0237\u023d\7\u009c\2\2\u0238\u0239\7"+
		"\b\2\2\u0239\u023a\5<\37\2\u023a\u023b\7\t\2\2\u023b\u023d\3\2\2\2\u023c"+
		"\u0235\3\2\2\2\u023c\u0236\3\2\2\2\u023c\u0237\3\2\2\2\u023c\u0238\3\2"+
		"\2\2\u023d=\3\2\2\2\u023e\u023f\t\17\2\2\u023f\u0240\7\u009a\2\2\u0240"+
		"?\3\2\2\2NELOWadjlpw\u0080\u0084\u0086\u008a\u0093\u0098\u009a\u00a3\u00ae"+
		"\u00b5\u00b8\u00bd\u00c9\u00d5\u00dc\u00e0\u00f0\u00f3\u00fb\u0104\u0108"+
		"\u010d\u012b\u0137\u013c\u0148\u014e\u0155\u0159\u0163\u0166\u016c\u016f"+
		"\u0171\u0173\u0179\u017e\u0188\u018f\u0192\u019b\u01a0\u01ae\u01b1\u01b3"+
		"\u01b8\u01bc\u01bf\u01c6\u01ce\u01d2\u01d6\u01d9\u01df\u01e2\u01e4\u01ed"+
		"\u01f2\u01f6\u01fa\u01fd\u0208\u020d\u0214\u0217\u023c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}