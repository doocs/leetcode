impl Solution {
    pub fn have_conflict(event1: Vec<String>, event2: Vec<String>) -> bool {
        !(event1[1] < event2[0] || event1[0] > event2[1])
    }
}
