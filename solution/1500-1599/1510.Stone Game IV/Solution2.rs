impl Solution {
    pub fn winner_square_game(n: i32) -> bool {
        let n = n as usize;
        let mut f = vec![false; n + 1];

        for i in 1..=n {
            let mut j = 1;
            while j <= i / j {
                if !f[i - j * j] {
                    f[i] = true;
                    break;
                }
                j += 1;
            }
        }

        f[n]
    }
}
