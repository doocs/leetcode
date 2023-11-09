use std::collections::HashMap;

impl Solution {
    pub fn maximum_beauty(flowers: Vec<i32>) -> i32 {
        let mut s = vec![0; flowers.len() + 1];
        let mut d = HashMap::new();
        let mut ans = i32::MIN;

        for (i, &v) in flowers.iter().enumerate() {
            if let Some(&j) = d.get(&v) {
                ans = ans.max(s[i] - s[j + 1] + v * 2);
            } else {
                d.insert(v, i);
            }
            s[i + 1] = s[i] + v.max(0);
        }

        ans
    }
}
