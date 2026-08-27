impl Solution {
    pub fn subsequence_pair_count(nums: Vec<i32>) -> i32 {
        fn gcd(mut first: usize, mut second: usize) -> usize {
            while second != 0 {
                let remainder = first % second;
                first = second;
                second = remainder;
            }
            first
        }
        const MOD: i32 = 1_000_000_007;
        const GCD_STATE_COUNT: usize = 201;
        let mut dp = [[0i32; GCD_STATE_COUNT]; GCD_STATE_COUNT];
        dp[0][0] = 1;
        for &num in nums.iter() {
            let value = num as usize;
            let mut gcd_with_value = [0usize; GCD_STATE_COUNT];
            for gcd_value in 0..GCD_STATE_COUNT {
                gcd_with_value[gcd_value] = gcd(gcd_value, value);
            }
            let mut next_dp = dp;
            for first_gcd in 0..GCD_STATE_COUNT {
                for second_gcd in 0..GCD_STATE_COUNT {
                    let count = dp[first_gcd][second_gcd];
                    if count == 0 {
                        continue;
                    }
                    let next_first_gcd = gcd_with_value[first_gcd];
                    let next_second_gcd = gcd_with_value[second_gcd];
                    next_dp[next_first_gcd][second_gcd] += count;
                    if next_dp[next_first_gcd][second_gcd] >= MOD {
                        next_dp[next_first_gcd][second_gcd] -= MOD;
                    }
                    next_dp[first_gcd][next_second_gcd] += count;
                    if next_dp[first_gcd][next_second_gcd] >= MOD {
                        next_dp[first_gcd][next_second_gcd] -= MOD;
                    }
                }
            }
            dp = next_dp;
        }
        let mut answer = 0;
        for gcd_value in 1..GCD_STATE_COUNT {
            answer += dp[gcd_value][gcd_value];
            if answer >= MOD {
                answer -= MOD;
            }
        }
        answer
    }
}
