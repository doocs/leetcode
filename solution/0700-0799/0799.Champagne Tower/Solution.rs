impl Solution {
    pub fn champagne_tower(poured: i32, query_row: i32, query_glass: i32) -> f64 {
        let mut f = vec![vec![0.0; 101]; 101];
        f[0][0] = poured as f64;
        for i in 0..=query_row as usize {
            for j in 0..=i {
                if f[i][j] > 1.0 {
                    let half = (f[i][j] - 1.0) / 2.0;
                    f[i][j] = 1.0;
                    f[i + 1][j] += half;
                    f[i + 1][j + 1] += half;
                }
            }
        }
        f[query_row as usize][query_glass as usize]
    }
}