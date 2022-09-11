use std::cmp::Ordering;
impl Solution {
    pub fn special_array(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut left = 0;
        let mut right = n + 1;
        while left < right {
            let mid = left + (right - left) / 2;
            let mut count = 0;
            for &num in nums.iter() {
                if num >= mid {
                    count += 1;
                }
            }
            match count.cmp(&mid) {
                Ordering::Equal => {
                    return mid;
                }
                Ordering::Less => {
                    right = mid;
                }
                Ordering::Greater => {
                    left = mid + 1;
                }
            }
        }
        -1
    }
}
