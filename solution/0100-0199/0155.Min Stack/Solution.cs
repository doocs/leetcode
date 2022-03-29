using System.Collections.Generic;

public class MinStack {
    private Stack<int> _stack = new Stack<int>();
    private Stack<int> _minStack = new Stack<int>();

    public void Push(int x) {
        _stack.Push(x);
        if (GetMin() >= x) _minStack.Push(x);
    }

    public void Pop() {
        var x = _stack.Pop();
        if (GetMin() == x) _minStack.Pop();
    }

    public int Top() {
        return _stack.Peek();
    }

    public int GetMin() {
        if (_minStack.Count == 0) return int.MaxValue;
        return _minStack.Peek();
    }
}