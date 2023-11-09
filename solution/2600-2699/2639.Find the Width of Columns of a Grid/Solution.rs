impl Solution {
    pub fn find_column_width(grid: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans = vec![0; grid[0].len()];

        for row in grid.iter() {
            for (j, num) in row.iter().enumerate() {
                let width = num.to_string().len() as i32;
                ans[j] = std::cmp::max(ans[j], width);
            }
        }

        ans
    }
}
