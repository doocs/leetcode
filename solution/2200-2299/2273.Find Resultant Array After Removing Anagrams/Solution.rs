impl Solution {
    pub fn remove_anagrams(words: Vec<String>) -> Vec<String> {
        fn check(s: &str, t: &str) -> bool {
            if s.len() != t.len() {
                return true;
            }
            let mut cnt = [0; 26];
            for c in s.bytes() {
                cnt[(c - b'a') as usize] += 1;
            }
            for c in t.bytes() {
                let idx = (c - b'a') as usize;
                cnt[idx] -= 1;
                if cnt[idx] < 0 {
                    return true;
                }
            }
            false
        }

        let mut ans = vec![words[0].clone()];
        for i in 1..words.len() {
            if check(&words[i - 1], &words[i]) {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
