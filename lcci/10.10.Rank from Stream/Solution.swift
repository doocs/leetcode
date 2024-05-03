
class BinaryIndexedTree {
    private var n: Int
    private var c: [Int]

    init(_ n: Int) {
        self.n = n
        self.c = Array(repeating: 0, count: n + 1)
    }

    func update(_ x: Int, _ delta: Int) {
        var idx = x
        while idx <= n {
            c[idx] += delta
            idx += (idx & -idx)
        }
    }

    func query(_ x: Int) -> Int {
        var sum = 0
        var idx = x
        while idx > 0 {
            sum += c[idx]
            idx -= (idx & -idx)
        }
        return sum
    }
}

class StreamRank {
    private var tree: BinaryIndexedTree

    init() {
        tree = BinaryIndexedTree(50010)
    }

    func track(_ x: Int) {
        tree.update(x + 1, 1)
    }

    func getRankOfNumber(_ x: Int) -> Int {
        return tree.query(x + 1)
    }
}

