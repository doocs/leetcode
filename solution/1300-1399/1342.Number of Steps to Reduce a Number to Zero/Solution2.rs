impl Solution {
    pub fn number_of_steps(mut num: i32) -> i32 {
        if num == 0 {
            0
        } else if num % 2 == 0 {
            1 + Solution::number_of_steps(num >> 1)
        } else {
            1 + Solution::number_of_steps(num - 1)
        }
    }
}
