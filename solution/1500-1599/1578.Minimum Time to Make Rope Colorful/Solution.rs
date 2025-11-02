impl Solution {
    pub fn min_cost(colors: String, needed_time: Vec<i32>) -> i32 {
        let n = needed_time.len();
        let mut ans = 0;
        let bytes = colors.as_bytes();
        let mut i = 0;

        while i < n {
            let mut j = i;
            let mut s = 0;
            let mut mx = 0;

            while j < n && bytes[j] == bytes[i] {
                s += needed_time[j];
                mx = mx.max(needed_time[j]);
                j += 1;
            }

            if j - i > 1 {
                ans += s - mx;
            }
            i = j;
        }

        ans
    }
}
