impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = 0;
        let mut i = 0;
        while i < n - 1 {
            if nums[i] == nums[i + 1] {
                res += 1;
                i += 1;
            } else {
                i += 2;
            }
        }
        if (n - res) % 2 == 1 {
            res += 1;
        }
        res as i32
    }
}
