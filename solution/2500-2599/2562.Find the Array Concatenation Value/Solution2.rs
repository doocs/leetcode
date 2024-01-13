impl Solution {
    pub fn find_the_array_conc_val(nums: Vec<i32>) -> i64 {
        let mut ans = 0;
        let mut n = nums.len();

        for i in 0..n / 2 {
            ans += format!("{}{}", nums[i], nums[n - i - 1])
                .parse::<i64>()
                .unwrap();
        }

        if n % 2 != 0 {
            ans += nums[n / 2] as i64;
        }

        ans
    }
}
