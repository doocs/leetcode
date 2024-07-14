class SortedStack {
    private var stk: [Int] = []

    init() {}

    func push(_ val: Int) {
        var temp: [Int] = []
        while let top = stk.last, top < val {
            temp.append(stk.removeLast())
        }
        stk.append(val)
        while let last = temp.popLast() {
            stk.append(last)
        }
    }

    func pop() {
        if !isEmpty() {
            stk.removeLast()
        }
    }

    func peek() -> Int {
        return isEmpty() ? -1 : stk.last!
    }

    func isEmpty() -> Bool {
        return stk.isEmpty
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * let obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * let param_3 = obj.peek();
 * var myVar: Bool;
 * myVar = obj.isEmpty();
 */
