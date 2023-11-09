impl Solution {
    pub fn max_score(mut nums: Vec<i32>) -> i32 {
        nums.sort_by(|a, b| b.cmp(a));
        let mut s: i64 = 0;
        for (i, &x) in nums.iter().enumerate() {
            s += x as i64;
            if s <= 0 {
                return i as i32;
            }
        }
        nums.len() as i32
    }
}
