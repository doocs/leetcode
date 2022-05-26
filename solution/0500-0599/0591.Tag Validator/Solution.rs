impl Solution {
    pub fn is_valid(code: String) -> bool {
        fn check(tag: &str) -> bool {
            let n = tag.len();
            n >= 1 && n <= 9 && tag.as_bytes().iter().all(|b| b.is_ascii_uppercase())
        }

        let mut stk = Vec::new();
        let mut i = 0;
        while i < code.len() {
            if i > 0 && stk.is_empty() {
                return false;
            }
            if code[i..].starts_with("<![CDATA[") {
                match code[i + 9..].find("]]>") {
                    Some(n) => i += n + 11,
                    None => return false,
                };
            } else if code[i..].starts_with("</") {
                let j = i + 2;
                match code[j..].find('>') {
                    Some(n) => {
                        let t = &code[j..j + n];
                        if !check(t) || stk.is_empty() || stk.pop().unwrap() != t {
                            return false;
                        }
                        i += n + 2;
                    }
                    None => return false,
                };
            } else if code[i..].starts_with("<") {
                let j = i + 1;
                match code[j..].find('>') {
                    Some(n) => {
                        let t = &code[j..j + n];
                        if !check(t) {
                            return false;
                        }
                        stk.push(t);
                    }
                    None => return false,
                };
            }
            i += 1;
        }
        stk.is_empty()
    }
}
