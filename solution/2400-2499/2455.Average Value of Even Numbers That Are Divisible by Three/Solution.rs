impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut s = 0;
        let mut n = 0;
        for x in nums.iter() {
            if x % 6 == 0 {
                s += x;
                n += 1;
            }
        }
        if n == 0 {
            return 0;
        }
        s / n
    }
}
