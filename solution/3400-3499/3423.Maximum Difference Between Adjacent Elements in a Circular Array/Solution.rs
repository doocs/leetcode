impl Solution {
    pub fn max_adjacent_distance(nums: Vec<i32>) -> i32 {
        nums.iter()
            .zip(nums.iter().cycle().skip(1))
            .take(nums.len())
            .map(|(a, b)| (*a - *b).abs())
            .max()
            .unwrap_or(0)
    }
}
