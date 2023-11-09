impl Solution {
    pub fn max_alive_year(birth: Vec<i32>, death: Vec<i32>) -> i32 {
        let n = birth.len();
        let mut d = vec![0; 102];
        let base = 1900;
        for i in 0..n {
            d[(birth[i] - base) as usize] += 1;
            d[(death[i] - base + 1) as usize] -= 1;
        }
        let mut ans = 0;
        let mut mx = 0;
        let mut s = 0;
        for i in 0..102 {
            s += d[i];
            if mx < s {
                mx = s;
                ans = base + (i as i32);
            }
        }
        ans
    }
}
