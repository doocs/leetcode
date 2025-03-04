class Solution {
    func transportationHub(_ path: [[Int]]) -> Int {
        var inDegree = [Int: Int]()
        var outDegree = [Int: Int]()
        var nodeSet = Set<Int>()
        var visitedEdges = Set<String>()

        for p in path {
            let a = p[0]
            let b = p[1]
            let edgeKey = "\(a)-\(b)"

            if !visitedEdges.contains(edgeKey) {
                visitedEdges.insert(edgeKey)
                nodeSet.insert(a)
                nodeSet.insert(b)
                inDegree[b, default: 0] += 1
                outDegree[a, default: 0] += 1
            }
        }

        for node in nodeSet {
            if inDegree[node, default: 0] == nodeSet.count - 1 && outDegree[node, default: 0] == 0 {
                return node
            }
        }

        return -1
    }
}