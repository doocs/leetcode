impl Solution {
    pub fn split_num(mut num: i32) -> i32 {
        let mut cnt = vec![0; 10];
        let mut n = 0;

        while num != 0 {
            cnt[(num as usize) % 10] += 1;
            num /= 10;
            n += 1;
        }

        let mut ans = vec![0; 2];
        let mut j = 0;
        for i in 0..n {
            while cnt[j] == 0 {
                j += 1;
            }
            cnt[j] -= 1;

            ans[i & 1] = ans[i & 1] * 10 + (j as i32);
        }

        ans[0] + ans[1]
    }
}
