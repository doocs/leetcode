impl Solution {
    pub fn min_changes(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut d = vec![0; (k + 2) as usize];
        for i in 0..n / 2 {
            let x = nums[i].min(nums[n - i - 1]);
            let y = nums[i].max(nums[n - i - 1]);
            d[0] += 1;
            d[(y - x) as usize] -= 1;
            d[(y - x + 1) as usize] += 1;
            let idx = (y.max(k - x) + 1) as usize;
            d[idx] -= 1;
            d[idx] += 2;
        }
        let mut ans = n as i32;
        let mut s = 0;
        for x in d {
            s += x;
            ans = ans.min(s);
        }
        ans
    }
}
