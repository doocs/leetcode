impl Solution {
    pub fn magical_string(n: i32) -> i32 {
        let mut s = vec![1, 2, 2];
        let mut i = 2;

        while s.len() < n as usize {
            let pre = s[s.len() - 1];
            let cur = 3 - pre;
            for _ in 0..s[i] {
                s.push(cur);
            }
            i += 1;
        }

        s.iter().take(n as usize).filter(|&&x| x == 1).count() as i32
    }
}
