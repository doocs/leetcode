impl Solution {
    pub fn smallest_index(nums: Vec<i32>) -> i32 {
        for (i, mut x) in nums.into_iter().enumerate() {
            let mut s = 0;
            while x > 0 {
                s += x % 10;
                x /= 10;
            }
            if s == i as i32 {
                return i as i32;
            }
        }
        -1
    }
}
