impl Solution {
    pub fn majority_element(nums: Vec<i32>) -> i32 {
        let mut major = 0;
        let mut cnt = 0;
        for &num in nums.iter() {
            if cnt == 0 {
                major = num;
                cnt = 1;
            } else {
                cnt += if major == num { 1 } else { -1 };
            }
        }
        major
    }
}
