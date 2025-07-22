impl Solution {
    pub fn longest_subarray(nums: Vec<i32>) -> i32 {
        let mx = *nums.iter().max().unwrap();
        let mut ans = 0;
        let mut cnt = 0;

        for &x in nums.iter() {
            if x == mx {
                cnt += 1;
                ans = ans.max(cnt);
            } else {
                cnt = 0;
            }
        }

        ans
    }
}
