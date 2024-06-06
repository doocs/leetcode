class MovingAverage {
    private var arr: [Int]
    private var s: Int
    private var cnt: Int
    
    init(_ size: Int) {
        arr = [Int](repeating: 0, count: size)
        s = 0
        cnt = 0
    }
    
    func next(_ val: Int) -> Double {
        let idx = cnt % arr.count
        s += val - arr[idx]
        arr[idx] = val
        cnt += 1
        return Double(s) / Double(min(cnt, arr.count))
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * let obj = MovingAverage(size)
 * let param_1 = obj.next(val)
 */