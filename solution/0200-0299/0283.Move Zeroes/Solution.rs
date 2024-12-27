impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut k = 0;
        let n = nums.len();
        for i in 0..n {
            if nums[i] != 0 {
                nums.swap(i, k);
                k += 1;
            }
        }
    }
}
