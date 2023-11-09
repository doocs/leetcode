impl Solution {
    #[allow(dead_code)]
    pub fn largest_subarray(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut ret_vec = vec![i32::MIN];
        let n = nums.len();

        if n == (k as usize) {
            return nums;
        }

        for i in 0..=n - (k as usize) {
            if nums[i] > ret_vec[0] {
                ret_vec = nums[i..i + (k as usize)].to_vec();
            }
        }

        ret_vec
    }
}
