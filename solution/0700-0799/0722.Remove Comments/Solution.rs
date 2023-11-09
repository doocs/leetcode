impl Solution {
    pub fn remove_comments(source: Vec<String>) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();
        let mut t: Vec<String> = Vec::new();
        let mut blockComment = false;

        for s in &source {
            let m = s.len();
            let mut i = 0;
            while i < m {
                if blockComment {
                    if i + 1 < m && &s[i..i + 2] == "*/" {
                        blockComment = false;
                        i += 2;
                    } else {
                        i += 1;
                    }
                } else {
                    if i + 1 < m && &s[i..i + 2] == "/*" {
                        blockComment = true;
                        i += 2;
                    } else if i + 1 < m && &s[i..i + 2] == "//" {
                        break;
                    } else {
                        t.push(s.chars().nth(i).unwrap().to_string());
                        i += 1;
                    }
                }
            }
            if !blockComment && !t.is_empty() {
                ans.push(t.join(""));
                t.clear();
            }
        }
        ans
    }
}
