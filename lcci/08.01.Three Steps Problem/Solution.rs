impl Solution {
    pub fn ways_to_step(n: i32) -> i32 {
        let (mut a, mut b, mut c) = (1, 2, 4);
        let m = 1000000007;
        for _ in 1..n {
            let t = a;
            a = b;
            b = c;
            c = ((a + b) % m + t) % m;
        }
        a
    }
}