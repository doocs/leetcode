class Solution {
    func containsNearbyAlmostDuplicate(_ nums: [Int], _ k: Int, _ t: Int) -> Bool {
        guard nums.count > 1, k > 0, t >= 0 else { return false }

        var ts = SortedDictionary<Int64, Int64>()
        for i in 0..<nums.count {
            let num = Int64(nums[i])
            if let x = ts.ceilingKey(num - Int64(t)), abs(x - num) <= Int64(t) {
                return true
            }
            ts.updateValue(num, forKey: num)
            if i >= k {
                ts.removeValue(forKey: Int64(nums[i - k]))
            }
        }
        return false
    }
}

public struct SortedDictionary<Key: Comparable & Hashable, Value> {
    private var keys = [Key]()
    private var values = [Key: Value]()

    public var isEmpty: Bool {
        return keys.isEmpty
    }

    public mutating func updateValue(_ value: Value, forKey key: Key) {
        if values[key] == nil {
            keys.append(key)
            keys.sort()
        }
        values[key] = value
    }

    public mutating func removeValue(forKey key: Key) {
        if values.removeValue(forKey: key) != nil {
            keys.removeAll { $0 == key }
        }
    }

    public func ceilingKey(_ key: Key) -> Key? {
        for k in keys {
            if k >= key {
                return k
            }
        }
        return nil
    }
}