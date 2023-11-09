use std::collections::HashMap;

impl Solution {
    pub fn share_candies(candies: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let n = candies.len();

        for i in k as usize..n {
            *cnt.entry(candies[i]).or_insert(0) += 1;
        }

        let mut ans = cnt.len() as i32;

        for i in k as usize..n {
            *cnt.entry(candies[i - (k as usize)]).or_insert(0) += 1;
            if let Some(x) = cnt.get_mut(&candies[i]) {
                *x -= 1;
                if *x == 0 {
                    cnt.remove(&candies[i]);
                }
            }

            ans = ans.max(cnt.len() as i32);
        }

        ans
    }
}
