impl Solution {
    pub fn smallest_number(num: i64) -> i64 {
        let mut neg = num < 0;
        let mut num = num.abs();
        let mut cnt = vec![0; 10];

        while num > 0 {
            cnt[(num % 10) as usize] += 1;
            num /= 10;
        }

        let mut ans = 0;
        if neg {
            for i in (0..10).rev() {
                while cnt[i] > 0 {
                    ans = ans * 10 + i as i64;
                    cnt[i] -= 1;
                }
            }
            return -ans;
        }

        if cnt[0] > 0 {
            for i in 1..10 {
                if cnt[i] > 0 {
                    cnt[i] -= 1;
                    ans = i as i64;
                    break;
                }
            }
        }

        for i in 0..10 {
            while cnt[i] > 0 {
                ans = ans * 10 + i as i64;
                cnt[i] -= 1;
            }
        }

        ans
    }
}
