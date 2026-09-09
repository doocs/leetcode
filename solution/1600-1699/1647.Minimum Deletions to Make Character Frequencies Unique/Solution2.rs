impl Solution {
    #[allow(dead_code)]
    pub fn min_deletions(s: String) -> i32 {
        let mut cnt = vec![0; 26];
        let mut ans = 0;

        for c in s.chars() {
            cnt[((c as u8) - ('a' as u8)) as usize] += 1;
        }

        cnt.sort_by(|&lhs, &rhs| rhs.cmp(&lhs));

        for i in 1..26 {
            while cnt[i] >= cnt[i - 1] && cnt[i] > 0 {
                cnt[i] -= 1;
                ans += 1;
            }
        }

        ans
    }
}
