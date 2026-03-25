impl Solution {
    pub fn check_strings(s1: String, s2: String) -> bool {
        let mut cnt: [[i32; 26]; 2] = [[0; 26]; 2];
        let n = s1.len();
        let s1 = s1.as_bytes();
        let s2 = s2.as_bytes();

        for i in 0..n {
            let idx = (i & 1) as usize;
            cnt[idx][(s1[i] - b'a') as usize] += 1;
            cnt[idx][(s2[i] - b'a') as usize] -= 1;
        }

        cnt.iter().all(|row| row.iter().all(|&x| x == 0))
    }
}
