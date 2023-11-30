impl Solution {
    pub fn close_strings(word1: String, word2: String) -> bool {
        let mut cnt1 = vec![0; 26];
        let mut cnt2 = vec![0; 26];
        for c in word1.chars() {
            cnt1[((c as u8) - b'a') as usize] += 1;
        }
        for c in word2.chars() {
            cnt2[((c as u8) - b'a') as usize] += 1;
        }
        for i in 0..26 {
            if (cnt1[i] == 0) != (cnt2[i] == 0) {
                return false;
            }
        }
        cnt1.sort();
        cnt2.sort();
        cnt1 == cnt2
    }
}
