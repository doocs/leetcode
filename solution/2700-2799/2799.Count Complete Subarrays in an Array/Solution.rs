use std::collections::HashMap;
use std::collections::HashSet;
impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let m = nums.iter().collect::<HashSet<&i32>>().len();
        let mut map = HashMap::new();
        let mut ans = 0;
        let mut i = 0;
        for j in 0..n {
            *map.entry(nums[j]).or_insert(0) += 1;
            while map.len() == m {
                ans += n - j;
                let v = map.entry(nums[i]).or_default();
                *v -= 1;
                if *v == 0 {
                    map.remove(&nums[i]);
                }
                i += 1;
            }
        }
        ans as i32
    }
}
