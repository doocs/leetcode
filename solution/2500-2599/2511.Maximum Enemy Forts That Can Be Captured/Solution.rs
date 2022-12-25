impl Solution {
    pub fn capture_forts(forts: Vec<i32>) -> i32 {
        let n = forts.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            if forts[i] != 0 {
                while j < n && forts[j] == 0 {
                    j += 1;
                }
                if j < n && forts[i] + forts[j] == 0 {
                    ans = ans.max(j - i - 1);
                }
            }
            i = j;
        }
        ans as i32
    }
}
