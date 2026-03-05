impl Solution {
    pub fn get_happy_string(n: i32, mut k: i32) -> String {
        if k > 3 * (1 << (n - 1)) {
            return String::new();
        }
        let cs = ['a', 'b', 'c'];
        let mut ans: Vec<char> = Vec::with_capacity(n as usize);
        for i in 0..n {
            let remain = 1 << (n - i - 1);
            for &c in &cs {
                if !ans.is_empty() && *ans.last().unwrap() == c {
                    continue;
                }
                if k <= remain {
                    ans.push(c);
                    break;
                }
                k -= remain;
            }
        }
        ans.into_iter().collect()
    }
}
