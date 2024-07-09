impl Solution {
    pub fn halves_are_alike(s: String) -> bool {
        let n = s.len() / 2;
        let vowels: std::collections::HashSet<char> = "aeiouAEIOU".chars().collect();
        let mut cnt = 0;

        for i in 0..n {
            if vowels.contains(&s.chars().nth(i).unwrap()) {
                cnt += 1;
            }
            if vowels.contains(&s.chars().nth(i + n).unwrap()) {
                cnt -= 1;
            }
        }

        cnt == 0
    }
}
