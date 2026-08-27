use std::collections::HashMap;
use std::sync::OnceLock;

impl Solution {
    pub fn longest_subarray(nums: Vec<i32>, k: i32) -> i32 {
        static PRIMES: OnceLock<Vec<Vec<i32>>> = OnceLock::new();

        let primes = PRIMES.get_or_init(|| {
            let mut primes = vec![Vec::<i32>::new(); 100001];

            for i in 2..100001 {
                if primes[i].is_empty() {
                    let mut j = i;
                    while j < 100001 {
                        primes[j].push(i as i32);
                        j += i;
                    }
                }
            }

            primes
        });

        let mut cnt: HashMap<i32, i32> = HashMap::new();

        let mut ans = 0;
        let mut l = 0usize;

        for r in 0..nums.len() {
            for &p in &primes[nums[r] as usize] {
                *cnt.entry(p).or_insert(0) += 1;
            }

            while cnt.len() > k as usize {
                for &p in &primes[nums[l] as usize] {
                    let v = cnt.get_mut(&p).unwrap();
                    *v -= 1;

                    if *v == 0 {
                        cnt.remove(&p);
                    }
                }

                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
