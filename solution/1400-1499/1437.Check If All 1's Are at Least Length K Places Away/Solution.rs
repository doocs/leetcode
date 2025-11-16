impl Solution {
    pub fn k_length_apart(nums: Vec<i32>, k: i32) -> bool {
        let mut j = -(k + 1);
        for (i, &x) in nums.iter().enumerate() {
            if x == 1 {
                if (i as i32) - j - 1 < k {
                    return false;
                }
                j = i as i32;
            }
        }
        true
    }
}
