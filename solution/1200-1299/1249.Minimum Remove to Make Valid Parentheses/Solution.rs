impl Solution {
    pub fn min_remove_to_make_valid(s: String) -> String {
        let bs = s.as_bytes();
        let mut right = {
            let mut left = 0;
            let mut right = 0;
            for c in bs.iter() {
                match c {
                    &b'(' => left += 1,
                    &b')' if right < left => right += 1,
                    _ => {}
                }
            }
            right
        };
        let mut has_left = 0;
        let mut res = vec![];
        for c in bs.iter() {
            match c {
                &b'(' => {
                    if has_left < right {
                        has_left += 1;
                        res.push(*c);
                    }
                }
                &b')' => {
                    if has_left != 0 && right != 0 {
                        right -= 1;
                        has_left -= 1;
                        res.push(*c);
                    }
                }
                _ => {
                    res.push(*c);
                }
            }
        }
        String::from_utf8_lossy(&res).to_string()
    }
}
