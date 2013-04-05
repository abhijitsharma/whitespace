package org.absharma.whitespace;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: absharma
 * Date: 4/4/13
 */
public class WhitespaceParserTests {

    @Test
    public void testBasic() {
        String s;
        WhitespaceEvaluator evaluator = new WhitespaceEvaluator();
        WhitespaceEvaluator.StackHeap stackHeap = new WhitespaceEvaluator.StackHeap();

        // Push 001 to Stack (S S SSTL)
        // Output Number @ top of Stack TL ST
        s = "SSSSTLTLST";
        evaluator.evaluate(s, stackHeap);
        Assert.assertEquals(stackHeap.stack.peek(), 1);

        // Push 001 to Stack (S S SSTL), and then
        // Push 010 to Stack (S S STSL)
        // Output Number @ top of Stack (TL ST)
        stackHeap = new WhitespaceEvaluator.StackHeap();
        s = "SSSSTLSSSTSLTLST";
        evaluator.evaluate(s, stackHeap);
        Assert.assertEquals(stackHeap.stack.peek(), 2);

        // Push 001 to Stack (S S SSTL), and then
        // Push 010 to Stack (S S STSL)
        // Add the 2 numbers on top of stack(TS SS)
        // Output Number @ top of Stack (TL ST)
        stackHeap = new WhitespaceEvaluator.StackHeap();
        s = "SSSSTLSSSTSLTSSSTLST";
        evaluator.evaluate(s, stackHeap);
        Assert.assertEquals(stackHeap.stack.peek(), 3);
        // Push 001 to Stack (S S SSTL), and then
        // Push 010 to Stack (S S STSL)
        // Push 110 to Stack (S S TTSL)
        // Add the 2 numbers on top of stack(TS SS)
        // Mult the 2 numbers on top of stack(TS SL)
        // Output Number @ top of Stack (TL ST)
        s = "SSSSTLSSSTSLSSTTSLTSSSTSSLTLST";
        stackHeap = new WhitespaceEvaluator.StackHeap();
        evaluator.evaluate(s, stackHeap);
        Assert.assertEquals(stackHeap.stack.peek(), 8);
    }
}
