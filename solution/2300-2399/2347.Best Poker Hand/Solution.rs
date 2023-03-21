impl Solution {
    pub fn best_hand(ranks: Vec<i32>, suits: Vec<char>) -> String {
        if suits.iter().all(|v| *v == suits[0]) {
            return "Flush".to_string();
        }
        let mut count = [0; 14];
        let mut is_pair = false;
        for &v in ranks.iter() {
            let i = v as usize;
            count[i] += 1;
            if count[i] == 3 {
                return "Three of a Kind".to_string();
            }
            is_pair = is_pair || count[i] == 2;
        }
        (if is_pair { "Pair" } else { "High Card" }).to_string()
    }
}
