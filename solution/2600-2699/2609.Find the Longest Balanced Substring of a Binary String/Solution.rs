impl Solution {
    pub fn find_the_longest_balanced_substring(s: String) -> i32 {
        let check = |i: usize, j: usize| -> bool {
            let mut cnt = 0;

            for k in i..=j {
                if s.as_bytes()[k] == b'1' {
                    cnt += 1;
                } else if cnt > 0 {
                    return false;
                }
            }

            cnt * 2 == j - i + 1
        };

        let mut ans = 0;
        let n = s.len();
        for i in 0..n - 1 {
            for j in (i + 1..n).rev() {
                if j - i + 1 < ans {
                    break;
                }

                if check(i, j) {
                    ans = std::cmp::max(ans, j - i + 1);
                    break;
                }
            }
        }

        ans as i32
    }
}
