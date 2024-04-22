class MyQueue {
    private var stk1: [Int] = []
    private var stk2: [Int] = []

    init() {}

    func push(_ x: Int) {
        stk1.append(x)
    }

    @discardableResult
    func pop() -> Int {
        move()
        return stk2.removeLast()
    }

    func peek() -> Int {
        move()
        return stk2.last!
    }

    func empty() -> Bool {
        return stk1.isEmpty && stk2.isEmpty
    }

    private func move() {
        if stk2.isEmpty {
            while !stk1.isEmpty {
                stk2.append(stk1.removeLast())
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * let obj = new MyQueue();
 * obj.push(x);
 * let param_2 = obj.pop();
 * let param_3 = obj.peek();
 * var myValue : Bool 
 * myValue = obj.empty();
 */