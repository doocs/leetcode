impl Solution {
    pub fn successful_pairs(spells: Vec<i32>, mut potions: Vec<i32>, success: i64) -> Vec<i32> {
        potions.sort();
        let m = potions.len();
        let mut ans = Vec::with_capacity(spells.len());

        for &v in &spells {
            let target = (success + v as i64 - 1) / v as i64;
            let idx = potions.partition_point(|&p| (p as i64) < target);
            ans.push((m - idx) as i32);
        }

        ans
    }
}
