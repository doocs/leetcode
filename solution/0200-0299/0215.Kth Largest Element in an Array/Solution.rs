use rand::Rng;

impl Solution {
    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let k = k as usize;
        let n = nums.len();
        let mut l = 0;
        let mut r = n;
        while l <= k - 1 && l < r {
            nums.swap(l, rand::thread_rng().gen_range(l, r));
            let num = nums[l];
            let mut mark = l;
            for i in l..r {
                if nums[i] > num {
                    mark += 1;
                    nums.swap(i, mark);
                }
            }
            nums.swap(l, mark);
            if mark + 1 <= k {
                l = mark + 1;
            } else {
                r = mark;
            }
        }
        nums[k - 1]
    }
}
