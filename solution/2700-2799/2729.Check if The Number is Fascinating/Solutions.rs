impl Solution {
    pub fn is_fascinating(n: i32) -> bool {
        let s = format!("{}{}{}", n, n * 2, n * 3);

        let mut cnt = vec![0; 10];
        for c in s.chars() {
            let t = (c as usize) - ('0' as usize);
            cnt[t] += 1;
            if cnt[t] > 1 {
                return false;
            }
        }

        cnt[0] == 0 && s.len() == 9
    }
}
