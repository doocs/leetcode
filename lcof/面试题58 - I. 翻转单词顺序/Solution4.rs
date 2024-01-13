impl Solution {
    pub fn reverse_words(mut s: String) -> String {
        s = s.trim().to_string();
        // 添加辅助空格，防止 usize 破界
        s.insert_str(0, " ");
        let chars = s.chars().collect::<Vec<char>>();
        let mut res = vec![];
        let mut l = chars.len() - 1;
        let mut r = chars.len() - 1;
        while l > 0 {
            while chars[l] == ' ' {
                if l == 0 {
                    break;
                }
                l -= 1;
            }
            r = l;
            while chars[l] != ' ' {
                if l == 0 {
                    break;
                }
                l -= 1;
            }
            let mut str = String::new();
            for i in l + 1..r + 1 {
                str.push(chars[i]);
            }
            res.push(str);
        }
        res.join(" ")
    }
}
