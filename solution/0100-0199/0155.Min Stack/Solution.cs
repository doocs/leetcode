public class MinStack {
    private Stack<int> stk1 = new Stack<int>();
    private Stack<int> stk2 = new Stack<int>();
    
    public MinStack() {
        stk2.Push(int.MaxValue);
    }
    
    public void Push(int x) {
        stk1.Push(x);
        stk2.Push(Math.Min(x, GetMin()));
    }
    
    public void Pop() {
        stk1.Pop();
        stk2.Pop();
    }
    
    public int Top() {
        return stk1.Peek();
    }
    
    public int GetMin() {
        return stk2.Peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.GetMin();
 */