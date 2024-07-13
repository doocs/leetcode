class MinStack {
    private var stack: [Int]
    private var minStack: [Int]

    init() {
        stack = []
        minStack = [Int.max]
    }

    func push(_ x: Int) {
        stack.append(x)
        minStack.append(min(x, minStack.last!))
    }

    func pop() {
        stack.removeLast()
        minStack.removeLast()
    }

    func top() -> Int {
        return stack.last!
    }

    func getMin() -> Int {
        return minStack.last!
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack();
 * obj.push(x);
 * obj.pop();
 * let param_3 = obj.top();
 * let param_4 = obj.getMin();
 */