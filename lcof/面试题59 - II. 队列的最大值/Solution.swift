class MaxQueue {
    private var q1: [Int] = []
    private var q2: [Int] = []
    
    init() {
    }
    
    func max_value() -> Int {
        return q2.isEmpty ? -1 : q2.first!
    }
    
    func push_back(_ value: Int) {
        q1.append(value)
        while !q2.isEmpty && q2.last! < value {
            q2.removeLast()
        }
        q2.append(value)
    }
    
    func pop_front() -> Int {
        if q1.isEmpty {
            return -1
        }
        let ans = q1.removeFirst()
        if q2.first == ans {
            q2.removeFirst()
        }
        return ans
    }
}