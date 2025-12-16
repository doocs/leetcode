impl Solution {
    pub fn max_gcd_score(nums: Vec<i32>, k: i32) -> i64 {
        let n = nums.len();
        let mut cnt = vec![0i32; n];
        for i in 0..n {
            let mut x = nums[i];
            while x % 2 == 0 {
                cnt[i] += 1;
                x /= 2;
            }
        }

        let mut ans: i64 = 0;
        for l in 0..n {
            let mut g: i32 = 0;
            let mut mi: i32 = 1 << 30;
            let mut t: i32 = 0;
            for r in l..n {
                g = Self::gcd(g, nums[r]);
                if cnt[r] < mi {
                    mi = cnt[r];
                    t = 1;
                } else if cnt[r] == mi {
                    t += 1;
                }
                let val = if t > k { g as i64 } else { (g * 2) as i64 };
                ans = ans.max((r as i64 - l as i64 + 1) * val);
            }
        }
        ans
    }

    fn gcd(a: i32, b: i32) -> i32 {
        if b == 0 { a } else { Self::gcd(b, a % b) }
    }
}
