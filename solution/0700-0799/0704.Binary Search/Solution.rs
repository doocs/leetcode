use std::cmp::Ordering;

impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut l = 0;
        let mut r = nums.len();
        while l < r {
            let mid = l + r >> 1;
            match nums[mid].cmp(&target) {
                Ordering::Less => l = mid + 1,
                Ordering::Greater => r = mid,
                Ordering::Equal => return mid as i32,
            }
        }
        -1
    }
}
