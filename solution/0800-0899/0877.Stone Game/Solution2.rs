impl Solution {
    pub fn stone_game(piles: Vec<i32>) -> bool {
        let n = piles.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = piles[i];
        }

        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                f[i][j] = (piles[i] - f[i + 1][j])
                    .max(piles[j] - f[i][j - 1]);
            }
        }

        f[0][n - 1] > 0
    }
}
