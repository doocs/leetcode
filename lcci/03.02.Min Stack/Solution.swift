class MinStack {
    private var stk1: [Int]
    private var stk2: [Int]

    init() {
        stk1 = []
        stk2 = [Int.max]
    }

    func push(_ x: Int) {
        stk1.append(x)

        stk2.append(min(x, stk2.last!))
    }

    func pop() {
        stk1.removeLast()
        stk2.removeLast()
    }

    func top() -> Int {
        return stk1.last!
    }

    func getMin() -> Int {
        return stk2.last!
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