impl Solution {
    pub fn reverse(mut x: i32) -> i32 {
        if x == i32::MIN {
            return 0;
        }
        let is_minus = x < 0;
        match x
            .abs()
            .to_string()
            .chars()
            .rev()
            .collect::<String>()
            .parse::<i32>()
        {
            Ok(x) => x * (if is_minus { -1 } else { 1 }),
            Err(_) => 0,
        }
    }
}
