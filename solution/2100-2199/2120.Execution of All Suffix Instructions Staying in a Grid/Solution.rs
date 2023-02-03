impl Solution {
    pub fn execute_instructions(n: i32, start_pos: Vec<i32>, s: String) -> Vec<i32> {
        let s = s.as_bytes();
        let m = s.len();
        let mut ans = vec![0; m];
        for i in 0..m {
            let mut y = start_pos[0];
            let mut x = start_pos[1];
            let mut j = i;
            while j < m {
                match s[j] {
                    b'U' => y -= 1,
                    b'D' => y += 1,
                    b'L' => x -= 1,
                    _ => x += 1,
                }
                if y == -1 || y == n || x == -1 || x == n {
                    break;
                }
                j += 1;
            }
            ans[i] = (j - i) as i32;
        }
        ans
    }
}
