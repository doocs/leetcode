class MedianFinder {
    private var lowerHalf = [Int]()
    private var upperHalf = [Int]()

    init() {}

    func addNum(_ num: Int) {
        if lowerHalf.count > upperHalf.count {
            lowerHalf.append(num)
            lowerHalf.sort(by: >)
            upperHalf.append(lowerHalf.removeFirst())
        } else {
            upperHalf.append(num)
            upperHalf.sort()
            lowerHalf.append(upperHalf.removeFirst())
        }
    }

    func findMedian() -> Double {
        if lowerHalf.count > upperHalf.count {
            return Double(lowerHalf.first!)
        } else {
            return (Double(lowerHalf.first!) + Double(upperHalf.first!)) / 2.0
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder();
 * obj.addNum(num);
 * let param_2 = obj.findMedian();
 */