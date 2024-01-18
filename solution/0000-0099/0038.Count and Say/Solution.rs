use std::iter::once;

impl Solution {
    pub fn count_and_say(n: i32) -> String {
        (1..n)
            .fold(vec![1], |curr, _| {
                let mut next = vec![];
                let mut slow = 0;
                for fast in 0..=curr.len() {
                    if fast == curr.len() || curr[slow] != curr[fast] {
                        next.extend(once((fast - slow) as u8).chain(once(curr[slow])));
                        slow = fast;
                    }
                }
                next
            })
            .into_iter()
            .map(|digit| (digit + b'0') as char)
            .collect()
    }
}
