impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        let n = trainers.len();
        for (i, &p) in players.iter().enumerate() {
            while j < n && trainers[j] < p {
                j += 1;
            }
            if j == n {
                return i as i32;
            }
            j += 1;
        }
        players.len() as i32
    }
}
