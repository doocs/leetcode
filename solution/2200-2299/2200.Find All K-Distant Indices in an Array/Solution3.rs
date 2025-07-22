impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut ans = Vec::new();
        let mut j = 0;
        for i in 0..n {
            while j < i.saturating_sub(k as usize) || (j < n && nums[j] != key) {
                j += 1;
            }
            if j < n && j <= i + k as usize {
                ans.push(i as i32);
            }
        }
        ans
    }
}
