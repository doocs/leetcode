public class CQueue {
    private Stack<int> stack1;
    private Stack<int> stack2;

    public CQueue() {
        stack1 = new Stack<int>();
        stack2 = new Stack<int>();
    }
    
    public void AppendTail(int value) {
        stack1.Push(value);
    }
    
    public int DeleteHead() {
        if (stack2.Count == 0) {
            while (stack1.Count != 0) {
                stack2.Push(stack1.Pop());
            }
        }
        return stack2.Count == 0 ? -1 : stack2.Pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.AppendTail(value);
 * int param_2 = obj.DeleteHead();
 */