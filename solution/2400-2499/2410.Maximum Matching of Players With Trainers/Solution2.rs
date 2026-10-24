impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        for &t in &trainers {
            if j < players.len() && players[j] <= t {
                j += 1;
            }
        }
        j as i32
    }
}
