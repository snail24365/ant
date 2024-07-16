package expression;

import antlr.ExprBaseVisitor;
import antlr.ExprParser;

import java.util.ArrayList;
import java.util.List;

public class AntlrToProgram extends ExprBaseVisitor<Program> {

    private List<String> semanticErrors; // 1. dup 2. undeclared

//    public AtlrToProgram(List<String> semanticErrors) {
//        this.semanticErrors = semanticErrors;
//    }

    @Override
    public Program visitProgram(ExprParser.ProgramContext ctx) {
        Program prog = new Program();

        semanticErrors = new ArrayList<>();
        AntlrToExpression exprVisitor = new AntlrToExpression(semanticErrors);
        for (int i = 0; i<ctx.getChildCount() - 1;i++) {
            if(i==ctx.getChildCount() - 1) {
                /*
                last child of the satrt symmbol prog is EOF
                // do not visit this child and
                attempt to conver it to  an expression object
                */
            } else {
                prog.addExpression(exprVisitor.visit(ctx.getChild(i)));
            }
        }
        return super.visitProgram(ctx);
    }
}
