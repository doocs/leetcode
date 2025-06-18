impl Solution {
    pub fn min_operations(word1: String, word2: String) -> i32 {
        let n = word1.len();
        let word1 = word1.as_bytes();
        let word2 = word2.as_bytes();
        let mut f = vec![i32::MAX; n + 1];
        f[0] = 0;

        for i in 1..=n {
            for j in 0..i {
                let a = Self::calc(word1, word2, j, i - 1, false);
                let b = 1 + Self::calc(word1, word2, j, i - 1, true);
                let t = a.min(b);
                f[i] = f[i].min(f[j] + t);
            }
        }

        f[n]
    }

    fn calc(word1: &[u8], word2: &[u8], l: usize, r: usize, rev: bool) -> i32 {
        let mut cnt = [[0i32; 26]; 26];
        let mut res = 0;

        for i in l..=r {
            let j = if rev { r - (i - l) } else { i };
            let a = (word1[j] - b'a') as usize;
            let b = (word2[i] - b'a') as usize;

            if a != b {
                if cnt[b][a] > 0 {
                    cnt[b][a] -= 1;
                } else {
                    cnt[a][b] += 1;
                    res += 1;
                }
            }
        }

        res
    }
}
