impl Solution {
    pub fn compress_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        cs.push(' ');
        let mut res = vec![];
        let mut l = 0;
        let mut cur = cs[0];
        for i in 1..cs.len() {
            if cs[i] != cur {
                let count = (i - l).to_string();
                l = i;
                res.push(cur);
                cur = cs[i];
                for c in count.chars() {
                    res.push(c);
                }
            }
        }
        if res.len() >= cs.len() - 1 {
            s
        } else {
            res.iter().collect()
        }
    }
}
