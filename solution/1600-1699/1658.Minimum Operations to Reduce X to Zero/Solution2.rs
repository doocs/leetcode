impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s: i32 = nums.iter().sum::<i32>() - x;
        let mut j: usize = 0;
        let mut t: i32 = 0;
        let mut mx: i32 = -1;
        for (i, &v) in nums.iter().enumerate() {
            t += v;
            while j <= i && t > s {
                t -= nums[j];
                j += 1;
            }
            if t == s {
                mx = mx.max((i - j + 1) as i32);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
