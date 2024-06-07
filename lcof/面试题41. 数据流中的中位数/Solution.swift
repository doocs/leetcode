class MedianFinder {
    private var lowerHalf = Heap<Int>(sort: >)
    private var upperHalf = Heap<Int>(sort: <)

    init() {}

    func addNum(_ num: Int) {
        if lowerHalf.count == 0 || num <= lowerHalf.peek()! {
            lowerHalf.insert(num)
        } else {
            upperHalf.insert(num)
        }

        if lowerHalf.count > upperHalf.count + 1 {
            upperHalf.insert(lowerHalf.remove()!)
        } else if upperHalf.count > lowerHalf.count {
            lowerHalf.insert(upperHalf.remove()!)
        }
    }

    func findMedian() -> Double {
        if lowerHalf.count > upperHalf.count {
            return Double(lowerHalf.peek()!)
        } else {
            return (Double(lowerHalf.peek()!) + Double(upperHalf.peek()!)) / 2.0
        }
    }
}

struct Heap<T> {
    var elements: [T]
    let sort: (T, T) -> Bool

    init(sort: @escaping (T, T) -> Bool) {
        self.sort = sort
        self.elements = []
    }

    var count: Int {
        return elements.count
    }

    func peek() -> T? {
        return elements.first
    }

    mutating func insert(_ value: T) {
        elements.append(value)
        siftUp(from: elements.count - 1)
    }

    mutating func remove() -> T? {
        guard !elements.isEmpty else { return nil }
        if elements.count == 1 {
            return elements.removeLast()
        } else {
            let value = elements[0]
            elements[0] = elements.removeLast()
            siftDown(from: 0)
            return value
        }
    }

    private mutating func siftUp(from index: Int) {
        var child = index
        var parent = parentIndex(of: child)
        while child > 0 && sort(elements[child], elements[parent]) {
            elements.swapAt(child, parent)
            child = parent
            parent = self.parentIndex(of: child)
        }
    }

    private mutating func siftDown(from index: Int) {
        var parent = index
        while true {
            let left = leftChildIndex(of: parent)
            let right = rightChildIndex(of: parent)
            var candidate = parent
            if left < elements.count && sort(elements[left], elements[candidate]) {
                candidate = left
            }
            if right < elements.count && sort(elements[right], elements[candidate]) {
                candidate = right
            }
            if candidate == parent {
                return
            }
            elements.swapAt(parent, candidate)
            parent = candidate
        }
    }

    private func parentIndex(of index: Int) -> Int {
        return (index - 1) / 2
    }

    private func leftChildIndex(of index: Int) -> Int {
        return 2 * index + 1
    }

    private func rightChildIndex(of index: Int) -> Int {
        return 2 * index + 2
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder();
 * obj.addNum(num);
 * let param_2 = obj.findMedian();
 */