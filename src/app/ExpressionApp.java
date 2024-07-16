package app;

import antlr.ExprLexer;
import antlr.ExprParser;
import expression.AntlrToExpression;
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



        }
    }

    private static ExprParser getParser(String fileName) {
        ExprParser parser = null;

        try {
            CharStream input = CharStreams.fromFileName(fileName);
            ExprLexer lexer = new ExprLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new ExprParser(tokens);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }
}
