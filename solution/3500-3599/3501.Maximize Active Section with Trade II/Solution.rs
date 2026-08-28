impl Solution {
    pub fn max_active_sections_after_trade(s: String, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let bytes = s.as_bytes();
        let length = bytes.len();
        let total_ones = bytes.iter().filter(|byte| **byte == b'1').count() as i32;
        if !bytes.contains(&b'0') {
            return vec![total_ones; queries.len()];
        }
        let mut zero_blocks: Vec<(usize, usize)> = Vec::new();
        let mut zero_block_at_position = Vec::with_capacity(length);
        for index in 0..length {
            if bytes[index] == b'0' {
                if index > 0 && bytes[index - 1] == b'0' {
                    zero_blocks.last_mut().unwrap().1 += 1;
                } else {
                    zero_blocks.push((index, 1usize));
                }
            }
            zero_block_at_position.push(zero_blocks.len() as isize - 1);
        }
        let zero_block_count = zero_blocks.len();
        let adjacent_pair_count = zero_block_count.saturating_sub(1);
        let sparse_level_count = if adjacent_pair_count == 0 {
            0
        } else {
            usize::BITS as usize - adjacent_pair_count.leading_zeros() as usize
        };
        let mut sparse_table = vec![0; adjacent_pair_count * sparse_level_count];
        for pair_index in 0..adjacent_pair_count {
            sparse_table[pair_index] =
                (zero_blocks[pair_index].1 + zero_blocks[pair_index + 1].1) as i32;
        }
        for level in 1..sparse_level_count {
            let half_span = 1usize << (level - 1);
            let span = 1usize << level;
            for start in 0..=adjacent_pair_count - span {
                sparse_table[level * adjacent_pair_count + start] = sparse_table
                    [(level - 1) * adjacent_pair_count + start]
                    .max(sparse_table[(level - 1) * adjacent_pair_count + start + half_span]);
            }
        }
        let max_pair_sum = |left_pair: usize, right_pair: usize| -> i32 {
            let right_pair = right_pair.min(adjacent_pair_count - 1);
            if left_pair > right_pair {
                return 0;
            }
            let level =
                usize::BITS as usize - (right_pair - left_pair + 1).leading_zeros() as usize - 1;
            let span = 1usize << level;
            sparse_table[level * adjacent_pair_count + left_pair]
                .max(sparse_table[level * adjacent_pair_count + right_pair - span + 1])
        };
        queries
            .into_iter()
            .map(|query| {
                let left = query[0] as usize;
                let right = query[1] as usize;
                let left_block_index = zero_block_at_position[left];
                let right_block_index = zero_block_at_position[right];
                let left_zero_count = if left_block_index == -1 {
                    -1
                } else {
                    let block_index = left_block_index as usize;
                    zero_blocks[block_index].1 as i32 - (left - zero_blocks[block_index].0) as i32
                };
                let right_zero_count = if right_block_index == -1 {
                    -1
                } else {
                    let block_index = right_block_index as usize;
                    (right - zero_blocks[block_index].0 + 1) as i32
                };
                let first_internal_pair = left_block_index + 1;
                let last_internal_pair = (if bytes[right] == b'1' {
                    right_block_index
                } else {
                    right_block_index - 1
                }) - 1;
                let last_full_zero_block = if bytes[right] == b'1' {
                    right_block_index
                } else {
                    right_block_index - 1
                };
                let mut best_total = total_ones;
                if bytes[left] == b'0'
                    && bytes[right] == b'0'
                    && left_block_index + 1 == right_block_index
                {
                    best_total = best_total.max(total_ones + left_zero_count + right_zero_count);
                } else if first_internal_pair <= last_internal_pair {
                    best_total = best_total.max(
                        total_ones
                            + max_pair_sum(
                                first_internal_pair as usize,
                                last_internal_pair as usize,
                            ),
                    );
                }
                if bytes[left] == b'0' && left_block_index + 1 <= last_full_zero_block {
                    best_total = best_total.max(
                        total_ones
                            + left_zero_count
                            + zero_blocks[(left_block_index + 1) as usize].1 as i32,
                    );
                }
                if bytes[right] == b'0' && left_block_index < right_block_index - 1 {
                    best_total = best_total.max(
                        total_ones
                            + right_zero_count
                            + zero_blocks[(right_block_index - 1) as usize].1 as i32,
                    );
                }
                best_total
            })
            .collect()
    }
}
