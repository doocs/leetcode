impl Solution {
    pub fn sort_permutation(nums: Vec<i32>) -> i32 {
        nums.iter()
            .enumerate()
            .filter(|&(i, &x)| i != x as usize)
            .fold(-1, |ans, (_, &x)| ans & x)
            .max(0)
    }
}
