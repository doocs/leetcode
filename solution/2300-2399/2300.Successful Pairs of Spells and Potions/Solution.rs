impl Solution {
    pub fn successful_pairs(spells: Vec<i32>, mut potions: Vec<i32>, success: i64) -> Vec<i32> {
        potions.sort();
        let m = potions.len();

        spells
            .into_iter()
            .map(|v| {
                let i = potions
                    .binary_search_by(|&p| {
                        let prod = (p as i64) * (v as i64);
                        if prod >= success {
                            std::cmp::Ordering::Greater
                        } else {
                            std::cmp::Ordering::Less
                        }
                    })
                    .unwrap_or_else(|x| x);
                (m - i) as i32
            })
            .collect()
    }
}
