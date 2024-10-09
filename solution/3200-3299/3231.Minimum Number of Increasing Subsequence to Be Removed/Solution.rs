impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut g = Vec::new();
        for &x in nums.iter() {
            let mut l = 0;
            let mut r = g.len();
            while l < r {
                let mid = (l + r) / 2;
                if g[mid] < x {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if l == g.len() {
                g.push(x);
            } else {
                g[l] = x;
            }
        }
        g.len() as i32
    }
}
