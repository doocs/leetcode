use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut j = 0;

        for (i, &x) in fruits.iter().enumerate() {
            *cnt.entry(x).or_insert(0) += 1;

            while cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }

            ans = ans.max(i - j + 1);
        }

        ans as i32
    }
}
