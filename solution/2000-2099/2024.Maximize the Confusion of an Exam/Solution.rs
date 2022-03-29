impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let bs = answer_key.as_bytes();
        let n = bs.len();
        let get_max_count = |target| {
            let mut l = 0;
            let mut u = k;
            for b in bs.iter() {
                if b != &target {
                    u -= 1;
                }
                if u < 0 {
                    if bs[l] != target {
                        u += 1;
                    }
                    l += 1;
                }
            }
            n - l
        };
        get_max_count(b'T').max(get_max_count(b'F')) as i32
    }
}
