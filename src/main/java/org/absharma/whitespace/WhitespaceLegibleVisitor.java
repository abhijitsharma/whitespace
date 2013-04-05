package org.absharma.whitespace;

import org.absharma.whitespace.node.ASTArithmeticCommand;
import org.absharma.whitespace.node.ASTArithmeticCommandAdditionInstr;
import org.absharma.whitespace.node.ASTArithmeticCommandDivisionInstr;
import org.absharma.whitespace.node.ASTArithmeticCommandMultiplicationInstr;
import org.absharma.whitespace.node.ASTArithmeticCommandSubtractionInstr;
import org.absharma.whitespace.node.ASTCompilationUnit;
import org.absharma.whitespace.node.ASTIOCommand;
import org.absharma.whitespace.node.ASTIOCommandOutputNumberInstr;
import org.absharma.whitespace.node.ASTOneLiteral;
import org.absharma.whitespace.node.ASTStackCommand;
import org.absharma.whitespace.node.ASTStackCommandPushInstr;
import org.absharma.whitespace.node.ASTZeroLiteral;
import org.absharma.whitespace.node.SimpleNode;
import org.absharma.whitespace.node.WhitespaceParserVisitor;

/**
 * User: absharma
 * Date: 4/4/13
 */
public class WhitespaceLegibleVisitor implements WhitespaceParserVisitor {

    @Override
    public Object visit(SimpleNode node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTCompilationUnit node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTStackCommand node, Object data) {
        System.out.print("STACK.");
        data = node.childrenAccept(this, data);
        System.out.println();
        return data;
    }

    @Override
    public Object visit(ASTStackCommandPushInstr node, Object data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            String bit = (String)((SimpleNode) node.jjtGetChild(i)).jjtGetValue();
            sb.append(bit);
        }
        System.out.print("PUSH " + Integer.parseInt(sb.toString(), 2));
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTIOCommand node, Object data) {
        System.out.print("IO.");
        data = node.childrenAccept(this, data);
        System.out.println();
        return data;
    }

    @Override
    public Object visit(ASTIOCommandOutputNumberInstr node, Object data) {
        System.out.print("OUT ");
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTZeroLiteral node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTOneLiteral node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTArithmeticCommand node, Object data) {
        System.out.print("MATH.");
        data = node.childrenAccept(this, data);
        System.out.println();
        return data;
    }

    @Override
    public Object visit(ASTArithmeticCommandAdditionInstr node, Object data) {
        System.out.print("+ ");
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTArithmeticCommandSubtractionInstr node, Object data) {
        System.out.print("- ");
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTArithmeticCommandMultiplicationInstr node, Object data) {
        System.out.print("* ");
        data = node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTArithmeticCommandDivisionInstr node, Object data) {
        System.out.print("/ ");
        data = node.childrenAccept(this, data);
        return data;
    }

    {

    }

}
