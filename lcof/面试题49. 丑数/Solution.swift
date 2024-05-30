class Solution {
    func nthUglyNumber(_ n: Int) -> Int {
        var vis = Set<Int64>()
        var pq = PriorityQueue<Int64>()
        let factors: [Int64] = [2, 3, 5]
        
        pq.push(1)
        vis.insert(1)
        var ans: Int64 = 0
        
        for _ in 0..<n {
            ans = pq.pop()!
            for factor in factors {
                let next = ans * factor
                if vis.insert(next).inserted {
                    pq.push(next)
                }
            }
        }
        
        return Int(ans)
    }
}

struct PriorityQueue<T: Comparable> {
    private var heap: [T] = []
    
    var isEmpty: Bool {
        return heap.isEmpty
    }
    
    mutating func push(_ element: T) {
        heap.append(element)
        heapifyUp(from: heap.count - 1)
    }
    
    mutating func pop() -> T? {
        guard !heap.isEmpty else {
            return nil
        }
        if heap.count == 1 {
            return heap.removeLast()
        }
        let value = heap[0]
        heap[0] = heap.removeLast()
        heapifyDown(from: 0)
        return value
    }
    
    private mutating func heapifyUp(from index: Int) {
        var index = index
        let element = heap[index]
        while index > 0 {
            let parentIndex = (index - 1) / 2
            if element >= heap[parentIndex] {
                break
            }
            heap[index] = heap[parentIndex]
            index = parentIndex
        }
        heap[index] = element
    }
    
    private mutating func heapifyDown(from index: Int) {
        var index = index
        let element = heap[index]
        let count = heap.count
        while index < count / 2 {
            var childIndex = index * 2 + 1
            if childIndex + 1 < count && heap[childIndex + 1] < heap[childIndex] {
                childIndex += 1
            }
            if element <= heap[childIndex] {
                break
            }
            heap[index] = heap[childIndex]
            index = childIndex
        }
        heap[index] = element
    }
}