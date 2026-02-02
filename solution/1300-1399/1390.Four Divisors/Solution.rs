impl Solution {
    pub fn sum_four_divisors(nums: Vec<i32>) -> i32 {
        let f = |x: i32| -> i32 {
            let mut cnt = 2;
            let mut s = x + 1;
            let mut i = 2;
            while i <= x / i {
                if x % i == 0 {
                    cnt += 1;
                    s += i;
                    if i * i != x {
                        cnt += 1;
                        s += x / i;
                    }
                }
                i += 1;
            }
            if cnt == 4 { s } else { 0 }
        };
        let mut ans = 0;
        for x in nums {
            ans += f(x);
        }
        ans
    }
}
