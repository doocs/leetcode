impl Solution {
    pub fn reorder_spaces(text: String) -> String {
        let spaces = text.chars().filter(|&c| c == ' ').count();
        let words: Vec<&str> = text.split_whitespace().collect();
        if words.len() == 1 {
            return format!("{}{}", words[0], " ".repeat(spaces));
        }
        let cnt = spaces / (words.len() - 1);
        let mod_spaces = spaces % (words.len() - 1);
        let result = words.join(&" ".repeat(cnt));
        result + &" ".repeat(mod_spaces)
    }
}
