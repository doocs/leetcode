impl Solution {
    #[allow(dead_code)]
    pub fn min_total_distance(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let m = grid[0].len();

        let mut row_vec = Vec::new();
        let mut col_vec = Vec::new();

        // Initialize the two vector
        for i in 0..n {
            for j in 0..m {
                if grid[i][j] == 1 {
                    row_vec.push(i as i32);
                    col_vec.push(j as i32);
                }
            }
        }

        // Since the row vector is originally sorted, we only need to sort the col vector here
        col_vec.sort();

        Self::compute_manhattan_dis(&row_vec, row_vec[row_vec.len() / 2]) +
            Self::compute_manhattan_dis(&col_vec, col_vec[col_vec.len() / 2])
    }

    #[allow(dead_code)]
    fn compute_manhattan_dis(v: &Vec<i32>, e: i32) -> i32 {
        let mut ret = 0;

        for num in v {
            ret += (num - e).abs();
        }

        ret
    }
}
