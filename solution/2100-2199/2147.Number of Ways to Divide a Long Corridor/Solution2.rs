impl Solution {
    pub fn number_of_ways(corridor: String) -> i32 {
        let modv: i64 = 1_000_000_007;
        let mut ans: i64 = 1;
        let mut cnt: i64 = 0;
        let mut last: i64 = 0;

        for (i, ch) in corridor.chars().enumerate() {
            if ch == 'S' {
                cnt += 1;
                if cnt > 2 && cnt % 2 == 1 {
                    ans = ans * (i as i64 - last) % modv;
                }
                last = i as i64;
            }
        }

        if cnt > 0 && cnt % 2 == 0 {
            ans as i32
        } else {
            0
        }
    }
}
