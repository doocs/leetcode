use std::collections::BTreeMap;

#[allow(dead_code)]
struct Leaderboard {
    /// This also keeps track of the top K players since it's implicitly sorted
    record_map: BTreeMap<i32, i32>,
}

impl Leaderboard {
    #[allow(dead_code)]
    fn new() -> Self {
        Self {
            record_map: BTreeMap::new(),
        }
    }

    #[allow(dead_code)]
    fn add_score(&mut self, player_id: i32, score: i32) {
        if self.record_map.contains_key(&player_id) {
            // The player exists, just add the score
            self.record_map.insert(player_id, self.record_map.get(&player_id).unwrap() + score);
        } else {
            // Add the new player to the map
            self.record_map.insert(player_id, score);
        }
    }

    #[allow(dead_code)]
    fn top(&self, k: i32) -> i32 {
        let mut cur_vec: Vec<(i32, i32)> = self.record_map
            .iter()
            .map(|(k, v)| (*k, *v))
            .collect();
        cur_vec.sort_by(|lhs, rhs| { rhs.1.cmp(&lhs.1) });
        // Iterate reversely for K
        let mut sum = 0;
        let mut i = 0;
        for (_, value) in &cur_vec {
            if i == k {
                break;
            }
            sum += value;
            i += 1;
        }

        sum
    }

    #[allow(dead_code)]
    fn reset(&mut self, player_id: i32) {
        // The player is ensured to exist in the board
        // Just set the score to 0
        self.record_map.insert(player_id, 0);
    }
}
