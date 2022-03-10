impl Solution {
    pub fn fib(n: i32) -> i32 {
        let mut a = 0;
        let mut b = 1;
        for _ in 0..n {
            let t = b;
            b = a + b;
            a = t
        }
        a
    }
}
