use std::collections::HashMap;

impl Solution {
    pub fn pyramid_transition(bottom: String, allowed: Vec<String>) -> bool {
        let mut d = vec![vec![0; 7]; 7];
        for s in allowed {
            let a = (s.as_bytes()[0] - b'A') as usize;
            let b = (s.as_bytes()[1] - b'A') as usize;
            let c = (s.as_bytes()[2] - b'A') as usize;
            d[a][b] |= 1 << c;
        }

        let mut f = HashMap::<String, bool>::new();

        fn dfs(s: &str, t: &mut Vec<u8>, d: &Vec<Vec<i32>>, f: &mut HashMap<String, bool>) -> bool {
            if s.len() == 1 {
                return true;
            }

            if t.len() + 1 == s.len() {
                let next = String::from_utf8_lossy(t).to_string();
                let mut nt = Vec::new();
                return dfs(&next, &mut nt, d, f);
            }

            let key = format!("{}.{}", s, String::from_utf8_lossy(t));
            if let Some(&res) = f.get(&key) {
                return res;
            }

            let i = t.len();
            let a = (s.as_bytes()[i] - b'A') as usize;
            let b = (s.as_bytes()[i + 1] - b'A') as usize;
            let mut cs = d[a][b];

            for c in 0..7 {
                if (cs >> c) & 1 == 1 {
                    t.push(b'A' + c as u8);
                    if dfs(s, t, d, f) {
                        f.insert(key.clone(), true);
                        t.pop();
                        return true;
                    }
                    t.pop();
                }
            }

            f.insert(key, false);
            false
        }

        let mut t = Vec::new();
        dfs(&bottom, &mut t, &d, &mut f)
    }
}
