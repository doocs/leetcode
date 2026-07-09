impl Solution {
    pub fn path_existence_queries(
        n: i32,
        nums: Vec<i32>,
        max_diff: i32,
        queries: Vec<Vec<i32>>,
    ) -> Vec<i32> {
        let n = n as usize;
        let mut pairs = Vec::with_capacity(n);
        for (i, &x) in nums.iter().enumerate() {
            pairs.push((x, i));
        }
        pairs.sort_unstable();

        let m = 20;
        let mut f = vec![vec![0; m]; n];

        let mut r = n - 1;
        for l in (0..n).rev() {
            while pairs[r].0 - pairs[l].0 > max_diff {
                r -= 1;
            }
            let (i, j) = (pairs[l].1, pairs[r].1);
            f[i][0] = j;
            for k in 1..m {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let (mut i, mut j) = (q[0] as usize, q[1] as usize);
            if nums[i] > nums[j] {
                std::mem::swap(&mut i, &mut j);
            }
            if i == j {
                ans.push(0);
                continue;
            }
            if nums[i] == nums[j] {
                ans.push(1);
                continue;
            }
            let mut d = 0;
            for k in (0..m).rev() {
                if nums[f[i][k]] < nums[j] {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if nums[f[i][0]] < nums[j] {
                ans.push(-1);
            } else {
                ans.push(d + 1);
            }
        }
        ans
    }
}
