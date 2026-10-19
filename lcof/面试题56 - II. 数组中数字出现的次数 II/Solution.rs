impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut cnt = [0; 32];
        for x in nums.iter() {
            for i in 0..32 {
                cnt[i] += (x >> i) & 1;
            }
        }
        let mut ans = 0;
        for i in (0..32).rev() {
            ans <<= 1;
            ans |= cnt[i] % 3;
        }
        ans
    }
}
