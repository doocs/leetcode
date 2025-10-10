impl Solution {
    pub fn count_of_peaks(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        struct BinaryIndexedTree {
            n: usize,
            c: Vec<i32>,
        }

        impl BinaryIndexedTree {
            fn new(n: usize) -> Self {
                Self { n, c: vec![0; n + 1] }
            }

            fn update(&mut self, mut x: usize, delta: i32) {
                while x <= self.n {
                    self.c[x] += delta;
                    x += x & (!x + 1);
                }
            }

            fn query(&self, mut x: usize) -> i32 {
                let mut s = 0;
                while x > 0 {
                    s += self.c[x];
                    x &= x - 1;
                }
                s
            }
        }

        let n = nums.len();
        let mut tree = BinaryIndexedTree::new(n - 1);

        let mut update = |i: usize, val: i32, nums: &Vec<i32>, tree: &mut BinaryIndexedTree| {
            if i == 0 || i >= n - 1 {
                return;
            }
            if nums[i - 1] < nums[i] && nums[i] > nums[i + 1] {
                tree.update(i, val);
            }
        };

        for i in 1..n - 1 {
            update(i, 1, &nums, &mut tree);
        }

        let mut ans = Vec::new();
        for q in queries {
            if q[0] == 1 {
                let l = (q[1] + 1).max(1) as usize;
                let r = (q[2] - 1).max(0) as usize;
                if l > r {
                    ans.push(0);
                } else {
                    ans.push(tree.query(r) - tree.query(l - 1));
                }
            } else {
                let idx = q[1] as usize;
                let val = q[2];
                let left = if idx > 0 { idx - 1 } else { 0 };
                let right = usize::min(idx + 1, n - 1);
                for i in left..=right {
                    update(i, -1, &nums, &mut tree);
                }
                nums[idx] = val;
                for i in left..=right {
                    update(i, 1, &nums, &mut tree);
                }
            }
        }

        ans
    }
}
