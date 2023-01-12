use std::collections::HashMap;
impl Solution {
    pub fn evaluate(s: String, knowledge: Vec<Vec<String>>) -> String {
        let s = s.as_bytes();
        let n = s.len();
        let mut map = HashMap::new();
        for v in knowledge.iter() {
            map.insert(&v[0], &v[1]);
        }
        let mut ans = String::new();
        let mut i = 0;
        while i < n {
            if s[i] == b'(' {
                i += 1;
                let mut j = i;
                let mut key = String::new();
                while s[j] != b')' {
                    key.push(s[j] as char);
                    j += 1;
                }
                ans.push_str(map.get(&key).unwrap_or(&&'?'.to_string()));
                i = j;
            } else {
                ans.push(s[i] as char);
            }
            i += 1;
        }
        ans
    }
}
