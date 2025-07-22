use std::collections::HashMap;

impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let mut d = HashMap::new();
        for &x in &nums {
            d.insert(x, 1);
        }
        let cnt = d.len();
        let mut ans = 0;
        let n = nums.len();
        d.clear();

        let (mut i, mut j) = (0, 0);
        while j < n {
            *d.entry(nums[j]).or_insert(0) += 1;
            while d.len() == cnt {
                ans += (n - j) as i32;
                let e = d.get_mut(&nums[i]).unwrap();
                *e -= 1;
                if *e == 0 {
                    d.remove(&nums[i]);
                }
                i += 1;
            }
            j += 1;
        }
        ans
    }
}
