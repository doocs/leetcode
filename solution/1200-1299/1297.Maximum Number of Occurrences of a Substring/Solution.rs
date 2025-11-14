use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn max_freq(s: String, max_letters: i32, min_size: i32, _max_size: i32) -> i32 {
        let n = s.len();
        let m = min_size as usize;
        let max_letters = max_letters as usize;
        let bytes = s.as_bytes();
        let mut cnt: HashMap<&[u8], i32> = HashMap::new();
        let mut ans = 0;

        for i in 0..=n - m {
            let t = &bytes[i..i + m];

            let mut set = HashSet::new();
            for &c in t {
                set.insert(c);
                if set.len() > max_letters {
                    break;
                }
            }
            if set.len() <= max_letters {
                let v = cnt.entry(t).or_insert(0);
                *v += 1;
                ans = ans.max(*v);
            }
        }

        ans
    }
}
