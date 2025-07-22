impl Solution {
    pub fn partition_array(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let mut ans = 1;
        let mut a = nums[0];

        for &b in nums.iter() {
            if b - a > k {
                a = b;
                ans += 1;
            }
        }

        ans
    }
}
