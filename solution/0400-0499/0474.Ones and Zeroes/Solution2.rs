impl Solution {
    pub fn find_max_form(strs: Vec<String>, m: i32, n: i32) -> i32 {
        let m = m as usize;
        let n = n as usize;
        let mut f = vec![vec![0; n + 1]; m + 1];

        for s in strs {
            let a = s.chars().filter(|&c| c == '0').count();
            let b = s.len() - a;
            for i in (a..=m).rev() {
                for j in (b..=n).rev() {
                    f[i][j] = f[i][j].max(f[i - a][j - b] + 1);
                }
            }
        }

        f[m][n]
    }
}
