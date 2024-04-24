class TripleInOne {
    private var cap: Int
    private var stk: [Int]

    init(_ stackSize: Int) {
        self.cap = stackSize
        self.stk = [Int](repeating: 0, count: cap * 3 + 3)
    }

    func push(_ stackNum: Int, _ value: Int) {
        if stk[cap * 3 + stackNum] < cap {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value
            stk[cap * 3 + stackNum] += 1
        }
    }

    func pop(_ stackNum: Int) -> Int {
        if isEmpty(stackNum) {
            return -1
        }
        stk[cap * 3 + stackNum] -= 1
        return stk[cap * stackNum + stk[cap * 3 + stackNum]]
    }

    func peek(_ stackNum: Int) -> Int {
        if isEmpty(stackNum) {
            return -1
        }
        return stk[cap * stackNum + stk[cap * 3 + stackNum] - 1]
    }

    func isEmpty(_ stackNum: Int) -> Bool {
        return stk[cap * 3 + stackNum] == 0
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * let obj = TripleInOne(stackSize)
 * obj.push(stackNum,value)
 * let param_2 = obj.pop(stackNum)
 * let param_3 = obj.peek(stackNum)
 * let param_4 = obj.isEmpty(stackNum)
 */