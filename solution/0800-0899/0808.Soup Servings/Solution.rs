impl Solution {
    pub fn soup_servings(n: i32) -> f64 {
        if n > 4800 {
            return 1.0;
        }
        Self::dfs((n + 24) / 25, (n + 24) / 25)
    }

    fn dfs(i: i32, j: i32) -> f64 {
        static mut F: [[f64; 200]; 200] = [[0.0; 200]; 200];

        unsafe {
            if i <= 0 && j <= 0 {
                return 0.5;
            }
            if i <= 0 {
                return 1.0;
            }
            if j <= 0 {
                return 0.0;
            }
            if F[i as usize][j as usize] > 0.0 {
                return F[i as usize][j as usize];
            }

            let ans = 0.25
                * (Self::dfs(i - 4, j)
                    + Self::dfs(i - 3, j - 1)
                    + Self::dfs(i - 2, j - 2)
                    + Self::dfs(i - 1, j - 3));
            F[i as usize][j as usize] = ans;
            ans
        }
    }
}
