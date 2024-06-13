class KthLargest {
    private var minHeap: [Int]
    private var size: Int

    init(_ k: Int, _ nums: [Int]) {
        size = k
        minHeap = []
        for num in nums {
            add(num)
        }
    }

    func add(_ val: Int) -> Int {
        if minHeap.count < size {
            minHeap.append(val)
            minHeap.sort()
        } else if val > minHeap[0] {
            minHeap[0] = val
            minHeap.sort()
        }
        return minHeap[0]
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * let obj = KthLargest(k, nums)
 * let ret = obj.add(val)
 */