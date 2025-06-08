impl Solution {
    pub fn find_kth_number(n: i32, k: i32) -> i32 {
        fn count(mut curr: i64, n: i32) -> i32 {
            let mut next = curr + 1;
            let mut total = 0;
            let n = n as i64;
            while curr <= n {
                total += std::cmp::min(n - curr + 1, next - curr);
                curr *= 10;
                next *= 10;
            }
            total as i32
        }

        let mut curr = 1;
        let mut k = k - 1;

        while k > 0 {
            let cnt = count(curr as i64, n);
            if k >= cnt {
                k -= cnt;
                curr += 1;
            } else {
                k -= 1;
                curr *= 10;
            }
        }

        curr
    }
}
