impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let cnt = nums.iter().map(|num| (num >> i) & 1).sum::<i32>();
            ans |= cnt % 3 << i;
        }
        ans
    }
}
