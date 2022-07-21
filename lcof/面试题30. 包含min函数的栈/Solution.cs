public class MinStack {
    Stack<int> stack;
    Stack<int> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<int>();
        minStack = new Stack<int>();
        minStack.Push(int.MaxValue);
    }
    
    public void Push(int x) {
        stack.Push(x);
        minStack.Push(Math.Min(minStack.Peek(), x));
    }
    
    public void Pop() {
        stack.Pop();
        minStack.Pop();
    }
    
    public int Top() {
        return stack.Peek();
    }
    
    public int Min() {
        return minStack.Peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.Min();
 */