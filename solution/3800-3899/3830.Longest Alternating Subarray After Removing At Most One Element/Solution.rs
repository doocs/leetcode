impl Solution {
    pub fn longest_alternating(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut l1 = vec![1; n];
        let mut l2 = vec![1; n];
        let mut r1 = vec![1; n];
        let mut r2 = vec![1; n];

        let mut ans = 0;

        for i in 1..n {
            if nums[i - 1] < nums[i] {
                l1[i] = l2[i - 1] + 1;
            } else if nums[i - 1] > nums[i] {
                l2[i] = l1[i - 1] + 1;
            }
            ans = ans.max(l1[i]);
            ans = ans.max(l2[i]);
        }

        for i in (0..n - 1).rev() {
            if nums[i + 1] > nums[i] {
                r1[i] = r2[i + 1] + 1;
            } else if nums[i + 1] < nums[i] {
                r2[i] = r1[i + 1] + 1;
            }
        }

        for i in 1..n - 1 {
            if nums[i - 1] < nums[i + 1] {
                ans = ans.max(l2[i - 1] + r2[i + 1]);
            } else if nums[i - 1] > nums[i + 1] {
                ans = ans.max(l1[i - 1] + r1[i + 1]);
            }
        }

        ans
    }
}
