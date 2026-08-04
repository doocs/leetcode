struct BinaryIndexedTree {
    n: usize,
    c: Vec<i32>,
}

impl BinaryIndexedTree {
    fn new(n: usize) -> Self {
        Self {
            n,
            c: vec![0; n + 1],
        }
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

impl Solution {
    pub fn count_ratio_subarrays(nums: Vec<i32>, a: i32, b: i32) -> i64 {
        let n = nums.len();

        let mut s = vec![0i64; n + 1];

        for i in 0..n {
            s[i + 1] = s[i]
                + if nums[i] % 2 == 1 {
                    a as i64
                } else {
                    -(b as i64)
                };
        }

        let mut st = s.clone();
        st.sort_unstable();
        st.dedup();

        let mut bit = BinaryIndexedTree::new(st.len() + 1);

        let mut ans = 0i64;

        for v in s {
            let x = match st.binary_search(&v) {
                Ok(i) => i,
                Err(i) => i,
            } + 1;

            ans += bit.query(x) as i64;
            bit.update(x, 1);
        }

        ans
    }
}
