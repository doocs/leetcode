impl Solution {
    pub fn count_consistent_strings(allowed: String, words: Vec<String>) -> i32 {
        let n = words.len();
        let mut make = [false; 26];
        for c in allowed.as_bytes() {
            make[(c - b'a') as usize] = true;
        }
        let mut ans = n as i32;
        for word in words.iter() {
            for c in word.as_bytes().iter() {
                if !make[(c - b'a') as usize] {
                    ans -= 1;
                    break;
                }
            }
        }
        ans
    }
}
