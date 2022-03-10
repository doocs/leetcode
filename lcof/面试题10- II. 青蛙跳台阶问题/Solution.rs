impl Solution {
    pub fn num_ways(n: i32) -> i32 {
        let mut tup = (0, 1);
        for _ in 0..n {
            tup = (tup.1, (tup.0 + tup.1) % 1000000007);
        }
        tup.1
    }
}
