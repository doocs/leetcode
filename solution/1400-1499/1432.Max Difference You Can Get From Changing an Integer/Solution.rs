impl Solution {
    pub fn max_diff(num: i32) -> i32 {
        let a = num.to_string();
        let mut a = a.clone();
        let mut b = a.clone();

        for c in a.chars() {
            if c != '9' {
                a = a.replace(c, "9");
                break;
            }
        }

        let chars: Vec<char> = b.chars().collect();
        if chars[0] != '1' {
            b = b.replace(chars[0], "1");
        } else {
            for &c in &chars[1..] {
                if c != '0' && c != '1' {
                    b = b.replace(c, "0");
                    break;
                }
            }
        }

        a.parse::<i32>().unwrap() - b.parse::<i32>().unwrap()
    }
}
