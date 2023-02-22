impl Solution {
    pub fn sum_of_unique(nums: Vec<i32>) -> i32 {
        let mut cnt = [0; 101];
        for x in nums {
            cnt[x as usize] += 1;
        }
        let mut ans = 0;
        for x in 1..101 {
            if cnt[x] == 1 {
                ans += x;
            }
        }
        ans as i32
    }
}