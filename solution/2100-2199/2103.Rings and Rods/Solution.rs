impl Solution {
    pub fn count_points(rings: String) -> i32 {
        let mut d: [i32; 90] = [0; 90];
        d['R' as usize] = 1;
        d['G' as usize] = 2;
        d['B' as usize] = 4;

        let mut mask: [i32; 10] = [0; 10];

        let cs: Vec<char> = rings.chars().collect();

        for i in (0..cs.len()).step_by(2) {
            let c = cs[i] as usize;
            let j = (cs[i + 1] as usize) - ('0' as usize);
            mask[j] |= d[c];
        }

        mask
            .iter()
            .filter(|&&x| x == 7)
            .count() as i32
    }
}
