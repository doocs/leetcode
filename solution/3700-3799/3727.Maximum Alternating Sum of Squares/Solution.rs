impl Solution {
    pub fn max_alternating_sum(nums: Vec<i32>) -> i64 {
        let mut nums: Vec<i64> = nums
            .into_iter()
            .map(|x| i64::from(x) * i64::from(x))
            .collect();
        nums.sort();
        let m = nums.len() >> 1;
        let mut ans = 0i64;
        for (i, &x) in nums.iter().enumerate() {
            ans += if i < m { -x } else { x };
        }
        ans
    }
}
