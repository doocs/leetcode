use std::collections::HashMap;

impl Solution {
    pub fn count_divisible_substrings(word: String) -> i32 {
        let d = vec!["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"];
        let mut mp: Vec<usize> = vec![0; 26];
        for (i, s) in d.iter().enumerate() {
            for c in s.chars() {
                mp[(c as usize) - ('a' as usize)] = i + 1;
            }
        }
        let mut ans = 0;
        for i in 0..10 {
            let mut cnt: HashMap<i32, i32> = HashMap::new();
            cnt.insert(0, 1);
            let mut s = 0;
            for c in word.chars() {
                s += (mp[(c as usize) - ('a' as usize)] - i) as i32;
                ans += cnt.get(&s).cloned().unwrap_or(0);
                *cnt.entry(s).or_insert(0) += 1;
            }
        }
        ans
    }
}
