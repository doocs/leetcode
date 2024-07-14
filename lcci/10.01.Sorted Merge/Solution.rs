impl Solution {
    pub fn merge(a: &mut Vec<i32>, m: i32, b: &mut Vec<i32>, n: i32) {
        let (mut i, mut j) = (m - 1, n - 1);
        for k in (0..m + n).rev() {
            if j < 0 || (i >= 0 && a[i as usize] > b[j as usize]) {
                a[k as usize] = a[i as usize];
                i -= 1;
            } else {
                a[k as usize] = b[j as usize];
                j -= 1;
            }
        }
    }
}
