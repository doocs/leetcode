impl Solution {
    pub fn find_unsorted_subarray(nums: Vec<i32>) -> i32 {
        let mut arr = nums.clone();
        arr.sort();
        let mut l = 0usize;
        while l < nums.len() && nums[l] == arr[l] {
            l += 1;
        }
        if l == nums.len() {
            return 0;
        }
        let mut r = nums.len() - 1;
        while r > l && nums[r] == arr[r] {
            r -= 1;
        }
        (r - l + 1) as i32
    }
}
