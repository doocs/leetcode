impl Solution {
    pub fn sum_of_multiples(n: i32) -> i32 {
        fn f(x: i32, n: i32) -> i32 {
            let m = n / x;
            ((x + m * x) * m) / 2
        }

        f(3, n) + f(5, n) + f(7, n) - f(3 * 5, n) - f(3 * 7, n) - f(5 * 7, n) + f(3 * 5 * 7, n)
    }
}
