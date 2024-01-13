use rand::Rng;

impl Solution {
    fn sort(nums: &mut Vec<i32>, l: usize, r: usize, k: usize) {
        if l + 1 > k || l >= r {
            return;
        }
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

        Self::sort(nums, l, mark, k);
        Self::sort(nums, mark + 1, r, k);
    }

    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let k = k as usize;
        Self::sort(&mut nums, 0, n, k);
        nums[k - 1]
    }
}
