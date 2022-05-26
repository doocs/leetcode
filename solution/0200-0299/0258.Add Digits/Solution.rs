impl Solution {
    pub fn add_digits(mut num: i32) -> i32 {
        (num - 1) % 9 + 1
    }
}
