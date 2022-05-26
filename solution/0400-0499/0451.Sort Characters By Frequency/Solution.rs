use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(s: String) -> String {
        let mut map = HashMap::new();
        for c in s.chars() {
            map.insert(c, map.get(&c).unwrap_or(&0) + 1);
        }
        let mut arr = map.into_iter().collect::<Vec<(char, i32)>>();
        arr.sort_unstable_by(|(_, a), (_, b)| b.cmp(&a));
        arr.into_iter()
            .map(|(c, v)| vec![c; v as usize].into_iter().collect::<String>())
            .collect()
    }
}
