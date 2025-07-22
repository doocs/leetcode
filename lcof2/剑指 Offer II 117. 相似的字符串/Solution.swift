class Solution {
    private var parent: [Int] = []

    func numSimilarGroups(_ strs: [String]) -> Int {
        let n = strs.count
        parent = Array(0..<n)

        for i in 0..<n {
            for j in (i + 1)..<n {
                if check(strs[i], strs[j]) {
                    parent[find(i)] = find(j)
                }
            }
        }

        var groups = 0
        for i in 0..<n {
            if i == find(i) {
                groups += 1
            }
        }
        return groups
    }

    private func check(_ a: String, _ b: String) -> Bool {
        let n = a.count
        var count = 0
        let arrA = Array(a), arrB = Array(b)

        for i in 0..<n {
            if arrA[i] != arrB[i] {
                count += 1
            }
            if count > 2 {
                return false
            }
        }
        return count <= 2
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
}