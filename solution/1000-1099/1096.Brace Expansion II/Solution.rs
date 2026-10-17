use std::collections::BTreeSet;

impl Solution {
    pub fn brace_expansion_ii(expression: String) -> Vec<String> {
        fn asd(aa: String, bb: &mut BTreeSet<String>) {
            let Some(cc) = aa.find('}') else {
                bb.insert(aa);
                return;
            };
            let dd = aa[..cc].rfind('{').unwrap();
            for ee in aa[dd + 1..cc].split(',') {
                asd(format!("{}{}{}", &aa[..dd], ee, &aa[cc + 1..]), bb);
            }
        }
        let mut cc = BTreeSet::new();
        asd(expression, &mut cc);
        cc.into_iter().collect()
    }
}
