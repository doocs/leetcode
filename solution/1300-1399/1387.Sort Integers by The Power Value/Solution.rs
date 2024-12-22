impl Solution {
    pub fn get_kth(lo: i32, hi: i32, k: i32) -> i32 {
        let f = |mut x: i32| -> i32 {
            let mut ans = 0;
            while x != 1 {
                if x % 2 == 0 {
                    x /= 2;
                } else {
                    x = 3 * x + 1;
                }
                ans += 1;
            }
            ans
        };

        let mut nums: Vec<i32> = (lo..=hi).collect();
        nums.sort_by(|&x, &y| f(x).cmp(&f(y)));
        nums[(k - 1) as usize]
    }
}
