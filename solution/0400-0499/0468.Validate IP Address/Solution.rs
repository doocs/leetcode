impl Solution {
    pub fn valid_ip_address(query_ip: String) -> String {
        if Self::is_ipv4(&query_ip) {
            return "IPv4".to_string();
        }
        if Self::is_ipv6(&query_ip) {
            return "IPv6".to_string();
        }
        "Neither".to_string()
    }

    fn is_ipv4(s: &str) -> bool {
        if s.ends_with('.') {
            return false;
        }
        let ss: Vec<&str> = s.split('.').collect();
        if ss.len() != 4 {
            return false;
        }
        for t in ss {
            if t.is_empty() || (t.len() > 1 && t.starts_with('0')) {
                return false;
            }
            match Self::convert(t) {
                Some(x) if x <= 255 => {
                    continue;
                }
                _ => {
                    return false;
                }
            }
        }
        true
    }

    fn is_ipv6(s: &str) -> bool {
        if s.ends_with(':') {
            return false;
        }
        let ss: Vec<&str> = s.split(':').collect();
        if ss.len() != 8 {
            return false;
        }
        for t in ss {
            if t.len() < 1 || t.len() > 4 {
                return false;
            }
            if !t.chars().all(|c| c.is_digit(16)) {
                return false;
            }
        }
        true
    }

    fn convert(s: &str) -> Option<i32> {
        let mut x = 0;
        for c in s.chars() {
            if !c.is_digit(10) {
                return None;
            }
            x = x * 10 + (c.to_digit(10).unwrap() as i32);
            if x > 255 {
                return Some(x);
            }
        }
        Some(x)
    }
}
