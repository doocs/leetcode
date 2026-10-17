use std::collections::HashMap;

impl Solution {
    pub fn evaluate(s: String, knowledge: Vec<Vec<String>>) -> String {
        let aa: HashMap<&str, &str> = knowledge
            .iter()
            .map(|bb| (bb[0].as_str(), bb[1].as_str()))
            .collect();
        let mut cc = String::with_capacity(s.len());
        let mut dd = s.as_str();
        while let Some((ee, ff)) = dd.split_once('(') {
            let (gg, asd) = ff.split_once(')').unwrap();
            cc.push_str(ee);
            cc.push_str(aa.get(gg).copied().unwrap_or("?"));
            dd = asd;
        }
        cc.push_str(dd);
        cc
    }
}
