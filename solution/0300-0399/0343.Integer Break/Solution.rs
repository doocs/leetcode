impl Solution {
    pub fn integer_break(n: i32) -> i32 {
        let n = n as usize;
        let mut f = vec![0; n + 1];
        f[1] = 1;
        for i in 2..=n {
            for j in 1..i {
                f[i] = f[i].max(f[i - j] * j).max((i - j) * j);
            }
        }
        f[n] as i32
    }
}
