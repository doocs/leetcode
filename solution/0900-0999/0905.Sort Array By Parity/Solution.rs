impl Solution {
    pub fn sort_array_by_parity(mut nums: Vec<i32>) -> Vec<i32> {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            while l < r && nums[l] & 1 == 0 {
                l += 1;
            }
            while l < r && nums[r] & 1 == 1 {
                r -= 1;
            }
            nums.swap(l, r);
        }
        nums
    }
}
