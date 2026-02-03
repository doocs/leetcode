impl Solution {
    pub fn max_sum_trionic(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut i = 0;
        let mut ans = i64::MIN;
        while i < n {
            let l = i;
            i += 1;
            while i < n && nums[i - 1] < nums[i] {
                i += 1;
            }
            if i == l + 1 {
                continue;
            }

            let p = i - 1;
            let mut s = nums[p - 1] as i64 + nums[p] as i64;
            while i < n && nums[i - 1] > nums[i] {
                s += nums[i] as i64;
                i += 1;
            }
            if i == p + 1 || i == n || nums[i - 1] == nums[i] {
                continue;
            }

            let q = i - 1;
            s += nums[i] as i64;
            i += 1;
            let mut mx = 0i64;
            let mut t = 0i64;
            while i < n && nums[i - 1] < nums[i] {
                t += nums[i] as i64;
                i += 1;
                mx = mx.max(t);
            }
            s += mx;

            mx = 0;
            t = 0;
            let mut j = p as isize - 2;
            while j >= l as isize {
                t += nums[j as usize] as i64;
                mx = mx.max(t);
                j -= 1;
            }
            s += mx;

            ans = ans.max(s);
            i = q;
        }
        ans
    }
}
