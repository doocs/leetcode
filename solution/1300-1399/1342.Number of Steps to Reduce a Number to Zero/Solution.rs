impl Solution {
    pub fn number_of_steps(mut num: i32) -> i32 {
        let mut count = 0;
        while num != 0 {
            if num % 2 == 0 {
                num >>= 1;
            } else {
                num -= 1;
            }
            count += 1;
        }
        count
    }
}
