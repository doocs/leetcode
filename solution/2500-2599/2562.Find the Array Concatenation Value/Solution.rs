impl Solution {
    pub fn find_the_array_conc_val(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        let mut j = n - 1;
        while i < j {
            ans += format!("{}{}", nums[i], nums[j]).parse::<i64>().unwrap();
            i += 1;
            j -= 1;
        }
        if i == j {
            ans += nums[i] as i64;
        }
        ans
    }
}
