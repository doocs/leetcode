impl Solution {
    pub fn min_cost(
        start_pos: Vec<i32>,
        home_pos: Vec<i32>,
        row_costs: Vec<i32>,
        col_costs: Vec<i32>,
    ) -> i32 {
        let calc = |nums: &Vec<i32>, i: i32, j: i32| -> i32 {
            let mut res = 0;
            for k in i..=j {
                res += nums[k as usize];
            }
            res
        };

        let x0 = start_pos[0];
        let y0 = start_pos[1];
        let x1 = home_pos[0];
        let y1 = home_pos[1];

        let dx = if x0 < x1 {
            calc(&row_costs, x0 + 1, x1)
        } else {
            calc(&row_costs, x1, x0 - 1)
        };

        let dy = if y0 < y1 {
            calc(&col_costs, y0 + 1, y1)
        } else {
            calc(&col_costs, y1, y0 - 1)
        };

        dx + dy
    }
}
