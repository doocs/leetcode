use std::collections::HashSet;

impl Solution {
    pub fn num_unique_emails(emails: Vec<String>) -> i32 {
        let mut s = HashSet::new();

        for email in emails {
            let parts: Vec<&str> = email.split('@').collect();
            let local = parts[0];
            let domain = parts[1];
            let mut t = String::new();
            for c in local.chars() {
                if c == '.' {
                    continue;
                }
                if c == '+' {
                    break;
                }
                t.push(c);
            }
            s.insert(format!("{}@{}", t, domain));
        }

        s.len() as i32
    }
}
