impl Solution {
    pub fn min_space_wasted_k_resizing(nums: Vec<i32>, k: i32) -> i32 {
        let mut k = k + 1;
        let n = nums.len();
        let mut g = vec![vec![0; n]; n];

        for i in 0..n {
            let (mut s, mut mx) = (0, 0);
            for j in i..n {
                s += nums[j];
                mx = mx.max(nums[j]);
                g[i][j] = mx * (j as i32 - i as i32 + 1) - s;
            }
        }

        let inf = 0x3f3f3f3f;
        let mut f = vec![vec![inf; (k + 1) as usize]; n + 1];
        f[0][0] = 0;

        for i in 1..=n {
            for j in 1..=k as usize {
                for h in 0..i {
                    f[i][j] = f[i][j].min(f[h][j - 1] + g[h][i - 1]);
                }
            }
        }

        f[n][k as usize]
    }
}
