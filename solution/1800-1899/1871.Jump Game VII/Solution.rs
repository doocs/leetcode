impl Solution {
    pub fn can_reach(s: String, min_jump: i32, max_jump: i32) -> bool {
        let s = s.as_bytes();
        let n = s.len();
        let min_jump = min_jump as usize;
        let max_jump = max_jump as usize;

        let mut pre = vec![0; n + 1];
        pre[1] = 1;

        let mut f = vec![false; n];
        f[0] = true;

        for i in 1..n {
            if s[i] == b'0' {
                let l = i.saturating_sub(max_jump);
                if i >= min_jump {
                    let r = i - min_jump;
                    f[i] = l <= r && pre[r + 1] - pre[l] > 0;
                }
            }
            pre[i + 1] = pre[i] + if f[i] { 1 } else { 0 };
        }

        f[n - 1]
    }
}
