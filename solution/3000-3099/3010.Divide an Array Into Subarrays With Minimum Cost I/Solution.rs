impl Solution {
    pub fn minimum_cost(nums: Vec<i32>) -> i32 {
        let a: i32 = nums[0];
        let mut b: i32 = i32::MAX;
        let mut c: i32 = i32::MAX;

        for &x in nums.iter().skip(1) {
            if x < b {
                c = b;
                b = x;
            } else if x < c {
                c = x;
            }
        }

        a + b + c
    }
}
