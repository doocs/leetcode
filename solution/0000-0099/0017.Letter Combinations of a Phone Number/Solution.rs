use std::collections::HashMap;

impl Solution {
    fn dfs(
        i: usize,
        s: &mut String,
        cs: &Vec<char>,
        map: &HashMap<char, String>,
        res: &mut Vec<String>,
    ) {
        if i == cs.len() {
            res.push(s.clone());
            return;
        }
        for c in map.get(&cs[i]).unwrap().chars() {
            s.push(c);
            Self::dfs(i + 1, s, cs, map, res);
            s.pop();
        }
    }

    pub fn letter_combinations(digits: String) -> Vec<String> {
        let mut res = vec![];
        if digits.is_empty() {
            return res;
        }

        let mut map = HashMap::new();
        map.insert('2', String::from("abc"));
        map.insert('3', String::from("def"));
        map.insert('4', String::from("ghi"));
        map.insert('5', String::from("jkl"));
        map.insert('6', String::from("mno"));
        map.insert('7', String::from("pqrs"));
        map.insert('8', String::from("tuv"));
        map.insert('9', String::from("wxyz"));

        Self::dfs(
            0,
            &mut String::new(),
            &digits.chars().collect(),
            &map,
            &mut res,
        );
        res
    }
}
