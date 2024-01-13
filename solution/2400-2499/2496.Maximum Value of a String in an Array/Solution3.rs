use std::cmp::max;

impl Solution {
    pub fn maximum_value(strs: Vec<String>) -> i32 {
        let mut ans = 0;

        for s in strs {
            match s.parse::<i32>() {
                Ok(v) => {
                    ans = max(ans, v);
                }
                Err(_) => {
                    ans = max(ans, s.len() as i32);
                }
            }
        }

        ans
    }
}
