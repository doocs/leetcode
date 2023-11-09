impl Solution {
    pub fn predict_party_victory(senate: String) -> String {
        let mut qr = std::collections::VecDeque::new();
        let mut qd = std::collections::VecDeque::new();
        let n = senate.len();
        for i in 0..n {
            if let Some(char) = senate.chars().nth(i) {
                if char == 'R' {
                    qr.push_back(i);
                } else {
                    qd.push_back(i);
                }
            }
        }

        while !qr.is_empty() && !qd.is_empty() {
            let front_qr = qr.pop_front().unwrap();
            let front_qd = qd.pop_front().unwrap();
            if front_qr < front_qd {
                qr.push_back(front_qr + n);
            } else {
                qd.push_back(front_qd + n);
            }
        }
        if qr.is_empty() {
            return "Dire".to_string();
        }
        "Radiant".to_string()
    }
}
