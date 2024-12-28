impl Solution {
    pub fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut dirs = vec![0, 1, 0, -1, 0];
        let mut vis = vec![vec![false; n]; m];
        let mut i = 0;
        let mut j = 0;
        let mut k = 0;
        let mut ans = Vec::new();

        for _ in 0..(m * n) {
            ans.push(matrix[i][j]);
            vis[i][j] = true;
            let x = i as i32 + dirs[k] as i32;
            let y = j as i32 + dirs[k + 1] as i32;

            if x < 0 || x >= m as i32 || y < 0 || y >= n as i32 || vis[x as usize][y as usize] {
                k = (k + 1) % 4;
            }

            i = (i as i32 + dirs[k] as i32) as usize;
            j = (j as i32 + dirs[k + 1] as i32) as usize;
        }

        ans
    }
}
