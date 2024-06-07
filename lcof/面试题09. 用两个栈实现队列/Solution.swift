class CQueue {
    private var stk1: [Int] = []
    private var stk2: [Int] = []

    init() {
    }

    func appendTail(_ value: Int) {
        stk1.append(value)
    }

    func deleteHead() -> Int {
        if stk2.isEmpty {
            while !stk1.isEmpty {
                stk2.append(stk1.removeLast())
            }
        }
        return stk2.isEmpty ? -1 : stk2.removeLast()
    }
}