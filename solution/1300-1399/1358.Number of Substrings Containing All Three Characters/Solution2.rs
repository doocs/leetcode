impl Solution {
    pub fn number_of_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let mut ans = 0;
        let mut l = 0;
        let mut cnt = [0; 3];

        for r in 0..bytes.len() {
            cnt[(bytes[r] - b'a') as usize] += 1;

            while cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0 {
                cnt[(bytes[l] - b'a') as usize] -= 1;
                l += 1;
            }

            ans += l as i32;
        }

        ans
    }
}
