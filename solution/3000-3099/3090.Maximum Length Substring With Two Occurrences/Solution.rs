impl Solution {
    pub fn maximum_length_substring(s: String) -> i32 {
        let mut cnt = [0; 26];
        let mut ans = 0;
        let mut l = 0;
        let s = s.as_bytes();

        for (r, &c) in s.iter().enumerate() {
            let i = (c - b'a') as usize;
            cnt[i] += 1;

            while cnt[i] > 2 {
                cnt[(s[l] - b'a') as usize] -= 1;
                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
