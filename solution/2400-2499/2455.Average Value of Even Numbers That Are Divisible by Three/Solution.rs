impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut sum = 0;
        let mut n = 0;
        for num in nums.iter() {
            if num % 6 == 0 {
                sum += num;
                n += 1;
            }
        }

        if n == 0 {
            return 0;
        }
        sum / n
    }
}
