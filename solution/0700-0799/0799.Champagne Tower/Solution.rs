impl Solution {
    pub fn champagne_tower(poured: i32, query_row: i32, query_glass: i32) -> f64 {
        let query_row = query_row as usize;
        let query_glass = query_glass as usize;
        let mut row = vec![poured as f64];
        for i in 1..=query_row {
            let mut next_row = vec![0f64; i + 1];
            for j in 0..i {
                if row[j] > 1f64 {
                    next_row[j] += (row[j] - 1f64) / 2f64;
                    next_row[j + 1] += (row[j] - 1f64) / 2f64;
                }
            }
            row = next_row;
        }
        1f64.min(row[query_glass])
    }
}
