impl Solution {
    pub fn champagne_tower(poured: i32, query_row: i32, query_glass: i32) -> f64 {
        let mut f = vec![poured as f64];
        for i in 1..=query_row {
            let mut g = vec![0.0; (i + 1) as usize];
            for j in 0..i as usize {
                if f[j] > 1.0 {
                    let half = (f[j] - 1.0) / 2.0;
                    g[j] += half;
                    g[j + 1] += half;
                }
            }
            f = g;
        }
        f[query_glass as usize].min(1.0)
    }
}