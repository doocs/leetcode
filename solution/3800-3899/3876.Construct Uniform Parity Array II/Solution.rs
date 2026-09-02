impl Solution {
    pub fn uniform_array(nums1: Vec<i32>) -> bool {
        match nums1.iter().filter(|&&x| x % 2 == 1).min() {
            None => true,
            Some(&mn) => !nums1.iter().any(|&x| x % 2 == 0 && x < mn),
        }
    }
}
