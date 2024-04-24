class Solution {
    private var g: [[Int]]!
    private var vis: [Bool]!
    private var target: Int!

    func findWhetherExistsPath(_ n: Int, _ graph: [[Int]], _ start: Int, _ target: Int) -> Bool {
        vis = [Bool](repeating: false, count: n)
        g = [[Int]](repeating: [], count: n)
        self.target = target
        for e in graph {
            g[e[0]].append(e[1])
        }
        return dfs(start)
    }

    private func dfs(_ i: Int) -> Bool {
        if i == target {
            return true
        }
        if vis[i] {
            return false
        }
        vis[i] = true
        for j in g[i] {
            if dfs(j) {
                return true
            }
        }
        return false
    }
}
