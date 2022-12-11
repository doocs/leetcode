impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut max = 0;
        for &v in nums.iter() {
            ans += 0.max(max + 1 - v);
            max = v.max(max + 1);
        }
        ans
    }
}
