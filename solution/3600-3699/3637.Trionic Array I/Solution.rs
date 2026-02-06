impl Solution {
    pub fn is_trionic(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut p = 0usize;

        while p + 2 < n && nums[p] < nums[p + 1] {
            p += 1;
        }
        if p == 0 {
            return false;
        }

        let mut q = p;
        while q + 1 < n && nums[q] > nums[q + 1] {
            q += 1;
        }
        if q == p || q + 1 == n {
            return false;
        }

        while q + 1 < n && nums[q] < nums[q + 1] {
            q += 1;
        }

        q + 1 == n
    }
}
