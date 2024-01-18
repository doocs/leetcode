public class CQueue {
    private Stack<int> stk1 = new Stack<int>();
    private Stack<int> stk2 = new Stack<int>();

    public CQueue() {

    }

    public void AppendTail(int value) {
        stk1.Push(value);
    }

    public int DeleteHead() {
        if (stk2.Count == 0) {
            while (stk1.Count != 0) {
                stk2.Push(stk1.Pop());
            }
        }
        return stk2.Count == 0 ? -1 : stk2.Pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.AppendTail(value);
 * int param_2 = obj.DeleteHead();
 */
