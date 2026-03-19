impl Solution {
    pub fn num_steps(s: String) -> i32 {
        let bytes = s.as_bytes();
        let mut ans: i32 = 0;
        let mut carry = false;

        for i in (1..bytes.len()).rev() {
            let mut c = bytes[i];

            if carry {
                if c == b'0' {
                    c = b'1';
                    carry = false;
                } else {
                    c = b'0';
                }
            }

            if c == b'1' {
                ans += 1;
                carry = true;
            }

            ans += 1;
        }

        if carry {
            ans += 1;
        }

        ans
    }
}
