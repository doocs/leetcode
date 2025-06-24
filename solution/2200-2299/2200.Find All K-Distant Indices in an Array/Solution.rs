impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut ans = Vec::new();
        for i in 0..n {
            for j in 0..n {
                if (i as i32 - j as i32).abs() <= k && nums[j] == key {
                    ans.push(i as i32);
                    break;
                }
            }
        }
        ans
    }
}
