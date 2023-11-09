impl Solution {
    pub fn find_the_longest_balanced_substring(s: String) -> i32 {
        let mut zero = 0;
        let mut one = 0;
        let mut ans = 0;

        for &c in s.as_bytes().iter() {
            if c == b'0' {
                if one > 0 {
                    zero = 0;
                    one = 0;
                }
                zero += 1;
            } else {
                one += 1;
                ans = std::cmp::max(ans, std::cmp::min(zero, one) * 2);
            }
        }

        ans
    }
}
