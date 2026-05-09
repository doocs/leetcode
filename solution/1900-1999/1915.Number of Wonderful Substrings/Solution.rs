impl Solution {
    pub fn wonderful_substrings(word: String) -> i64 {
        let mut cnt = [0i64; 1 << 10];
        cnt[0] = 1;
        let mut ans: i64 = 0;
        let mut st: usize = 0;
        for c in word.chars() {
            st ^= 1 << (c as usize - 'a' as usize);
            ans += cnt[st];
            for i in 0..10 {
                ans += cnt[st ^ (1 << i)];
            }
            cnt[st] += 1;
        }
        ans
    }
}
