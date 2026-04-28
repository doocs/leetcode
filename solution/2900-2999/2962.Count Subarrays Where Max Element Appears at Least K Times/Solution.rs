impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, k: i32) -> i64 {
        let mx = *nums.iter().max().unwrap();
        let n = nums.len();
        let mut ans = 0i64;
        let mut cnt = 0;
        let mut j = 0;

        for &x in &nums {
            while j < n && cnt < k {
                if nums[j] == mx {
                    cnt += 1;
                }
                j += 1;
            }

            if cnt < k {
                break;
            }

            ans += (n - j + 1) as i64;

            if x == mx {
                cnt -= 1;
            }
        }

        ans
    }
}
