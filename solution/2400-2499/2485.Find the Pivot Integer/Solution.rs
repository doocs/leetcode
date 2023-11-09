impl Solution {
    pub fn pivot_integer(n: i32) -> i32 {
        let y = (n * (n + 1)) / 2;
        let x = (y as f64).sqrt() as i32;

        if x * x == y {
            return x;
        }

        -1
    }
}
