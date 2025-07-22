class Node {
    var left: Node?
    var right: Node?
    let l: Int
    let r: Int
    let mid: Int
    var v = 0
    var add = 0
    
    init(_ l: Int, _ r: Int) {
        self.l = l
        self.r = r
        self.mid = (l + r) >> 1
    }
}

class SegmentTree {
    private var root: Node
    private let MOD = 1_000_000_007
    
    init(_ n: Int) {
        root = Node(1, n)
    }
    
    func modify(_ l: Int, _ r: Int, _ v: Int) {
        modify(l, r, v, root)
    }
    
    private func modify(_ l: Int, _ r: Int, _ v: Int, _ node: Node) {
        if l > r {
            return
        }
        if node.l >= l && node.r <= r {
            node.v = (node.v + (node.r - node.l + 1) * v) % MOD
            node.add = (node.add + v) % MOD
            return
        }
        pushdown(node)
        if l <= node.mid {
            modify(l, r, v, node.left!)
        }
        if r > node.mid {
            modify(l, r, v, node.right!)
        }
        pushup(node)
    }
    
    func query(_ l: Int, _ r: Int) -> Int {
        return query(l, r, root)
    }
    
    private func query(_ l: Int, _ r: Int, _ node: Node) -> Int {
        if l > r {
            return 0
        }
        if node.l >= l && node.r <= r {
            return node.v
        }
        pushdown(node)
        var v = 0
        if l <= node.mid {
            v = (v + query(l, r, node.left!)) % MOD
        }
        if r > node.mid {
            v = (v + query(l, r, node.right!)) % MOD
        }
        return v
    }
    
    private func pushup(_ node: Node) {
        node.v = (node.left!.v + node.right!.v) % MOD
    }
    
    private func pushdown(_ node: Node) {
        if node.left == nil {
            node.left = Node(node.l, node.mid)
        }
        if node.right == nil {
            node.right = Node(node.mid + 1, node.r)
        }
        if node.add != 0 {
            let left = node.left!, right = node.right!
            left.v = (left.v + (left.r - left.l + 1) * node.add) % MOD
            right.v = (right.v + (right.r - right.l + 1) * node.add) % MOD
            left.add = (left.add + node.add) % MOD
            right.add = (right.add + node.add) % MOD
            node.add = 0
        }
    }
}

class Solution {
    private var g: [[Int]] = []
    private var begin: [Int] = []
    private var end: [Int] = []
    private var idx = 1
    
    func bonus(_ n: Int, _ leadership: [[Int]], _ operations: [[Int]]) -> [Int] {
        g = Array(repeating: [], count: n + 1)
        for l in leadership {
            let (a, b) = (l[0], l[1])
            g[a].append(b)
        }
        
        begin = Array(repeating: 0, count: n + 1)
        end = Array(repeating: 0, count: n + 1)
        idx = 1
        dfs(1)
        
        var ans: [Int] = []
        let tree = SegmentTree(n)
        for op in operations {
            let (p, v) = (op[0], op[1])
            if p == 1 {
                tree.modify(begin[v], begin[v], op[2])
            } else if p == 2 {
                tree.modify(begin[v], end[v], op[2])
            } else if p == 3 {
                ans.append(tree.query(begin[v], end[v]))
            }
        }
        return ans
    }
    
    private func dfs(_ u: Int) {
        begin[u] = idx
        for v in g[u] {
            dfs(v)
        }
        end[u] = idx
        idx += 1
    }
}