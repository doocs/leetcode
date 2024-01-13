use std::collections::HashMap;

impl Solution {
    pub fn is_fascinating(mut n: i32) -> bool {
        let mut i = n * 2;
        let mut j = n * 3;

        let mut hash = HashMap::new();

        while n != 0 {
            let cnt = hash.entry(n % 10).or_insert(0);
            *cnt += 1;
            n /= 10;
        }

        while i != 0 {
            let cnt = hash.entry(i % 10).or_insert(0);
            *cnt += 1;
            i /= 10;
        }

        while j != 0 {
            let cnt = hash.entry(j % 10).or_insert(0);
            *cnt += 1;
            j /= 10;
        }

        for k in 1..=9 {
            if !hash.contains_key(&k) || hash[&k] > 1 {
                return false;
            }
        }

        !hash.contains_key(&0)
    }
}
