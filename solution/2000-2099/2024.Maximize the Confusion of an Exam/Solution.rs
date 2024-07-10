impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let n = answer_key.len();
        let k = k as usize;
        let s: Vec<char> = answer_key.chars().collect();

        let f = |c: char| -> usize {
            let mut l = 0;
            let mut cnt = 0;
            for &ch in &s {
                cnt += if ch == c { 1 } else { 0 };
                if cnt > k {
                    cnt -= if s[l] == c { 1 } else { 0 };
                    l += 1;
                }
            }
            n - l
        };

        std::cmp::max(f('T'), f('F')) as i32
    }
}
