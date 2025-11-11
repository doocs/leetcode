impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let cnt = nums.iter().filter(|&&x| x == 1).count() as i32;
        if cnt > 0 {
            return n - cnt;
        }
        let mut mi = n + 1;
        for i in 0..nums.len() {
            let mut g = 0;
            for j in i..nums.len() {
                g = gcd(g, nums[j]);
                if g == 1 {
                    mi = mi.min((j - i + 1) as i32);
                    break;
                }
            }
        }
        if mi > n {
            -1
        } else {
            n - 1 + mi - 1
        }
    }
}

fn gcd(mut a: i32, mut b: i32) -> i32 {
    while b != 0 {
        let tmp = a % b;
        a = b;
        b = tmp;
    }
    a.abs()
}
