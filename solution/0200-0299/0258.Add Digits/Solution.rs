impl Solution {
    pub fn add_digits(num: i32) -> i32 {
        if num < 10 {
            return num;
        }
        Self::add_digits(
            num.to_string()
                .chars()
                .map(|c| c.to_string().parse::<i32>().unwrap())
                .sum::<i32>(),
        )
    }
}
