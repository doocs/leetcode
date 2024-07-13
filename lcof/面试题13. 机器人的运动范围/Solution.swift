class Solution {
    private var vis: [[Bool]] = []
    private var m: Int = 0
    private var n: Int = 0
    private var k: Int = 0

    func movingCount(_ m: Int, _ n: Int, _ k: Int) -> Int {
        self.m = m
        self.n = n
        self.k = k
        self.vis = Array(repeating: Array(repeating: false, count: n), count: m)
        return dfs(0, 0)
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        if i >= m || j >= n || vis[i][j] || (digitSum(i) + digitSum(j)) > k {
            return 0
        }
        vis[i][j] = true
        return 1 + dfs(i + 1, j) + dfs(i, j + 1)
    }

    private func digitSum(_ num: Int) -> Int {
        var num = num
        var sum = 0
        while num > 0 {
            sum += num % 10
            num /= 10
        }
        return sum
    }
}
