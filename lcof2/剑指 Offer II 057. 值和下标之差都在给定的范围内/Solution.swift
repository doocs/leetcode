class Solution {
    func containsNearbyAlmostDuplicate(_ nums: [Int], _ k: Int, _ t: Int) -> Bool {
        guard nums.count > 1, k > 0, t >= 0 else { return false }

        var ts = SortedSet<Int64>()
        for i in 0..<nums.count {
            let num = Int64(nums[i])
            if let x = ts.ceiling(num - Int64(t)), x <= num + Int64(t) {
                return true
            }
            ts.insert(num)
            if i >= k {
                ts.remove(Int64(nums[i - k]))
            }
        }
        return false
    }
}

struct SortedSet<Element: Comparable> {
    private var elements = [Element]()
    
    mutating func insert(_ value: Element) {
        elements.append(value)
        elements.sort()
    }
    
    mutating func remove(_ value: Element) {
        if let index = elements.firstIndex(of: value) {
            elements.remove(at: index)
        }
    }
    
    func ceiling(_ value: Element) -> Element? {
        for element in elements {
            if element >= value {
                return element
            }
        }
        return nil
    }
}