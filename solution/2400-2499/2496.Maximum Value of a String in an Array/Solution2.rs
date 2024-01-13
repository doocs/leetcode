impl Solution {
    pub fn maximum_value(strs: Vec<String>) -> i32 {
        let parse = |s: String| -> i32 {
            let mut x = 0;

            for c in s.chars() {
                if c >= 'a' && c <= 'z' {
                    x = s.len();
                    break;
                }

                x = x * 10 + (((c as u8) - b'0') as usize);
            }

            x as i32
        };

        let mut ans = 0;
        for s in strs {
            let v = parse(s);
            if v > ans {
                ans = v;
            }
        }

        ans
    }
}
