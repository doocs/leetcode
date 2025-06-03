impl Solution {
    pub fn minimize_max(mut nums: Vec<i32>, p: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let (mut l, mut r) = (0, nums[n - 1] - nums[0] + 1);

        let check = |diff: i32| -> bool {
            let mut cnt = 0;
            let mut i = 0;
            while i < n - 1 {
                if nums[i + 1] - nums[i] <= diff {
                    cnt += 1;
                    i += 2;
                } else {
                    i += 1;
                }
            }
            cnt >= p
        };

        while l < r {
            let mid = (l + r) / 2;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        l
    }
}
