use std::collections::HashMap;

impl Solution {
    pub fn get_sneaky_numbers(nums: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for x in nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = Vec::new();
        for (x, v) in cnt {
            if v == 2 {
                ans.push(x);
            }
        }
        ans
    }
}
