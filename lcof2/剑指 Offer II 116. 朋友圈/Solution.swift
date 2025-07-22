class Solution {
    private var isConnected: [[Int]] = []
    private var visited: [Bool] = []
    private var n: Int = 0

    func findCircleNum(_ isConnected: [[Int]]) -> Int {
        self.isConnected = isConnected
        self.n = isConnected.count
        self.visited = [Bool](repeating: false, count: n)
        var numberOfCircles = 0

        for i in 0..<n {
            if !visited[i] {
                dfs(i)
                numberOfCircles += 1
            }
        }
        return numberOfCircles
    }

    private func dfs(_ i: Int) {
        visited[i] = true
        for j in 0..<n {
            if !visited[j] && isConnected[i][j] == 1 {
                dfs(j)
            }
        }
    }
}