impl Solution {
    #[allow(dead_code)]
    pub fn find_duplicate(nums: Vec<i32>) -> i32 {
        let mut left = 0;
        let mut right = nums.len() - 1;

        while left < right {
            let mid = (left + right) >> 1;
            let cnt = nums
                .iter()
                .filter(|x| **x <= mid as i32)
                .count();
            if cnt > mid {
                right = mid;
            } else  {
                left = mid + 1;
            }
        }

        left as i32
    }
}