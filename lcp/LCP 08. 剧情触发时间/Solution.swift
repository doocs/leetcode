class Solution {
    func getTriggerTime(_ increase: [[Int]], _ requirements: [[Int]]) -> [Int] {
        let m = increase.count, n = requirements.count
        var s = Array(repeating: [0, 0, 0], count: m + 1)

        for i in 0..<m {
            for j in 0..<3 {
                s[i + 1][j] = s[i][j] + increase[i][j]
            }
        }

        var ans = Array(repeating: -1, count: n)
        for i in 0..<n {
            var left = 0, right = m + 1
            while left < right {
                let mid = (left + right) / 2
                if check(s[mid], requirements[i]) {
                    ans[i] = mid
                    right = mid
                } else {
                    left = mid + 1
                }
            }
        }
        return ans
    }

    private func check(_ a: [Int], _ b: [Int]) -> Bool {
        for i in 0..<3 {
            if a[i] < b[i] {
                return false
            }
        }
        return true
    }
}