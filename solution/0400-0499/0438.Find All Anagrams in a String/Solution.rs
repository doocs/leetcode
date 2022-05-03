impl Solution {
    pub fn find_anagrams(s: String, p: String) -> Vec<i32> {
        let (s, p) = (s.as_bytes(), p.as_bytes());
        let (m, n) = (s.len(), p.len());
        let mut res = vec![];
        if n > m {
            return res;
        }

        let mut counter = [0; 26];
        for i in 0..n {
            counter[(p[i] - b'a') as usize] += 1;
            counter[(s[i] - b'a') as usize] -= 1;
        }
        for i in n..m {
            if counter.iter().all(|&v| v == 0) {
                res.push((i - n) as i32);
            }
            counter[(s[i] - b'a') as usize] -= 1;
            counter[(s[i - n] - b'a') as usize] += 1;
        }
        if counter.iter().all(|&v| v == 0) {
            res.push((m - n) as i32);
        }
        res
    }
}
