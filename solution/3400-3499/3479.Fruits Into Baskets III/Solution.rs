struct SegmentTree<'a> {
    nums: &'a [i32],
    tr: Vec<i32>,
}

impl<'a> SegmentTree<'a> {
    fn new(nums: &'a [i32]) -> Self {
        let n = nums.len();
        let mut tree = SegmentTree {
            nums,
            tr: vec![0; n * 4],
        };
        tree.build(1, 1, n);
        tree
    }

    fn build(&mut self, u: usize, l: usize, r: usize) {
        if l == r {
            self.tr[u] = self.nums[l - 1];
            return;
        }
        let mid = (l + r) >> 1;
        self.build(u * 2, l, mid);
        self.build(u * 2 + 1, mid + 1, r);
        self.pushup(u);
    }

    fn modify(&mut self, u: usize, l: usize, r: usize, i: usize, v: i32) {
        if l == r {
            self.tr[u] = v;
            return;
        }
        let mid = (l + r) >> 1;
        if i <= mid {
            self.modify(u * 2, l, mid, i, v);
        } else {
            self.modify(u * 2 + 1, mid + 1, r, i, v);
        }
        self.pushup(u);
    }

    fn query(&self, u: usize, l: usize, r: usize, v: i32) -> i32 {
        if self.tr[u] < v {
            return -1;
        }
        if l == r {
            return l as i32;
        }
        let mid = (l + r) >> 1;
        if self.tr[u * 2] >= v {
            return self.query(u * 2, l, mid, v);
        }
        self.query(u * 2 + 1, mid + 1, r, v)
    }

    fn pushup(&mut self, u: usize) {
        self.tr[u] = self.tr[u * 2].max(self.tr[u * 2 + 1]);
    }
}

impl Solution {
    pub fn num_of_unplaced_fruits(fruits: Vec<i32>, baskets: Vec<i32>) -> i32 {
        let mut tree = SegmentTree::new(&baskets);
        let n = baskets.len();
        let mut ans = 0;
        for &x in fruits.iter() {
            let i = tree.query(1, 1, n, x);
            if i < 0 {
                ans += 1;
            } else {
                tree.modify(1, 1, n, i as usize, 0);
            }
        }
        ans
    }
}
