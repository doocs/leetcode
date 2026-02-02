impl Solution {
    pub fn min_bitwise_array(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = Vec::with_capacity(nums.len());
        for x in nums {
            if x == 2 {
                ans.push(-1);
            } else {
                for i in 1..32 {
                    if (((x >> i) & 1) ^ 1) == 1 {
                        ans.push(x ^ (1 << (i - 1)));
                        break;
                    }
                }
            }
        }
        ans
    }
}
