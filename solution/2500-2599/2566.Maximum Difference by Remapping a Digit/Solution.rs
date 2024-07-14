impl Solution {
    pub fn min_max_difference(num: i32) -> i32 {
        let s = num.to_string();
        let min = s
            .replace(char::from(s.as_bytes()[0]), "0")
            .parse::<i32>()
            .unwrap();
        for &c in s.as_bytes() {
            if c != b'9' {
                return s.replace(c, "9").parse().unwrap() - min;
            }
        }
        num - min
    }
}
