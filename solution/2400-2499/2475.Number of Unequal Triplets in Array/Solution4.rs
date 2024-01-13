impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut nums = nums;
        nums.sort();
        let n = nums.len();

        for i in 1..n - 1 {
            let mut l = 0;
            let mut r = i;
            while l < r {
                let mid = (l + r) >> 1;
                if nums[mid] >= nums[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let j = r;

            let mut l = i + 1;
            let mut r = n;
            while l < r {
                let mid = (l + r) >> 1;
                if nums[mid] > nums[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let k = r;

            ans += j * (n - k);
        }

        ans as i32
    }
}
