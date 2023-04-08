use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(s: String) -> String {
        let mut cnt = HashMap::new();
        for c in s.chars() {
            cnt.insert(c, cnt.get(&c).unwrap_or(&0) + 1);
        }
        let mut cs = cnt.into_iter().collect::<Vec<(char, i32)>>();
        cs.sort_unstable_by(|(_, a), (_, b)| b.cmp(&a));
        cs.into_iter()
            .map(|(c, v)| vec![c; v as usize].into_iter().collect::<String>())
            .collect()
    }
}
