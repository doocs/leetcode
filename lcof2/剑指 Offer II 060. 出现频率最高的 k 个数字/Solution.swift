import HeapModule

class Solution {
    func topKFrequent(_ nums: [Int], _ k: Int) -> [Int] {
        var frequency: [Int: Int] = [:]
        for num in nums {
            frequency[num, default: 0] += 1
        }
        
        var freqHeap = Heap<FreqElement>()
        for (key, value) in frequency {
            freqHeap.insert(.init(val: key, freq: value))
            if freqHeap.count > k {
                freqHeap.removeMin()
            }
        }
        var ans = [Int]()
        while let element = freqHeap.popMax() {
            ans.append(element.val)
        }
        return ans
    }
}

struct FreqElement: Comparable {
    let val: Int
    let freq: Int

    static func < (lhs: FreqElement, rhs: FreqElement) -> Bool {
        lhs.freq < rhs.freq
    }

    static func == (lhs: FreqElement, rhs: FreqElement) -> Bool {
        lhs.freq == rhs.freq
    }
}
