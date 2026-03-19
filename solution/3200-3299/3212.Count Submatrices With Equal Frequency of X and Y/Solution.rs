impl Solution {
    pub fn number_of_submatrices(grid: Vec<Vec<char>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut s = vec![vec![vec![0i32; 2]; n + 1]; m + 1];
        let mut ans = 0;

        for i in 1..=m {
            for j in 1..=n {
                s[i][j][0] = s[i - 1][j][0]
                    + s[i][j - 1][0]
                    - s[i - 1][j - 1][0]
                    + if grid[i - 1][j - 1] == 'X' { 1 } else { 0 };

                s[i][j][1] = s[i - 1][j][1]
                    + s[i][j - 1][1]
                    - s[i - 1][j - 1][1]
                    + if grid[i - 1][j - 1] == 'Y' { 1 } else { 0 };

                if s[i][j][0] > 0 && s[i][j][0] == s[i][j][1] {
                    ans += 1;
                }
            }
        }

        ans
    }
}
