impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        if s1.len() != s2.len() {
            return false;
        }

        let mut cnt = vec![0; 26];
        for c in s1.chars() {
            cnt[(c as usize - 'a' as usize)] += 1;
        }

        for c in s2.chars() {
            let index = c as usize - 'a' as usize;
            if cnt[index] == 0 {
                return false;
            }
            cnt[index] -= 1;
        }

        true
    }
}
