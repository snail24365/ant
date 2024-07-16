package expression;

import antlr.ExprBaseVisitor;
import antlr.ExprParser;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {
    private List<String> vars; // stores all the variables declared in the program so far
    private List<String> semanticErrors; // 1. dup 2. undeclared

    @Override
    public Expression visitDeclaration(ExprParser.DeclarationContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine();
        String id = ctx.getChild(0).getText();
        if(vars.contains(id)) {
            semanticErrors.add("Error : variable" + id + " alread declared ");
        }
        return super.visitDeclaration(ctx);
    }

    @Override
    public Expression visitMultiplication(ExprParser.MultiplicationContext ctx) {
        return super.visitMultiplication(ctx);
    }

    @Override
    public Expression visitAddition(ExprParser.AdditionContext ctx) {
        return super.visitAddition(ctx);
    }

    @Override
    public Expression visitVariable(ExprParser.VariableContext ctx) {
        return super.visitVariable(ctx);
    }

    @Override
    public Expression visitNumber(ExprParser.NumberContext ctx) {
        return super.visitNumber(ctx);
    }
}
