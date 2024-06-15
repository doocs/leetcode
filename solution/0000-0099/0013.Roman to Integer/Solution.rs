impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        let d = vec![('I', 1), ('V', 5), ('X', 10), ('L', 50), ('C', 100), ('D', 500), ('M', 1000)]
            .into_iter()
            .collect::<std::collections::HashMap<_, _>>();

        let s: Vec<char> = s.chars().collect();
        let mut ans = 0;
        let len = s.len();

        for i in 0..len - 1 {
            if d[&s[i]] < d[&s[i + 1]] {
                ans -= d[&s[i]];
            } else {
                ans += d[&s[i]];
            }
        }

        ans += d[&s[len - 1]];
        ans
    }
}
