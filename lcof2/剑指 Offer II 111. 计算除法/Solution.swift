class Solution {
    private var parent = [Int]()
    private var weight = [Double]()
    
    func calcEquation(
        _ equations: [[String]], 
        _ values: [Double], 
        _ queries: [[String]]
    ) -> [Double] {
        let n = equations.count
        parent = Array(0..<(n * 2))
        weight = Array(repeating: 1.0, count: n * 2)
        
        var map = [String: Int]()
        var index = 0
        
        for i in 0..<n {
            let a = equations[i][0]
            let b = equations[i][1]
            
            if map[a] == nil {
                map[a] = index
                index += 1
            }
            if map[b] == nil {
                map[b] = index
                index += 1
            }
            
            let pa = find(map[a]!)
            let pb = find(map[b]!)
            
            if pa != pb {
                parent[pa] = pb
                weight[pa] = weight[map[b]!] * values[i] / weight[map[a]!]
            }
        }
        
        var result = [Double]()
        
        for query in queries {
            let (c, d) = (query[0], query[1])
            if let id1 = map[c], let id2 = map[d], find(id1) == find(id2) {
                result.append(weight[id1] / weight[id2])
            } else {
                result.append(-1.0)
            }
        }
        
        return result
    }
    
    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            let origin = parent[x]
            parent[x] = find(parent[x])
            weight[x] *= weight[origin]
        }
        return parent[x]
    }
}