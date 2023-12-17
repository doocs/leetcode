impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        for mask in 0..1 << n {
            let mut t = Vec::new();
            let mut ok = true;
            for i in 0..n {
                if ((mask >> i) & 1) == 1 {
                    if i > 0 && ((mask >> (i - 1)) & 1) == 0 && nums[i] == nums[i - 1] {
                        ok = false;
                        break;
                    }
                    t.push(nums[i]);
                }
            }
            if ok {
                ans.push(t);
            }
        }
        ans
    }
}
