impl Solution {
    pub fn rotate(nums: &mut Vec<i32>, k: i32) {
        let n = nums.len();
        let k = k as usize % n;
        if n == 1 || k == 0 {
            return;
        }

        nums.reverse();
        nums[..k].reverse();
        nums[k..].reverse();
    }
}
