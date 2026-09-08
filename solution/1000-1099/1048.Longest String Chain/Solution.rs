impl Solution {
    pub fn longest_str_chain(mut words: Vec<String>) -> i32 {
        fn check(a: &[u8], b: &[u8]) -> bool {
            if a.len() + 1 != b.len() {
                return false;
            }
            let mut i = 0;
            for &c in b {
                if i < a.len() && a[i] == c {
                    i += 1;
                }
            }
            i == a.len()
        }

        words.sort_unstable_by_key(|w| w.len());
        let n = words.len();
        let mut f = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if check(words[j].as_bytes(), words[i].as_bytes()) {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
        }
        *f.iter().max().unwrap()
    }
}
