impl Solution {
    pub fn find_the_string(lcp: Vec<Vec<i32>>) -> String {
        let n = lcp.len();
        let mut s = vec!['\0'; n];
        let mut i = 0;

        for c in b'a'..=b'z' {
            while i < n && s[i] != '\0' {
                i += 1;
            }
            if i == n {
                break;
            }
            for j in i..n {
                if lcp[i][j] > 0 {
                    s[j] = c as char;
                }
            }
        }

        for i in 0..n {
            if s[i] == '\0' {
                return "".to_string();
            }
        }

        for i in (0..n).rev() {
            for j in (0..n).rev() {
                if s[i] == s[j] {
                    if i == n - 1 || j == n - 1 {
                        if lcp[i][j] != 1 {
                            return "".to_string();
                        }
                    } else if lcp[i][j] != lcp[i + 1][j + 1] + 1 {
                        return "".to_string();
                    }
                } else if lcp[i][j] > 0 {
                    return "".to_string();
                }
            }
        }

        s.into_iter().collect()
    }
}
