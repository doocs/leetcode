impl Solution {
    pub fn max_subsequence(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let k = k as usize;
        let mut idx: Vec<usize> = (0..n).collect();

        idx.sort_by_key(|&i| nums[i]);
        idx[n - k..].sort();

        let mut ans = Vec::with_capacity(k);
        for i in n - k..n {
            ans.push(nums[idx[i]]);
        }

        ans
    }
}
