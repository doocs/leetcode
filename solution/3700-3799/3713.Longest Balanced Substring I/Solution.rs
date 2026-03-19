impl Solution {
    pub fn longest_balanced(s: String) -> i32 {
        let n: i32 = s.len() as i32;
        let bytes = s.as_bytes();
        let mut ans: i32 = 0;

        for i in 0..n {
            let mut cnt: [i32; 26] = [0; 26];
            let mut mx: i32 = 0;
            let mut v: i32 = 0;

            for j in i..n {
                let c: usize = (bytes[j as usize] - b'a') as usize;
                cnt[c] += 1;

                if cnt[c] == 1 {
                    v += 1;
                }

                mx = mx.max(cnt[c]);

                if mx * v == j - i + 1 {
                    ans = ans.max(j - i + 1);
                }
            }
        }

        ans
    }
}
