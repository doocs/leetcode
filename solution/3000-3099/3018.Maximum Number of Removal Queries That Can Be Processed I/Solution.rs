impl Solution {
    pub fn maximum_processable_queries(nums: Vec<i32>, queries: Vec<i32>) -> i32 {
        let n = nums.len();
        let m = queries.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            for j in (i..n).rev() {
                if i > 0 {
                    let idx = f[i - 1][j] as usize;
                    if idx < m {
                        f[i][j] = f[i][j]
                            .max(f[i - 1][j] + if nums[i - 1] >= queries[idx] { 1 } else { 0 });
                    }
                }
                if j + 1 < n {
                    let idx = f[i][j + 1] as usize;
                    if idx < m {
                        f[i][j] = f[i][j]
                            .max(f[i][j + 1] + if nums[j + 1] >= queries[idx] { 1 } else { 0 });
                    }
                }
                if f[i][j] as usize == m {
                    return m as i32;
                }
            }
        }

        let mut ans = 0;
        for i in 0..n {
            let idx = f[i][i] as usize;
            if idx < m {
                ans = ans.max(f[i][i] + if nums[i] >= queries[idx] { 1 } else { 0 });
            } else {
                ans = ans.max(f[i][i]);
            }
        }

        ans
    }
}
