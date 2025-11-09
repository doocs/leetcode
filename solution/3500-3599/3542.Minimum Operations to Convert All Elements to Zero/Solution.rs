impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut stk = Vec::new();
        let mut ans = 0;
        for &x in nums.iter() {
            while let Some(&last) = stk.last() {
                if last > x {
                    ans += 1;
                    stk.pop();
                } else {
                    break;
                }
            }
            if x != 0 && (stk.is_empty() || *stk.last().unwrap() != x) {
                stk.push(x);
            }
        }
        ans += stk.len() as i32;
        ans
    }
}
