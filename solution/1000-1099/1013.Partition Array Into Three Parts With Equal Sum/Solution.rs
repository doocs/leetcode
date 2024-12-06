impl Solution {
    pub fn can_three_parts_equal_sum(arr: Vec<i32>) -> bool {
        let sum: i32 = arr.iter().sum();
        let s = sum / 3;
        let mod_val = sum % 3;
        if mod_val != 0 {
            return false;
        }

        let mut cnt = 0;
        let mut t = 0;
        for &x in &arr {
            t += x;
            if t == s {
                cnt += 1;
                t = 0;
            }
        }

        cnt >= 3
    }
}
