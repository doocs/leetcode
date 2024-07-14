import Collections

class KthLargest {
    private var h: Heap<Int>
    private var size: Int

    init(_ k: Int, _ nums: [Int]) {
        h = Heap()
        size = k
        for x in nums {
            add(x)
        }
    }
    
    func add(_ val: Int) -> Int {
        h.insert(val)
        if h.count > size {
            h.removeMin()
        }
        return h.min!
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * let obj = KthLargest(k, nums)
 * let ret_1: Int = obj.add(val)
 */
