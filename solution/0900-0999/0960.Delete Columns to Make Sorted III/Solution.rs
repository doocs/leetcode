impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs[0].len();
        let mut f = vec![1; n];

        for i in 1..n {
            for j in 0..i {
                if strs.iter().all(|s| s.as_bytes()[j] <= s.as_bytes()[i]) {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
        }

        (n - *f.iter().max().unwrap()) as i32
    }
}
