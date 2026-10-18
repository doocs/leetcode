use std::collections::HashMap;

impl Solution {
    pub fn evaluate(s: String, knowledge: Vec<Vec<String>>) -> String {
        let mut d = HashMap::new();
        for e in &knowledge {
            d.insert(e[0].as_str(), e[1].as_str());
        }
        let mut ans = String::new();
        let n = s.len();
        let mut i = 0;
        while i < n {
            if s.as_bytes()[i] == b'(' {
                let mut j = i + 1;
                while s.as_bytes()[j] != b')' {
                    j += 1;
                }
                ans.push_str(d.get(&s[i + 1..j]).copied().unwrap_or("?"));
                i = j;
            } else {
                ans.push(s.as_bytes()[i] as char);
            }
            i += 1;
        }
        ans
    }
}
