class BinaryIndexedTree {
    private var n: Int
    private var c: [Int]

    init(_ n: Int) {
        self.n = n
        self.c = [Int](repeating: 0, count: n + 1)
    }

    func update(_ x: Int, _ val: Int) {
        var x = x
        while x <= n {
            c[x] = max(c[x], val)
            x += x & -x
        }
    }

    func query(_ x: Int) -> Int {
        var x = x
        var s = 0
        while x > 0 {
            s = max(s, c[x])
            x -= x & -x
        }
        return s
    }
}

class Solution {
    func bestSeqAtIndex(_ height: [Int], _ weight: [Int]) -> Int {
        let n = height.count
        var arr: [(Int, Int)] = []
        for i in 0..<n {
            arr.append((height[i], weight[i]))
        }
        arr.sort {
            if $0.0 == $1.0 {
                return $1.1 < $0.1
            }
            return $0.0 < $1.0
        }

        let weights = Set(arr.map { $1 })
        let sortedWeights = Array(weights).sorted()
        let m = sortedWeights.enumerated().reduce(into: [Int: Int]()) {
            $0[$1.element] = $1.offset + 1
        }

        let tree = BinaryIndexedTree(sortedWeights.count)
        var ans = 1
        for (_, w) in arr {
            let x = m[w]!
            let t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        }
        return ans
    }
}
