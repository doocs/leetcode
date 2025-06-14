impl Solution {
    pub fn min_max_difference(num: i32) -> i32 {
        let s = num.to_string();
        let mi = s
            .replace(s.chars().next().unwrap(), "0")
            .parse::<i32>()
            .unwrap();
        for c in s.chars() {
            if c != '9' {
                let mx = s.replace(c, "9").parse::<i32>().unwrap();
                return mx - mi;
            }
        }
        num - mi
    }
}
