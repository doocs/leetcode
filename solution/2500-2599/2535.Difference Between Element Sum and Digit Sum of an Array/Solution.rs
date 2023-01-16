impl Solution {
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for &num in nums.iter() {
            let mut num = num;
            ans += num;
            while num != 0 {
                ans -= num % 10;
                num /= 10;
            }
        }
        ans 
    }
}
