impl Solution {
    pub fn max_area(h: i32, w: i32, mut horizontal_cuts: Vec<i32>, mut vertical_cuts: Vec<i32>) -> i32 {
        const MOD: i64 = 1_000_000_007;
        
        horizontal_cuts.sort();
        vertical_cuts.sort();
        
        let m = horizontal_cuts.len();
        let n = vertical_cuts.len();
        
        let mut x = i64::max(horizontal_cuts[0] as i64, h as i64 - horizontal_cuts[m - 1] as i64);
        let mut y = i64::max(vertical_cuts[0] as i64, w as i64 - vertical_cuts[n - 1] as i64);
        
        for i in 1..m {
            x = i64::max(x, horizontal_cuts[i] as i64 - horizontal_cuts[i - 1] as i64);
        }
        
        for i in 1..n {
            y = i64::max(y, vertical_cuts[i] as i64 - vertical_cuts[i - 1] as i64);
        }
        
        ((x * y) % MOD) as i32
    }
}