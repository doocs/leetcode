class SegmentTree {
    var nums: [Int]
    var tr: [Int]

    init(_ nums: [Int]) {
        self.nums = nums
        let n = nums.count
        self.tr = [Int](repeating: 0, count: n << 2)
        build(1, 1, n)
    }

    func build(_ u: Int, _ l: Int, _ r: Int) {
        if l == r {
            tr[u] = nums[l - 1]
            return
        }
        let mid = (l + r) >> 1
        build(u << 1, l, mid)
        build(u << 1 | 1, mid + 1, r)
        pushup(u)
    }

    func modify(_ u: Int, _ l: Int, _ r: Int, _ i: Int, _ v: Int) {
        if l == r {
            tr[u] = v
            return
        }
        let mid = (l + r) >> 1
        if i <= mid {
            modify(u << 1, l, mid, i, v)
        } else {
            modify(u << 1 | 1, mid + 1, r, i, v)
        }
        pushup(u)
    }

    func query(_ u: Int, _ l: Int, _ r: Int, _ v: Int) -> Int {
        if tr[u] < v {
            return -1
        }
        if l == r {
            return l
        }
        let mid = (l + r) >> 1
        if tr[u << 1] >= v {
            return query(u << 1, l, mid, v)
        }
        return query(u << 1 | 1, mid + 1, r, v)
    }

    func pushup(_ u: Int) {
        tr[u] = max(tr[u << 1], tr[u << 1 | 1])
    }
}

class Solution {
    func numOfUnplacedFruits(_ fruits: [Int], _ baskets: [Int]) -> Int {
        let tree = SegmentTree(baskets)
        let n = baskets.count
        var ans = 0
        for x in fruits {
            let i = tree.query(1, 1, n, x)
            if i < 0 {
                ans += 1
            } else {
                tree.modify(1, 1, n, i, 0)
            }
        }
        return ans
    }
}
