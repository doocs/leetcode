class Solution {
    func majorityElement(_ nums: [Int]) -> Int {
        var count = 0
        var candidate: Int?

        for num in nums {
            if count == 0 {
                candidate = num
                count = 1
            } else if let candidate = candidate, candidate == num {
                count += 1
            } else {
                count -= 1
            }
        }

        count = 0
        if let candidate = candidate {
            for num in nums {
                if num == candidate {
                    count += 1
                }
            }
            if count > nums.count / 2 {
                return candidate
            }
        }

        return -1
    }
}