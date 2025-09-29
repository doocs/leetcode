impl Solution {
    pub fn triangular_sum(mut nums: Vec<i32>) -> i32 {
        let mut k = nums.len() as i32 - 1;
        while k > 0 {
            for i in 0..k as usize {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            k -= 1;
        }
        nums[0]
    }
}
