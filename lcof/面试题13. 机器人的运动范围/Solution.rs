impl Solution {
    fn dfs(sign: &mut Vec<Vec<bool>>, k: usize, i: usize, j: usize) -> i32 {
        if i == sign.len()
            || j == sign[0].len()
            || sign[i][j]
            || j % 10 + j / 10 % 10 + i % 10 + i / 10 % 10 > k
        {
            return 0;
        }
        sign[i][j] = true;
        1 + Self::dfs(sign, k, i + 1, j) + Self::dfs(sign, k, i, j + 1)
    }

    pub fn moving_count(m: i32, n: i32, k: i32) -> i32 {
        let mut sign = vec![vec![false; n as usize]; m as usize];
        Self::dfs(&mut sign, k as usize, 0, 0)
    }
}
