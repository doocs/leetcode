impl Solution {
    pub fn smallest_equivalent_string(s1: String, s2: String, base_str: String) -> String {
        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        let mut p = (0..26).collect::<Vec<_>>();
        for (a, b) in s1.bytes().zip(s2.bytes()) {
            let x = (a - b'a') as usize;
            let y = (b - b'a') as usize;
            let px = find(x, &mut p);
            let py = find(y, &mut p);
            if px < py {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }

        base_str
            .bytes()
            .map(|c| (b'a' + find((c - b'a') as usize, &mut p) as u8) as char)
            .collect()
    }
}
