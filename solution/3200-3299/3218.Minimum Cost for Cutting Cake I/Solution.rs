impl Solution {
    pub fn minimum_cost(m: i32, n: i32, mut horizontal_cut: Vec<i32>, mut vertical_cut: Vec<i32>) -> i32 {
        horizontal_cut.sort();
        vertical_cut.sort();
        let (mut i, mut j) = ((m - 2) as isize, (n - 2) as isize);
        let (mut h, mut v) = (1, 1);
        let mut ans = 0;

        while i >= 0 || j >= 0 {
            if j < 0 || (i >= 0 && horizontal_cut[i as usize] > vertical_cut[j as usize]) {
                ans += horizontal_cut[i as usize] * v;
                i -= 1;
                h += 1;
            } else {
                ans += vertical_cut[j as usize] * h;
                j -= 1;
                v += 1;
            }
        }

        ans
    }
}
