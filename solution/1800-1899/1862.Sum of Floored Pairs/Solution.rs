impl Solution {
    pub fn sum_of_floored_pairs(nums: Vec<i32>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut mx = 0;
        for &x in nums.iter() {
            mx = mx.max(x);
        }

        let mut cnt = vec![0; (mx + 1) as usize];
        let mut s = vec![0; (mx + 1) as usize];

        for &x in nums.iter() {
            cnt[x as usize] += 1;
        }

        for i in 1..=mx as usize {
            s[i] = s[i - 1] + cnt[i];
        }

        let mut ans = 0;
        for y in 1..=mx as usize {
            if cnt[y] > 0 {
                let mut d = 1;
                while d * y <= (mx as usize) {
                    ans += ((cnt[y] as i64)
                        * (d as i64)
                        * (s[std::cmp::min(mx as usize, d * y + y - 1)] - s[d * y - 1]))
                        % (MOD as i64);
                    ans %= MOD as i64;
                    d += 1;
                }
            }
        }

        ans as i32
    }
}
