impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mid = nums[nums.len() / 2];
        let mut res = 0;
        for num in nums.iter() {
            res += (num - mid).abs();
        }
        res
    }
}
