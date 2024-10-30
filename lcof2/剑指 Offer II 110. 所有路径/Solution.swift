class Solution {
    private var results = [[Int]]()
    private var graph = [[Int]]()

    func allPathsSourceTarget(_ graph: [[Int]]) -> [[Int]] {
        self.graph = graph
        var path = [0]
        dfs(0, &path)
        return results
    }

    private func dfs(_ node: Int, _ path: inout [Int]) {
        if node == graph.count - 1 {
            results.append(Array(path))
            return
        }
        
        for next in graph[node] {
            path.append(next)
            dfs(next, &path)
            path.removeLast()
        }
    }
}
