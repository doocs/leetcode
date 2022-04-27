impl Solution {
    pub fn interpret(command: String) -> String {
        const ss: [&str; 3] = ["G", "o", "al"];
        let n = command.len();
        let bs = command.as_bytes();
        let mut res = String::new();
        let mut i = 0;
        while i < n {
            if bs[i] == b'G' {
                res.push_str(ss[0]);
                i += 1;
            } else if bs[i + 1] == b')' {
                res.push_str(ss[1]);
                i += 2
            } else {
                res.push_str(ss[2]);
                i += 4
            }
        }
        res
    }
}
