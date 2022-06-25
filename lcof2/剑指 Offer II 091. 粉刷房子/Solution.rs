impl Solution {
    pub fn min_cost(costs: Vec<Vec<i32>>) -> i32 {
        let mut dp = [0, 0, 0];
        for cost in costs.iter() {
            dp = [
                cost[0] + dp[1].min(dp[2]),
                cost[1] + dp[0].min(dp[2]),
                cost[2] + dp[0].min(dp[1]),
            ];
        }
        *dp.iter().min().unwrap()
    }
}
