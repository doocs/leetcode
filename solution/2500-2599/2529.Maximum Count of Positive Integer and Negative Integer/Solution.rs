impl Solution {
    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for x in nums {
            if x > 0 {
                a += 1;
            } else if x < 0 {
                b += 1;
            }
        }

        std::cmp::max(a, b)
    }
}
