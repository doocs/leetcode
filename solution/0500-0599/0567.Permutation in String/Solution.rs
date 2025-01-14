impl Solution {
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        let mut need = 0;
        let mut cnt = vec![0; 26];

        for c in s1.chars() {
            let index = (c as u8 - b'a') as usize;
            if cnt[index] == 0 {
                need += 1;
            }
            cnt[index] += 1;
        }

        let m = s1.len();
        let n = s2.len();
        let s2_bytes = s2.as_bytes();

        for i in 0..n {
            let c = (s2_bytes[i] - b'a') as usize;
            cnt[c] -= 1;
            if cnt[c] == 0 {
                need -= 1;
            }

            if i >= m {
                let c = (s2_bytes[i - m] - b'a') as usize;
                cnt[c] += 1;
                if cnt[c] == 1 {
                    need += 1;
                }
            }

            if need == 0 {
                return true;
            }
        }

        false
    }
}
