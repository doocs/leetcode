impl Solution {
    pub fn merge_triplets(triplets: Vec<Vec<i32>>, target: Vec<i32>) -> bool {
        let [x, y, z]: [i32; 3] = target.try_into().unwrap();
        let (mut d, mut e, mut f) = (0, 0, 0);

        for triplet in triplets {
            if let [a, b, c] = triplet[..] {
                if a <= x && b <= y && c <= z {
                    d = d.max(a);
                    e = e.max(b);
                    f = f.max(c);
                }
            }
        }

        [d, e, f] == [x, y, z]
    }
}
