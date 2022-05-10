use std::collections::HashMap;
struct AuthenticationManager {
    time_to_live: i32,
    map: HashMap<String, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl AuthenticationManager {
    fn new(timeToLive: i32) -> Self {
        Self {
            time_to_live: timeToLive,
            map: HashMap::new(),
        }
    }

    fn generate(&mut self, token_id: String, current_time: i32) {
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn renew(&mut self, token_id: String, current_time: i32) {
        if self.map.get(&token_id).unwrap_or(&0) <= &current_time {
            return;
        }
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn count_unexpired_tokens(&self, current_time: i32) -> i32 {
        self.map.values().filter(|&time| *time > current_time).count() as i32
    }
}


/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * let obj = AuthenticationManager::new(timeToLive);
 * obj.generate(tokenId, currentTime);
 * obj.renew(tokenId, currentTime);
 * let ret_3: i32 = obj.count_unexpired_tokens(currentTime);
 */