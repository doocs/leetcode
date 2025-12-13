use std::collections::HashSet;

impl Solution {
    pub fn validate_coupons(
        code: Vec<String>,
        business_line: Vec<String>,
        is_active: Vec<bool>,
    ) -> Vec<String> {
        fn check(s: &str) -> bool {
            if s.is_empty() {
                return false;
            }
            s.chars()
                .all(|c| c.is_ascii_alphanumeric() || c == '_')
        }

        let bs: HashSet<&str> =
            ["electronics", "grocery", "pharmacy", "restaurant"]
                .iter()
                .copied()
                .collect();

        let mut idx: Vec<usize> = Vec::new();
        for i in 0..code.len() {
            if is_active[i] && bs.contains(business_line[i].as_str()) && check(&code[i]) {
                idx.push(i);
            }
        }

        idx.sort_by(|&i, &j| {
            let cmp = business_line[i].cmp(&business_line[j]);
            if cmp == std::cmp::Ordering::Equal {
                code[i].cmp(&code[j])
            } else {
                cmp
            }
        });

        idx.into_iter().map(|i| code[i].clone()).collect()
    }
}
