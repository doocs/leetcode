impl Solution {
    pub fn missing_integer(nums: Vec<i32>) -> i32 {
        let mut s = nums[0];

        for j in 1..nums.len() {
            if nums[j] != nums[j - 1] + 1 {
                break;
            }
            s += nums[j];
        }

        const M: usize = 51;
        let mut st = [false; M];

        for &x in &nums {
            st[x as usize] = true;
        }

        while s < M as i32 && st[s as usize] {
            s += 1;
        }

        s
    }
}
