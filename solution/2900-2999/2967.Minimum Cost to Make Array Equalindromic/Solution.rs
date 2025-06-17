impl Solution {
    pub fn minimum_cost(nums: Vec<i32>) -> i64 {
        use std::cmp::min;
        use std::sync::Once;

        static INIT: Once = Once::new();
        static mut PS: Vec<i64> = Vec::new();

        INIT.call_once(|| {
            let mut ps_local = Vec::with_capacity(2 * 100_000);
            for i in 1..=100_000 {
                let s = i.to_string();

                let mut t1 = s.clone();
                t1 = t1.chars().rev().collect();
                ps_local.push(format!("{}{}", s, t1).parse::<i64>().unwrap());

                let mut t2 = s[0..s.len() - 1].to_string();
                t2 = t2.chars().rev().collect();
                ps_local.push(format!("{}{}", s, t2).parse::<i64>().unwrap());
            }
            ps_local.sort();
            unsafe {
                PS = ps_local;
            }
        });

        let mut nums = nums;
        nums.sort();

        let mid = nums[nums.len() / 2] as i64;

        let i = unsafe {
            match PS.binary_search(&mid) {
                Ok(i) => i,
                Err(i) => i,
            }
        };

        let f = |x: i64| -> i64 { nums.iter().map(|&v| (v as i64 - x).abs()).sum() };

        let mut ans = i64::MAX;

        for j in i.saturating_sub(1)..=(i + 1).min(2 * 100_000 - 1) {
            let x = unsafe { PS[j] };
            ans = min(ans, f(x));
        }

        ans
    }
}
