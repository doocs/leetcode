class Solution {
    private var presum: [Int]

    init(_ w: [Int]) {
        let n = w.count
        presum = [Int](repeating: 0, count: n + 1)
        for i in 0..<n {
            presum[i + 1] = presum[i] + w[i]
        }
    }

    func pickIndex() -> Int {
        let n = presum.count
        let x = Int.random(in: 1...presum[n - 1])
        var left = 0
        var right = n - 2
        while left < right {
            let mid = (left + right) >> 1
            if presum[mid + 1] >= x {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * let w = [1]
 * let solution = Solution(w)
 * solution.pickIndex()
 */