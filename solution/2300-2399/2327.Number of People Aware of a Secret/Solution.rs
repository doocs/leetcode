impl Solution {
    pub fn people_aware_of_secret(n: i32, delay: i32, forget: i32) -> i32 {
        let n = n as usize;
        let delay = delay as usize;
        let forget = forget as usize;
        let m = (n << 1) + 10;
        let modulo: i64 = 1_000_000_007;

        let mut d = vec![0i64; m];
        let mut cnt = vec![0i64; m];

        cnt[1] = 1;
        for i in 1..=n {
            if cnt[i] > 0 {
                d[i] = (d[i] + cnt[i]) % modulo;
                d[i + forget] = (d[i + forget] - cnt[i] + modulo) % modulo;
                let mut nxt = i + delay;
                while nxt < i + forget {
                    cnt[nxt] = (cnt[nxt] + cnt[i]) % modulo;
                    nxt += 1;
                }
            }
        }

        let mut ans: i64 = 0;
        for i in 1..=n {
            ans = (ans + d[i]) % modulo;
        }
        ans as i32
    }
}
