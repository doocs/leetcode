use std::collections::HashMap;

impl Solution {
    pub fn find_error_nums(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32;
        let mut cnt: HashMap<i32, i32> = HashMap::new();

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
        }

        let mut ans = vec![0; 2];

        for x in 1..=n {
            let c = *cnt.get(&x).unwrap_or(&0);
            if c == 2 {
                ans[0] = x;
            } else if c == 0 {
                ans[1] = x;
            }
        }

        ans
    }
}
