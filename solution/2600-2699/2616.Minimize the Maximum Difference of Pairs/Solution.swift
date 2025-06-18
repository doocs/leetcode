class Solution {
    func minimizeMax(_ nums: [Int], _ p: Int) -> Int {
        var nums = nums.sorted()
        let n = nums.count
        var l = 0
        var r = nums[n - 1] - nums[0] + 1

        func check(_ diff: Int) -> Bool {
            var cnt = 0
            var i = 0
            while i < n - 1 {
                if nums[i + 1] - nums[i] <= diff {
                    cnt += 1
                    i += 2
                } else {
                    i += 1
                }
            }
            return cnt >= p
        }

        while l < r {
            let mid = (l + r) >> 1
            if check(mid) {
                r = mid
            } else {
                l = mid + 1
            }
        }

        return l
    }
}
