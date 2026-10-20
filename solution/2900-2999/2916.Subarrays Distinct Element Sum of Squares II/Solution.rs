use std::collections::HashMap;

const MOD: i64 = 1_000_000_007;

#[derive(Clone, Copy)]
struct Node {
    l: i32,
    r: i32,
    sum: i64,
    sum_sq: i64,
    lazy: i32,
}

struct SegmentTree {
    tr: Vec<Node>,
}

impl SegmentTree {
    fn new(n: i32) -> Self {
        let mut st = Self {
            tr: vec![
                Node {
                    l: 0,
                    r: 0,
                    sum: 0,
                    sum_sq: 0,
                    lazy: 0,
                };
                (n as usize) << 2
            ],
        };
        st.build(1, 0, n - 1);
        st
    }

    fn build(&mut self, u: usize, l: i32, r: i32) {
        self.tr[u].l = l;
        self.tr[u].r = r;
        if l == r {
            return;
        }
        let mid = (l + r) >> 1;
        self.build(u << 1, l, mid);
        self.build(u << 1 | 1, mid + 1, r);
    }

    fn apply(&mut self, u: usize, v: i32) {
        let len = (self.tr[u].r - self.tr[u].l + 1) as i64;
        let add = v as i64;
        self.tr[u].sum_sq += 2 * add * self.tr[u].sum + add * add * len;
        self.tr[u].sum += add * len;
        self.tr[u].lazy += v;
    }

    fn pushdown(&mut self, u: usize) {
        if self.tr[u].lazy != 0 {
            let lazy = self.tr[u].lazy;
            self.apply(u << 1, lazy);
            self.apply(u << 1 | 1, lazy);
            self.tr[u].lazy = 0;
        }
    }

    fn pushup(&mut self, u: usize) {
        self.tr[u].sum = self.tr[u << 1].sum + self.tr[u << 1 | 1].sum;
        self.tr[u].sum_sq = self.tr[u << 1].sum_sq + self.tr[u << 1 | 1].sum_sq;
    }

    fn modify(&mut self, u: usize, l: i32, r: i32, v: i32) {
        if self.tr[u].l >= l && self.tr[u].r <= r {
            self.apply(u, v);
            return;
        }
        self.pushdown(u);
        let mid = (self.tr[u].l + self.tr[u].r) >> 1;
        if l <= mid {
            self.modify(u << 1, l, r, v);
        }
        if r > mid {
            self.modify(u << 1 | 1, l, r, v);
        }
        self.pushup(u);
    }
}

impl Solution {
    pub fn sum_counts(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut st = SegmentTree::new(n);
        let mut last = HashMap::new();
        let mut ans = 0_i64;
        for (i, &x) in nums.iter().enumerate() {
            let p = *last.get(&x).unwrap_or(&-1);
            st.modify(1, p + 1, i as i32, 1);
            last.insert(x, i as i32);
            ans = (ans + st.tr[1].sum_sq) % MOD;
        }
        ans as i32
    }
}
