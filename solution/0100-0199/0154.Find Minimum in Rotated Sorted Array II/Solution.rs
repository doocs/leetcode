impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[r] {
                l = mid + 1;
            } else if nums[mid] == nums[r] {
                r -= 1;
            } else {
                r = mid;
            }
        }
        nums[l]
    }
}
