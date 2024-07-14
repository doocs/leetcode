use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut j = 0;
        let n = fruits.len();

        for &x in &fruits {
            *cnt.entry(x).or_insert(0) += 1;
            if cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }
        }

        (n - j) as i32
    }
}
