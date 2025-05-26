impl Solution {
    pub fn count_largest_group(n: i32) -> i32 {
        let mut cnt = vec![0; 40];
        let mut ans = 0;
        let mut mx = 0;

        for i in 1..=n {
            let mut s = 0;
            let mut x = i;
            while x > 0 {
                s += x % 10;
                x /= 10;
            }
            cnt[s as usize] += 1;
            if mx < cnt[s as usize] {
                mx = cnt[s as usize];
                ans = 1;
            } else if mx == cnt[s as usize] {
                ans += 1;
            }
        }

        ans
    }
}
