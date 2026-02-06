impl Solution {
    pub fn min_removal(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let mut cnt = 0;
        let n = nums.len();
        for i in 0..n {
            let mut j = n;
            let target = nums[i] as i64 * k as i64;
            if target <= nums[n - 1] as i64 {
                j = nums.partition_point(|&x| x as i64 <= target);
            }
            cnt = cnt.max(j - i);
        }
        (n - cnt) as i32
    }
}
