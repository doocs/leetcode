impl Solution {
    pub fn find_middle_index(nums: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r: i32 = nums.iter().sum();

        for (i, &x) in nums.iter().enumerate() {
            r -= x;
            if l == r {
                return i as i32;
            }
            l += x;
        }

        -1
    }
}
