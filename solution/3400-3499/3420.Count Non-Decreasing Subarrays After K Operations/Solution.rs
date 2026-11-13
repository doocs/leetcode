impl Solution {
    pub fn count_non_decreasing_subarrays(nums: Vec<i32>, k: i32) -> i64 {
        let mut ans: i64 = 0;
        let mut cost: i64 = 0;
        let mut stk: std::collections::VecDeque<(i32, i32)> = std::collections::VecDeque::new();
        let mut l: i64 = nums.len() as i64 - 1;
        let mut r: i64 = l;
        while l >= 0 {
            let x = nums[l as usize];
            let mut cnt: i32 = 1;
            while let Some(&(v, c)) = stk.back().filter(|&&(v, _)| v < x) {
                stk.pop_back();
                cnt += c;
                cost += (x - v) as i64 * c as i64;
            }
            stk.push_back((x, cnt));
            while cost > k as i64 {
                let &(v, c) = stk.front().unwrap();
                stk.pop_front();
                cost -= (v - nums[r as usize]) as i64;
                r -= 1;
                if c > 1 {
                    stk.push_front((v, c - 1));
                }
            }
            ans += r - l + 1;
            l -= 1;
        }
        ans
    }
}
