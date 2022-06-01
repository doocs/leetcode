impl Solution {
    pub fn majority_element(nums: Vec<i32>) -> i32 {
        let mut m = 0;
        let mut cnt = 0;
        for &v in nums.iter() {
            if cnt == 0 {
                m = v;
                cnt = 1;
            } else {
                cnt += if m == v { 1 } else { -1 };
            }
        }
        m
    }
}