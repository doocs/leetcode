impl Solution {
    pub fn max_active_sections_after_trade(s: String) -> i32 {
        let (ones_count, _, max_gain) = s.as_bytes().chunk_by(|left, right| left == right).fold(
            (0, i32::MIN, 0),
            |(ones_count, previous_zeros, max_gain), block| {
                let block_length = block.len() as i32;
                if block[0] == b'1' {
                    (ones_count + block_length, previous_zeros, max_gain)
                } else {
                    (
                        ones_count,
                        block_length,
                        max_gain.max(previous_zeros + block_length),
                    )
                }
            },
        );
        ones_count + max_gain
    }
}
