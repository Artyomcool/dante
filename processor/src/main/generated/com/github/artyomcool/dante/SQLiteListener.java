// Generated from com/github/artyomcool/dante/SQLite.g4 by ANTLR 4.5.1

   package com.github.artyomcool.dante;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLiteParser}.
 */
public interface SQLiteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(SQLiteParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(SQLiteParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(SQLiteParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(SQLiteParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void enterSelect_or_values(SQLiteParser.Select_or_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void exitSelect_or_values(SQLiteParser.Select_or_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(SQLiteParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(SQLiteParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SQLiteParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SQLiteParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#full_column_name}.
	 * @param ctx the parse tree
	 */
	void enterFull_column_name(SQLiteParser.Full_column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#full_column_name}.
	 * @param ctx the parse tree
	 */
	void exitFull_column_name(SQLiteParser.Full_column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void enterRaise_function(SQLiteParser.Raise_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void exitRaise_function(SQLiteParser.Raise_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_term(SQLiteParser.Ordering_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_term(SQLiteParser.Ordering_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void enterCommon_table_expression(SQLiteParser.Common_table_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void exitCommon_table_expression(SQLiteParser.Common_table_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#result_column}.
	 * @param ctx the parse tree
	 */
	void enterResult_column(SQLiteParser.Result_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#result_column}.
	 * @param ctx the parse tree
	 */
	void exitResult_column(SQLiteParser.Result_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void enterTable_or_subquery(SQLiteParser.Table_or_subqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void exitTable_or_subquery(SQLiteParser.Table_or_subqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(SQLiteParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(SQLiteParser.Join_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void enterJoin_operator(SQLiteParser.Join_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void exitJoin_operator(SQLiteParser.Join_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void enterJoin_constraint(SQLiteParser.Join_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void exitJoin_constraint(SQLiteParser.Join_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void enterCompound_operator(SQLiteParser.Compound_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void exitCompound_operator(SQLiteParser.Compound_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void enterSigned_number(SQLiteParser.Signed_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void exitSigned_number(SQLiteParser.Signed_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(SQLiteParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(SQLiteParser.Literal_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(SQLiteParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(SQLiteParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#error_message}.
	 * @param ctx the parse tree
	 */
	void enterError_message(SQLiteParser.Error_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#error_message}.
	 * @param ctx the parse tree
	 */
	void exitError_message(SQLiteParser.Error_messageContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(SQLiteParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(SQLiteParser.Column_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(SQLiteParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(SQLiteParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(SQLiteParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(SQLiteParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(SQLiteParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(SQLiteParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#database_name}.
	 * @param ctx the parse tree
	 */
	void enterDatabase_name(SQLiteParser.Database_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#database_name}.
	 * @param ctx the parse tree
	 */
	void exitDatabase_name(SQLiteParser.Database_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(SQLiteParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(SQLiteParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(SQLiteParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(SQLiteParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void enterCollation_name(SQLiteParser.Collation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void exitCollation_name(SQLiteParser.Collation_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(SQLiteParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(SQLiteParser.Index_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(SQLiteParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(SQLiteParser.Table_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(SQLiteParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(SQLiteParser.Any_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#bind_name}.
	 * @param ctx the parse tree
	 */
	void enterBind_name(SQLiteParser.Bind_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#bind_name}.
	 * @param ctx the parse tree
	 */
	void exitBind_name(SQLiteParser.Bind_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLiteParser#bind_parameter}.
	 * @param ctx the parse tree
	 */
	void enterBind_parameter(SQLiteParser.Bind_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLiteParser#bind_parameter}.
	 * @param ctx the parse tree
	 */
	void exitBind_parameter(SQLiteParser.Bind_parameterContext ctx);
}