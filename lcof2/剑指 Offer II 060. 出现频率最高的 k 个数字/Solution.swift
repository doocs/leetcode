class Solution {
    func topKFrequent(_ nums: [Int], _ k: Int) -> [Int] {
        var frequency: [Int: Int] = [:]
        
        for num in nums {
            frequency[num, default: 0] += 1
        }
        
        var queue = PriorityQueue<(Int, Int)>(sort: { $0.1 < $1.1 })
        
        for entry in frequency {
            queue.enqueue((entry.key, entry.value))
            if queue.count > k {
                _ = queue.dequeue()
            }
        }
        
        return queue.toArray().map { $0.0 }
    }
}

public struct PriorityQueue<Element> {
    private var heap: [Element]
    private let sort: (Element, Element) -> Bool
    
    public init(sort: @escaping (Element, Element) -> Bool) {
        self.heap = []
        self.sort = sort
    }
    
    public var isEmpty: Bool {
        return heap.isEmpty
    }
    
    public var count: Int {
        return heap.count
    }
    
    public func peek() -> Element? {
        return heap.first
    }
    
    public mutating func enqueue(_ element: Element) {
        heap.append(element)
        siftUp(heap.count - 1)
    }
    
    public mutating func dequeue() -> Element? {
        guard !heap.isEmpty else { return nil }
        heap.swapAt(0, heap.count - 1)
        let element = heap.removeLast()
        siftDown(0)
        return element
    }
    
    public func toArray() -> [Element] {
        return heap
    }
    
    private mutating func siftUp(_ index: Int) {
        var index = index
        while index > 0 {
            let parentIndex = (index - 1) / 2
            if sort(heap[index], heap[parentIndex]) {
                heap.swapAt(index, parentIndex)
                index = parentIndex
            } else {
                return
            }
        }
    }
    
    private mutating func siftDown(_ index: Int) {
        var index = index
        let count = heap.count
        let element = heap[index]
        
        while true {
            let leftChildIndex = 2 * index + 1
            let rightChildIndex = 2 * index + 2
            var first = index
            
            if leftChildIndex < count && sort(heap[leftChildIndex], heap[first]) {
                first = leftChildIndex
            }
            if rightChildIndex < count && sort(heap[rightChildIndex], heap[first]) {
                first = rightChildIndex
            }
            if first == index { return }
            
            heap[index] = heap[first]
            heap[first] = element
            index = first
        }
    }
}

