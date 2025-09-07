impl Solution {
    pub fn beauty_sum(s: String) -> i32 {
        let mut ans = 0;
        let n = s.len();
        let s: Vec<char> = s.chars().collect();

        for i in 0..n {
            let mut cnt = vec![0; 26];
            for j in i..n {
                cnt[s[j] as usize - 'a' as usize] += 1;
                let mut mi = 1000;
                let mut mx = 0;
                for &v in &cnt {
                    if v > 0 {
                        mi = mi.min(v);
                        mx = mx.max(v);
                    }
                }
                ans += mx - mi;
            }
        }
        ans
    }
}
