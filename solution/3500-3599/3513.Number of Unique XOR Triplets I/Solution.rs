impl Solution {
    pub fn unique_xor_triplets(nums: Vec<i32>) -> i32 {
        let length = nums.len() as i32;
        if length < 3 {
            return length;
        }
        1 << (length.ilog2() + 1)
    }
}
