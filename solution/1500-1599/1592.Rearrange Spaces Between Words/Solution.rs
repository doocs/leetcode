impl Solution {
    fn create_spaces(n: usize) -> String {
        let mut res = String::new();
        for _ in 0..n {
            res.push(' ');
        }
        res
    }

    pub fn reorder_spaces(text: String) -> String {
        let count = {
            let mut res = 0;
            for c in text.as_bytes() {
                if c == &b' ' {
                    res += 1;
                }
            }
            res
        };

        let works = text.split_whitespace().collect::<Vec<&str>>();
        let n = works.len();
        if n == 1 {
            return works[0].to_string() + &Self::create_spaces(count);
        }
        works.join(&Self::create_spaces((count / (n - 1)))) + &Self::create_spaces(count % (n - 1))
    }
}
