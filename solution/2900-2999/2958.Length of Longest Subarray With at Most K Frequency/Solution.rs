impl Solution {
    pub fn max_subarray_length(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut cnt = std::collections::HashMap::new();

        let mut l = 0;
        for r in 0..nums.len() {
            *cnt.entry(nums[r]).or_insert(0) += 1;

            while cnt[&nums[r]] > k {
                *cnt.get_mut(&nums[l]).unwrap() -= 1;
                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
