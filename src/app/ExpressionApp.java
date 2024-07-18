package app;

import antlr.ExprLexer;
import antlr.ExprParser;
import expression.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class ExpressionApp {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Usage : file name");
        }
        else {
            String fileName = args[0];
            ExprParser parser = getParser(fileName);

            ParseTree antlrAst = parser.prog();

            if(MyErrorListener.hasError) {
                int a= 1;
            }else {
                AntlrToProgram progVisitor = new AntlrToProgram();
                Program prog = progVisitor.visit(antlrAst);

                if(progVisitor.semanticErrors.isEmpty()){
                    ExpressionProcessor ep = new ExpressionProcessor(prog.expressions);
                    for (String evaluation : ep.getEvaluationResults()) {
                        System.out.println(evaluation);
                    }
                } else {
                    for(String err : progVisitor.semanticErrors) {
                        System.out.println(err);
                    }
                }
            }



        }
    }

    private static ExprParser getParser(String fileName) {
        ExprParser parser = null;

        try {
            CharStream input = CharStreams.fromFileName(fileName);
            ExprLexer lexer = new ExprLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new ExprParser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new MyErrorListener());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }
}
