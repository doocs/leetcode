impl Solution {
    pub fn can_partition(nums: Vec<i32>) -> bool {
        let s: i32 = nums.iter().sum();
        if s % 2 != 0 {
            return false;
        }
        let m = (s / 2) as usize;
        let mut f = vec![false; m + 1];
        f[0] = true;

        for x in nums {
            let x = x as usize;
            for j in (x..=m).rev() {
                f[j] = f[j] || f[j - x];
            }
        }

        f[m]
    }
}
