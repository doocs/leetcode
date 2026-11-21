impl Solution {
    pub fn ways_to_make_fair(nums: Vec<i32>) -> i32 {
        let s: i32 = nums.iter().sum();
        let r0: i32 = nums.iter().step_by(2).sum();
        let mut r = [r0, s - r0];
        let mut l = [0, 0];
        let mut ans = 0;
        for (i, &x) in nums.iter().enumerate() {
            r[i % 2] -= x;
            if l[0] + r[1] == l[1] + r[0] {
                ans += 1;
            }
            l[i % 2] += x;
        }
        ans
    }
}
