impl Solution {
    pub fn min_domino_rotations(tops: Vec<i32>, bottoms: Vec<i32>) -> i32 {
        let n = tops.len() as i32;
        let f = |x: i32| -> i32 {
            let mut cnt1 = 0;
            let mut cnt2 = 0;
            for i in 0..n as usize {
                if tops[i] != x && bottoms[i] != x {
                    return n + 1;
                }
                if tops[i] == x {
                    cnt1 += 1;
                }
                if bottoms[i] == x {
                    cnt2 += 1;
                }
            }
            n - cnt1.max(cnt2)
        };

        let ans = f(tops[0]).min(f(bottoms[0]));
        if ans > n {
            -1
        } else {
            ans
        }
    }
}
