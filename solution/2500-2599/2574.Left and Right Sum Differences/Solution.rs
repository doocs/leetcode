impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut l = 0;
        let mut r: i32 = nums.iter().sum();
        let mut ans = Vec::with_capacity(nums.len());
        for x in nums {
            r -= x;
            ans.push((l - r).abs());
            l += x;
        }
        ans
    }
}
