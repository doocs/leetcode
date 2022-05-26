impl Solution {
    pub fn minimum_difference(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let k = k as usize;
        let mut res = i32::MAX;
        for i in 0..=nums.len() - k {
            res = res.min(nums[i + k - 1] - nums[i]);
        }
        res
    }
}
