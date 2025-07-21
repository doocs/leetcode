use std::collections::HashSet;

impl Solution {
    pub fn maximum_unique_subarray(nums: Vec<i32>) -> i32 {
        let mut vis = HashSet::new();
        let (mut ans, mut s, mut i) = (0, 0, 0);

        for &x in &nums {
            while vis.contains(&x) {
                let y = nums[i];
                s -= y;
                vis.remove(&y);
                i += 1;
            }
            vis.insert(x);
            s += x;
            ans = ans.max(s);
        }

        ans
    }
}
