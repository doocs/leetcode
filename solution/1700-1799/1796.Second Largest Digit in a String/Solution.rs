impl Solution {
    pub fn second_highest(s: String) -> i32 {
        let mut first = -1;
        let mut second = -1;
        for c in s.as_bytes() {
            if char::is_digit(*c as char, 10) {
                let num = (c - b'0') as i32;
                if first < num {
                    second = first;
                    first = num;
                } else if num < first && second < num {
                    second = num;
                }
            }
        }
        second
    }
}
