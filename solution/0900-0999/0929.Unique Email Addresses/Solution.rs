use std::collections::HashSet;
impl Solution {
    pub fn num_unique_emails(emails: Vec<String>) -> i32 {
        let mut set = HashSet::new();
        for email in emails.iter() {
            let res: Vec<&str> = email.split('@').collect();
            let mut s = String::new();
            for &c in res[0].as_bytes().iter() {
                if c == b'.' {
                    continue;
                }
                if c == b'+' {
                    break;
                }
                s.push(c as char)
            }
            s.push('@');
            s.push_str(res[1]);
            set.insert(s);
        }
        set.len() as i32
    }
}
