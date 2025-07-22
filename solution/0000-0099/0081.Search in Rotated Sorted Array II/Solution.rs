impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> bool {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[r] {
                if nums[l] <= target && target <= nums[mid] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if nums[mid] < nums[r] {
                if nums[mid] < target && target <= nums[r] {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                r -= 1;
            }
        }
        nums[l] == target
    }
}
