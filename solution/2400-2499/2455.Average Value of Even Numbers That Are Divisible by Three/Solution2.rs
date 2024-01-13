impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let filtered_nums: Vec<i32> = nums
            .iter()
            .cloned()
            .filter(|&n| n % 6 == 0)
            .collect();

        if filtered_nums.is_empty() {
            return 0;
        }

        filtered_nums.iter().sum::<i32>() / (filtered_nums.len() as i32)
    }
}
