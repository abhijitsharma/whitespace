package org.absharma.whitespace;

import org.absharma.whitespace.node.ASTCompilationUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * User: absharma
 * Date: 4/4/13
 */
public class WhitespaceEvaluator {

    static class StackHeap {
        Stack stack = new Stack();
        Map heap = new HashMap();
    }

    public boolean evaluate(String expression, StackHeap stackHeap) {
        StringReader sr = new StringReader(expression);
        WhitespaceParser parser = new WhitespaceParser(sr);
        try {
            parser.CompilationUnit();
            ASTCompilationUnit root = (ASTCompilationUnit) parser.jjtree.rootNode();

            System.out.println("---------------------");
            System.out.println("Printing the Program");
            System.out.println("---------------------");

            WhitespaceLegibleVisitor v = new WhitespaceLegibleVisitor();
            root.jjtAccept(v, stackHeap);

            System.out.println("---------------------");
            System.out.println("Evaluating the Program");
            System.out.println("---------------------");

            WhitespaceEvaluationVisitor ev = new WhitespaceEvaluationVisitor();
            root.jjtAccept(ev, stackHeap);
            return true;
        } catch (ParseException e) {
            throw new RuntimeException("Error in parsing expression " + expression, e);
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage:");
            System.out.println("java -jar whitespace-1.0.jar -f <source-file>");
            System.exit(-1);
        }
        String file = args[1];
        String content = null;
        try {
            content = replaceWhitespaces(streamAsString(new FileInputStream(file)));
        } catch (IOException e) {
            System.out.println("File Read Error : " + file + " : " + e.getMessage());
            System.exit(-1);
        }
        new WhitespaceEvaluator().evaluate(content, new StackHeap());
    }

    private static String streamAsString(final InputStream is) throws IOException {
        Reader r = new InputStreamReader(is);
        StringWriter sw = new StringWriter();
        char[] buffer = new char[1024];
        for (int n; (n = r.read(buffer)) != -1; )
            sw.write(buffer, 0, n);
        return sw.toString();
    }

    private static String replaceWhitespaces(String content) {
        StringBuilder sb = new StringBuilder();
        for (char c : content.toCharArray()) {
            switch (c) {
                case 32 :
                    sb.append("S");
                    break;
                case 9 :
                    sb.append("T");
                    break;
                case 10 :
                    sb.append("L");
                    break;
                default:
            }
        }
        return sb.toString();
    }
}
