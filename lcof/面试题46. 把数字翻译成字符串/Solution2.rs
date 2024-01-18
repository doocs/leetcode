impl Solution {
    fn dfs(s: &String, i: usize, res: &mut i32) {
        if i >= s.len() {
            return;
        }
        let val = s[i - 1..=i].parse::<i32>().unwrap();
        if val >= 10 && val <= 25 {
            *res += 1;
            Self::dfs(s, i + 2, res);
        }
        Self::dfs(s, i + 1, res);
    }

    pub fn translate_num(num: i32) -> i32 {
        let s = num.to_string();
        let mut res = 1;
        Self::dfs(&s, 1, &mut res);
        res
    }
}
