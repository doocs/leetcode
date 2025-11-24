impl Solution {
    pub fn smallest_repunit_div_by_k(k: i32) -> i32 {
        let mut n = 1 % k;
        for i in 1..=k {
            if n == 0 {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        -1
    }
}
