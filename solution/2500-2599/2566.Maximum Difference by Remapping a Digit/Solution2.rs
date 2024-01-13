impl Solution {
    pub fn min_max_difference(num: i32) -> i32 {
        let mut s = num.to_string().into_bytes();
        let first = s[0];
        for i in 0..s.len() {
            if s[i] == first {
                s[i] = b'0';
            }
        }
        let mi = String::from_utf8_lossy(&s).parse::<i32>().unwrap();

        let mut t = num.to_string().into_bytes();
        for i in 0..t.len() {
            if t[i] != b'9' {
                let second = t[i];

                for j in 0..t.len() {
                    if t[j] == second {
                        t[j] = b'9';
                    }
                }

                let mx = String::from_utf8_lossy(&t).parse::<i32>().unwrap();
                return mx - mi;
            }
        }

        num - mi
    }
}
