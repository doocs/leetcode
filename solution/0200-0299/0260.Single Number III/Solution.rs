impl Solution {
    pub fn single_number(nums: Vec<i32>) -> Vec<i32> {
        let xs = nums.iter().fold(0, |r, v| r ^ v);
        let lb = xs & -xs;
        let mut a = 0;
        for x in &nums {
            if x & lb != 0 {
                a ^= x;
            }
        }
        let b = xs ^ a;
        vec![a, b]
    }
}