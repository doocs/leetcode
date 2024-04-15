impl Solution {
    pub fn longest_nice_subarray(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mask = 0;
        let mut j = 0;

        for (i, &x) in nums.iter().enumerate() {
            let mut x = x;
            while (mask & x) != 0 {
                mask ^= nums[j];
                j += 1;
            }
            ans = ans.max(i - j + 1);
            mask |= x;
        }

        ans as i32
    }
}
