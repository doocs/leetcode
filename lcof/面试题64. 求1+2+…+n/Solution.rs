impl Solution {
    pub fn sum_nums(mut n: i32) -> i32 {
        n != 0 && (n += Solution::sum_nums(n - 1), true).1;
        n
    }
}
