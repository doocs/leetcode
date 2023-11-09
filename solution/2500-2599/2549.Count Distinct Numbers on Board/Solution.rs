impl Solution {
    pub fn distinct_integers(n: i32) -> i32 {
        if n == 1 {
            return 1;
        }

        n - 1
    }
}
