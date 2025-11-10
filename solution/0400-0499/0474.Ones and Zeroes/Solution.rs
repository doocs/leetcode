impl Solution {
    pub fn find_max_form(strs: Vec<String>, m: i32, n: i32) -> i32 {
        let sz = strs.len();
        let m = m as usize;
        let n = n as usize;
        let mut f = vec![vec![vec![0; n + 1]; m + 1]; sz + 1];
        for i in 1..=sz {
            let a = strs[i - 1].chars().filter(|&c| c == '0').count();
            let b = strs[i - 1].len() - a;
            for j in 0..=m {
                for k in 0..=n {
                    f[i][j][k] = f[i - 1][j][k];
                    if j >= a && k >= b {
                        f[i][j][k] = f[i][j][k].max(f[i - 1][j - a][k - b] + 1);
                    }
                }
            }
        }
        f[sz][m][n] as i32
    }
}
