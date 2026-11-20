impl Solution {
    pub fn max_selected_elements(mut nums: Vec<i32>) -> i32 {
        nums.sort_unstable();
        let mut ans = 1;
        let mut prev = i32::MIN;
        let mut f0 = 1;
        let mut f1 = 1;
        for &x in &nums {
            if x == prev {
                f1 = f0 + 1;
            } else if x == prev + 1 {
                f0 += 1;
                f1 += 1;
            } else if x == prev + 2 {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = ans.max(f0).max(f1);
            prev = x;
        }
        ans
    }
}
