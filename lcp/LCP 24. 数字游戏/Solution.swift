class MedianFinder {
    private var lowerHeap: [Int] = []
    private var upperHeap: [Int] = []
    private let mod = 1_000_000_007
    private var sumLower = 0
    private var sumUpper = 0

    init() {}

    func addNum(_ num: Int) {
    
        upperHeap.append(num)
        sumUpper += num
        upperHeap.sort()

    
        if let minUpper = upperHeap.first {
            upperHeap.removeFirst()
            lowerHeap.append(minUpper)
            sumUpper -= minUpper
            sumLower += minUpper
            lowerHeap.sort(by: >)
        }

    
        if lowerHeap.count > upperHeap.count + 1 {
            if let maxLower = lowerHeap.first {
                lowerHeap.removeFirst()
                upperHeap.append(maxLower)
                sumLower -= maxLower
                sumUpper += maxLower
                upperHeap.sort()
            }
        }
    }

    func findMedian() -> Int {
        if lowerHeap.count > upperHeap.count {
            return lowerHeap.first ?? 0
        } else if let minUpper = upperHeap.first, let maxLower = lowerHeap.first {
            return (minUpper + maxLower) / 2
        }
        return 0
    }

    func cal() -> Int {
        let median = findMedian()
        var result = (sumUpper - median * upperHeap.count) + (median * lowerHeap.count - sumLower)
        result %= mod
        if result < 0 {
            result += mod
        }
        return Int(result)
    }
}

class Solution {
    func numsGame(_ nums: [Int]) -> [Int] {
        let n = nums.count
        var result = [Int]()
        let finder = MedianFinder()

        for i in 0..<n {
            finder.addNum(nums[i] - i)
            result.append(finder.cal())
        }

        return result
    }
}