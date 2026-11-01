impl Solution {
    pub fn min_moves(nums: Vec<i32>) -> i32 {
        let (s, mi) = nums
            .iter()
            .fold((0i64, i32::MAX), |(s, mi), &x| (s + x as i64, mi.min(x)));
        (s - mi as i64 * nums.len() as i64) as i32
    }
}
