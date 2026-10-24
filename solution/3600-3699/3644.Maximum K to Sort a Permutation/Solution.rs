impl Solution {
    pub fn sort_permutation(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        for (i, &x) in nums.iter().enumerate() {
            if i as i32 != x {
                ans &= x;
            }
        }
        ans.max(0)
    }
}
