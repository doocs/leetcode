impl Solution {
    fn is_IPv4(s: &String) -> bool {
        let ss = s.split('.').collect::<Vec<&str>>();
        if ss.len() != 4 {
            return false;
        }
        for s in ss {
            match s.parse::<i32>() {
                Err(_) => return false,
                Ok(num) => {
                    if num < 0 || num > 255 || num.to_string() != s.to_string() {
                        return false;
                    }
                }
            }
        }
        true
    }

    fn is_IPv6(s: &String) -> bool {
        let ss = s.split(':').collect::<Vec<&str>>();
        if ss.len() != 8 {
            return false;
        }
        for s in ss {
            if s.len() == 0 || s.len() > 4 {
                return false;
            }
            for &c in s.as_bytes() {
                if c >= b'0' && c <= b'9' || c >= b'a' && c <= b'f' || c >= b'A' && c <= b'F' {
                    continue;
                }
                return false;
            }
        }
        true
    }

    pub fn valid_ip_address(query_ip: String) -> String {
        if Self::is_IPv4(&query_ip) {
            return String::from("IPv4");
        }
        if Self::is_IPv6(&query_ip) {
            return String::from("IPv6");
        }
        String::from("Neither")
    }
}
