impl Solution {
    #[allow(dead_code)]
    pub fn paint_walls(cost: Vec<i32>, time: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut record_vec: Vec<Vec<i32>> = vec![vec![-1; n << 1 | 1]; n];
        Self::dfs(&mut record_vec, 0, n as i32, n as i32, &time, &cost)
    }

    #[allow(dead_code)]
    fn dfs(
        record_vec: &mut Vec<Vec<i32>>,
        i: i32,
        j: i32,
        n: i32,
        time: &Vec<i32>,
        cost: &Vec<i32>
    ) -> i32 {
        if n - i <= j - n {
            // All the remaining walls can be printed at no cost
            // Just return 0
            return 0;
        }
        if i >= n {
            // No way this case can be achieved
            // Just return +INF
            return 1 << 30;
        }
        if record_vec[i as usize][j as usize] == -1 {
            // This record hasn't been written
            record_vec[i as usize][j as usize] = std::cmp::min(
                Self::dfs(record_vec, i + 1, j + time[i as usize], n, time, cost) +
                    cost[i as usize],
                Self::dfs(record_vec, i + 1, j - 1, n, time, cost)
            );
        }
        record_vec[i as usize][j as usize]
    }
}
