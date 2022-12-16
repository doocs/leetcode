impl Solution {
    pub fn min_elements(nums: Vec<i32>, limit: i32, goal: i32) -> i32 {
        let limit = limit as i64;
        let goal = goal as i64;
        let mut sum = 0;
        for &num in nums.iter() {
            sum += num as i64;
        }
        let diff = (goal - sum).abs();
        ((diff + limit - 1) / limit) as i32
    }
}
