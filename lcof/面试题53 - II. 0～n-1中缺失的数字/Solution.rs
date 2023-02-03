impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() as i32);
        while l < r {
            let mut mid = (l + r) >> 1;
            if nums[mid as usize] > mid {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}