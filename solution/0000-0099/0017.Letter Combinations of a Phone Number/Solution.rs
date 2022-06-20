impl Solution {
    fn dfs(i: usize, digits: &[u8], map: &Vec<Vec<char>>, s: &mut String, res: &mut Vec<String>) {
        if i == digits.len() {
            res.push(s.clone());
            return;
        }
        for c in map[(digits[i] - b'2') as usize].iter() {
            s.push(*c);
            Self::dfs(i + 1, digits, map, s, res);
            s.pop();
        }
    }

    pub fn letter_combinations(digits: String) -> Vec<String> {
        if digits.is_empty() {
            return Vec::new();
        }
        let digits = digits.as_bytes();
        let map = vec![
            vec!['a', 'b', 'c'],
            vec!['d', 'e', 'f'],
            vec!['g', 'h', 'i'],
            vec!['j', 'k', 'l'],
            vec!['m', 'n', 'o'],
            vec!['p', 'q', 'r', 's'],
            vec!['t', 'u', 'v'],
            vec!['w', 'x', 'y', 'z'],
        ];
        let mut res = Vec::new();
        Self::dfs(0, digits, &map, &mut String::new(), &mut res);
        res
    }
}
