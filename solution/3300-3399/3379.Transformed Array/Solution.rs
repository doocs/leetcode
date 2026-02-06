impl Solution {
    pub fn construct_transformed_array(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32;
        let mut ans = vec![0; nums.len()];
        for (i, &x) in nums.iter().enumerate() {
            ans[i] = nums[(((i as i32 + x % n + n) % n) as usize)];
        }
        ans
    }
}
