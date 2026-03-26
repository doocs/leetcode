impl Solution {
    pub fn stone_game(piles: Vec<i32>) -> bool {
        let n = piles.len();
        let mut f = vec![vec![0; n]; n];

        fn dfs(i: usize, j: usize, piles: &Vec<i32>, f: &mut Vec<Vec<i32>>) -> i32 {
            if i == j {
                return piles[i]
            }
            if f[i][j] != 0 {
                return f[i][j];
            }

            let res = (piles[i] - dfs(i + 1, j, piles, f))
                .max(piles[j] - dfs(i, j - 1, piles, f));

            f[i][j] = res;
            res
        }

        dfs(0, n - 1, &piles, &mut f) > 0
    }
}
