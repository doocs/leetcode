impl Solution {
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let mut x = 0;
        let mut y = 0;

        for &v in &nums {
            x += v;
            let mut num = v;
            while num > 0 {
                y += num % 10;
                num /= 10;
            }
        }

        x - y
    }
}
