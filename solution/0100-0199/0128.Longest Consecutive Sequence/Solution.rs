use std::collections::HashSet;

impl Solution {
    #[allow(dead_code)]
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut s = HashSet::new();
        let mut ret = 0;

        // Initialize the set
        for num in &nums {
            s.insert(*num);
        }

        for num in &nums {
            if s.contains(&(*num - 1)) {
                continue;
            }
            let mut cur_num = num.clone();
            while s.contains(&cur_num) {
                cur_num += 1;
            }
            // Update the answer
            ret = std::cmp::max(ret, cur_num - num);
        }

        ret
    }
}
