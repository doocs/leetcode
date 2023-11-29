impl Solution {
    pub fn num_trees(n: i32) -> i32 {
        let n = n as usize;
        let mut f = vec![0; n + 1];
        f[0] = 1;
        for i in 1..=n {
            for j in 0..i {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        f[n] as i32
    }
}
