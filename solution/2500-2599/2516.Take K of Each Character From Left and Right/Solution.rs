impl Solution {
    pub fn take_characters(s: String, k: i32) -> i32 {
        let s = s.as_bytes();
        let mut count = vec![0; 3];
        for c in s.iter() {
            count[(c - b'a') as usize] += 1;
        }
        if count.iter().any(|v| *v < k) {
            return -1;
        }
        let n = s.len();
        let mut ans = 0;
        let mut i = 0;
        for j in 0..n {
            count[(s[j] - b'a') as usize] -= 1;
            while count[(s[j] - b'a') as usize] < k {
                count[(s[i] - b'a') as usize] += 1;
                i += 1;
            }
            ans = ans.max(j - i + 1);
        }
        (n - ans) as i32
    }
}
