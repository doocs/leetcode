class Solution {
    private var parent: [Int] = []

    func findRedundantConnection(_ edges: [[Int]]) -> [Int] {
        parent = Array(0..<1010)  
        
        for edge in edges {
            let a = edge[0]
            let b = edge[1]
            
            if find(a) == find(b) {
                return edge
            }
            parent[find(a)] = find(b)
        }
        return []
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            parent[x] = find(parent[x])  
        }
        return parent[x]
    }
}