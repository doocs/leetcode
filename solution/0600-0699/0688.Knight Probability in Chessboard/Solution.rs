const DIR: [(i32, i32); 8] = [
    (-2, -1),
    (2, -1),
    (-1, -2),
    (1, -2),
    (2, 1),
    (-2, 1),
    (1, 2),
    (-1, 2),
];
const P: f64 = 1.0 / 8.0;

impl Solution {
    #[allow(dead_code)]
    pub fn knight_probability(n: i32, k: i32, row: i32, column: i32) -> f64 {
        // Here dp[i][j][k] represents through `i` steps, the probability that the knight stays on the board
        // Starts from row: `j`, column: `k`
        let mut dp: Vec<Vec<Vec<f64>>> =
            vec![vec![vec![0 as f64; n as usize]; n as usize]; k as usize + 1];

        // Initialize the dp vector, since dp[0][j][k] should be 1
        for j in 0..n as usize {
            for k in 0..n as usize {
                dp[0][j][k] = 1.0;
            }
        }

        // Begin the actual dp process
        for i in 1..=k {
            for j in 0..n {
                for k in 0..n {
                    for (dx, dy) in DIR {
                        let x = j + dx;
                        let y = k + dy;
                        if Self::check_bounds(x, y, n, n) {
                            dp[i as usize][j as usize][k as usize] +=
                                P * dp[(i as usize) - 1][x as usize][y as usize];
                        }
                    }
                }
            }
        }

        dp[k as usize][row as usize][column as usize]
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
