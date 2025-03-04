impl Solution {
    pub fn jump(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        let mut last = 0;
        for i in 0..(nums.len() - 1) {
            mx = mx.max(i as i32 + nums[i]);
            if last == i as i32 {
                ans += 1;
                last = mx;
            }
        }
        ans
    }
}
