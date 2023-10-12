impl Solution {
    pub fn max_operations(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = std::collections::HashMap::new();
        let mut ans = 0;
        for x in nums {
            let m = k - x;
            if let Some(v) = cnt.get_mut(&m) {
                ans += 1;
                *v -= 1;
                if *v == 0 {
                    cnt.remove(&m);
                }
            } else if let Some(v) = cnt.get_mut(&x) {
                *v += 1;
            } else {
                cnt.insert(x, 1);
            }
        }
        ans
    }
}
