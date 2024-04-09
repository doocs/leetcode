impl Solution {
    pub fn min_swaps(nums: Vec<i32>) -> i32 {
        let k: i32 = nums.iter().sum();
        let n: usize = nums.len();
        let mut cnt: i32 = 0;
        for i in 0..k {
            cnt += nums[i as usize];
        }
        let mut mx: i32 = cnt;
        for i in k..(n as i32) + k {
            cnt +=
                nums[(i % (n as i32)) as usize] -
                nums[((i - k + (n as i32)) % (n as i32)) as usize];
            mx = mx.max(cnt);
        }
        return k - mx;
    }
}
