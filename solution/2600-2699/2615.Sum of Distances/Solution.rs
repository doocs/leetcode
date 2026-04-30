use std::collections::HashMap;

impl Solution {
    pub fn distance(nums: Vec<i32>) -> Vec<i64> {
        let n = nums.len();
        let mut ans = vec![0i64; n];
        let mut d: HashMap<i32, Vec<usize>> = HashMap::new();
        for i in 0..n {
            d.entry(nums[i]).or_insert(Vec::new()).push(i);
        }
        for idx in d.values() {
            let m = idx.len();
            let mut left = 0i64;
            let mut right = 0i64;
            for &i in idx {
                right += i as i64;
            }
            right -= m as i64 * idx[0] as i64;
            for i in 0..m {
                ans[idx[i]] = left + right;
                if i + 1 < m {
                    let diff = (idx[i + 1] - idx[i]) as i64;
                    left += diff * (i + 1) as i64;
                    right -= diff * (m - i - 1) as i64;
                }
            }
        }
        ans
    }
}
