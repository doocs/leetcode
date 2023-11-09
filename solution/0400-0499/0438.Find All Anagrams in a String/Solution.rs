impl Solution {
    pub fn find_anagrams(s: String, p: String) -> Vec<i32> {
        let (s, p) = (s.as_bytes(), p.as_bytes());
        let (m, n) = (s.len(), p.len());
        let mut ans = vec![];
        if m < n {
            return ans;
        }

        let mut cnt = [0; 26];
        for i in 0..n {
            cnt[(p[i] - b'a') as usize] += 1;
            cnt[(s[i] - b'a') as usize] -= 1;
        }
        for i in n..m {
            if cnt.iter().all(|&v| v == 0) {
                ans.push((i - n) as i32);
            }
            cnt[(s[i] - b'a') as usize] -= 1;
            cnt[(s[i - n] - b'a') as usize] += 1;
        }
        if cnt.iter().all(|&v| v == 0) {
            ans.push((m - n) as i32);
        }
        ans
    }
}
