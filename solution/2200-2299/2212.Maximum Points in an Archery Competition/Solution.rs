impl Solution {
    pub fn maximum_bob_points(num_arrows: i32, alice_arrows: Vec<i32>) -> Vec<i32> {
        let mut st = 0;
        let mut mx = 0;
        let m = alice_arrows.len();
        for mask in 1..(1 << m) {
            let mut cnt = 0;
            let mut s = 0;
            for i in 0..m {
                if (mask >> i) & 1 == 1 {
                    s += i as i32;
                    cnt += alice_arrows[i] + 1;
                }
            }
            if cnt <= num_arrows && s > mx {
                mx = s;
                st = mask;
            }
        }
        let mut ans = vec![0; m];
        let mut num_arrows = num_arrows;
        for i in 0..m {
            if (st >> i) & 1 == 1 {
                ans[i] = alice_arrows[i] + 1;
                num_arrows -= ans[i];
            }
        }
        ans[0] += num_arrows;
        ans
    }
}
