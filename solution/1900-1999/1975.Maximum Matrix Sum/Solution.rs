impl Solution {
    pub fn max_matrix_sum(matrix: Vec<Vec<i32>>) -> i64 {
        let mut s = 0;
        let mut mi = i32::MAX;
        let mut cnt = 0;
        for row in matrix {
            for &x in row.iter() {
                cnt += if x < 0 { 1 } else { 0 };
                let y = x.abs();
                mi = mi.min(y);
                s += y as i64;
            }
        }
        if cnt % 2 == 0 {
            s
        } else {
            s - (mi as i64 * 2)
        }
    }
}
