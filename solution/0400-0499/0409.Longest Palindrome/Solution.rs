use std::collections::HashMap;

impl Solution {
    pub fn longest_palindrome(s: String) -> i32 {
        let mut map: HashMap<char, i32> = HashMap::new();
        for c in s.chars() {
            map.insert(c, map.get(&c).unwrap_or(&0) + 1);
        }
        let mut has_odd = false;
        let mut res = 0;
        for v in map.values() {
            res += v;
            if v % 2 == 1 {
                has_odd = true;
                res -= 1;
            }
        }
        res + if has_odd { 1 } else { 0 }
    }
}
