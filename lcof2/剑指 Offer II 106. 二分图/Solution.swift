class Solution {
    private var parent: [Int] = []

    func isBipartite(_ graph: [[Int]]) -> Bool {
        let n = graph.count
        parent = Array(0..<n)

        for u in 0..<n {
            for v in graph[u] {
                if find(u) == find(v) {
                    return false
                }
                parent[find(v)] = find(graph[u][0])
            }
        }
        return true
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
}