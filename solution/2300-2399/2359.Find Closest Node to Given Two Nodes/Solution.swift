class Solution {
    func closestMeetingNode(_ edges: [Int], _ node1: Int, _ node2: Int) -> Int {
        let n = edges.count
        var g = [[Int]](repeating: [], count: n)
        for i in 0..<n {
            if edges[i] != -1 {
                g[i].append(edges[i])
            }
        }
        let inf = 1 << 30

        func f(_ i: Int) -> [Int] {
            var dist = [Int](repeating: inf, count: n)
            dist[i] = 0
            var q = [i]
            var idx = 0
            while idx < q.count {
                let i = q[idx]
                idx += 1
                for j in g[i] {
                    if dist[j] == inf {
                        dist[j] = dist[i] + 1
                        q.append(j)
                    }
                }
            }
            return dist
        }

        let d1 = f(node1)
        let d2 = f(node2)
        var ans = -1, d = inf
        for i in 0..<n {
            let t = max(d1[i], d2[i])
            if t < d {
                d = t
                ans = i
            }
        }
        return ans
    }
}
