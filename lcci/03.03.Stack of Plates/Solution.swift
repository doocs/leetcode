class StackOfPlates {
    private var stacks: [[Int]]
    private var cap: Int

    init(_ cap: Int) {
        self.cap = cap
        self.stacks = []
    }

    func push(_ val: Int) {
        if cap == 0 {
            return
        }
        if stacks.isEmpty || stacks.last!.count >= cap {
            stacks.append([])
        }
        stacks[stacks.count - 1].append(val)
    }

    func pop() -> Int {
        return popAt(stacks.count - 1)
    }

    func popAt(_ index: Int) -> Int {
        guard index >= 0, index < stacks.count, !stacks[index].isEmpty else {
            return -1
        }
        let value = stacks[index].removeLast()
        if stacks[index].isEmpty {
            stacks.remove(at: index)
        }
        return value
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * let obj = new StackOfPlates(cap);
 * obj.push(val);
 * let param_2 = obj.pop();
 * let param_3 = obj.popAt(index);
 */