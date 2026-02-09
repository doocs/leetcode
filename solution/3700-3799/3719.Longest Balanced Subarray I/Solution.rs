use std::collections::HashSet;

impl Solution {
    pub fn longest_balanced(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans: i32 = 0;

        for i in 0..n {
            let mut vis: HashSet<i32> = HashSet::new();
            let mut cnt = [0i32; 2];

            for j in i..n {
                if !vis.contains(&nums[j]) {
                    vis.insert(nums[j]);
                    let idx = (nums[j] & 1) as usize;
                    cnt[idx] += 1;
                }
                if cnt[0] == cnt[1] {
                    ans = ans.max((j - i + 1) as i32);
                }
            }
        }

        ans
    }
}
