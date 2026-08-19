use std::collections::HashMap;

impl Solution {
    pub fn max_number_of_families(n: i32, reserved_seats: Vec<Vec<i32>>) -> i32 {
        let mut d: HashMap<i32, i32> = HashMap::new();

        for e in reserved_seats {
            let row = e[0];
            let col = e[1];
            let mask = 1 << (10 - col);

            d.entry(row).and_modify(|x| *x |= mask).or_insert(mask);
        }

        let masks = [0b0111100000, 0b0000011110, 0b0001111000];

        let mut ans = (n - d.len() as i32) * 2;

        for mut x in d.values().copied() {
            for &mask in &masks {
                if (x & mask) == 0 {
                    x |= mask;
                    ans += 1;
                }
            }
        }

        ans
    }
}
