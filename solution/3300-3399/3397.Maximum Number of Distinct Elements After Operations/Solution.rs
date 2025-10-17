impl Solution {
    pub fn max_distinct_elements(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let mut ans = 0;
        let mut pre = i32::MIN;

        for &x in &nums {
            let cur = (x + k).min((x - k).max(pre + 1));
            if cur > pre {
                ans += 1;
                pre = cur;
            }
        }

        ans
    }
}
