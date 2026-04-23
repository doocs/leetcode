impl Solution {
    pub fn min_operations(grid: Vec<Vec<i32>>, x: i32) -> i32 {
        let mut nums: Vec<i32> = grid.into_iter().flatten().collect();
        let mod_val = nums[0] % x;

        if nums.iter().any(|&num| num % x != mod_val) {
            return -1;
        }

        nums.sort_unstable();

        let mid = nums[nums.len() / 2];
        nums.iter().fold(0, |acc, &num| acc + (num - mid).abs() / x)
    }
}
