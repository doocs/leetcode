impl Solution {
    pub fn max_score_indices(nums: Vec<i32>) -> Vec<i32> {
        let mut l0 = 0;
        let mut r1: i32 = nums.iter().sum();
        let mut mx = r1;
        let mut ans = vec![0];

        for i in 1..=nums.len() {
            let x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            let t = l0 + r1;
            if mx == t {
                ans.push(i as i32);
            } else if mx < t {
                mx = t;
                ans = vec![i as i32];
            }
        }

        ans
    }
}
