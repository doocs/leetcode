impl Solution {
    pub fn split_num(num: i32) -> i32 {
        let mut s = num.to_string().into_bytes();
        s.sort_unstable();

        let mut ans = vec![0; 2];
        for (i, c) in s.iter().enumerate() {
            ans[i & 1] = ans[i & 1] * 10 + ((c - b'0') as i32);
        }

        ans[0] + ans[1]
    }
}
