impl Solution {
    pub fn kth_smallest_subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut l = *nums.iter().min().unwrap();
        let mut r: i32 = nums.iter().sum();

        let f = |s: i32| -> i32 {
            let (mut cnt, mut t, mut j) = (0, 0, 0);

            for i in 0..nums.len() {
                t += nums[i];
                while t > s {
                    t -= nums[j];
                    j += 1;
                }
                cnt += (i - j + 1) as i32;
            }
            cnt
        };

        while l < r {
            let mid = (l + r) / 2;
            if f(mid) >= k {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
