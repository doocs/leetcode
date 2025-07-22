impl Solution {
    pub fn minimum_subarray_length(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut cnt = vec![0; 32];
        let mut ans = n as i32 + 1;
        let mut s = 0;
        let mut i = 0;

        for (j, &x) in nums.iter().enumerate() {
            s |= x;
            for h in 0..32 {
                if (x >> h) & 1 == 1 {
                    cnt[h] += 1;
                }
            }

            while s >= k && i <= j {
                ans = ans.min((j - i + 1) as i32);
                let y = nums[i];
                for h in 0..32 {
                    if (y >> h) & 1 == 1 {
                        cnt[h] -= 1;
                        if cnt[h] == 0 {
                            s ^= 1 << h;
                        }
                    }
                }
                i += 1;
            }
        }
        if ans > n as i32 {
            -1
        } else {
            ans
        }
    }
}
