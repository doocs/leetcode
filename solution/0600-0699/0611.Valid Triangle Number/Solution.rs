impl Solution {
    pub fn triangle_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut res = 0;
        for i in (2..n).rev() {
            let mut left = 0;
            let mut right = i - 1;
            while left < right {
                if nums[left] + nums[right] > nums[i] {
                    res += right - left;
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }
        res as i32
    }
}
