impl Solution {
    pub fn generate_matrix(n: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![vec![0; n as usize]; n as usize];
        let dirs = [0, 1, 0, -1, 0];
        let (mut i, mut j, mut k) = (0, 0, 0);
        for v in 1..=n * n {
            ans[i as usize][j as usize] = v;
            let (x, y) = (i + dirs[k], j + dirs[k + 1]);
            if x < 0 || x >= n || y < 0 || y >= n || ans[x as usize][y as usize] != 0 {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        ans
    }
}
