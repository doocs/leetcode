impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut idx = Vec::new();
        for i in 0..n {
            if nums[i] == key {
                idx.push(i as i32);
            }
        }

        let search = |x: i32| -> usize {
            let (mut l, mut r) = (0, idx.len());
            while l < r {
                let mid = (l + r) >> 1;
                if idx[mid] >= x {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            l
        };

        let mut ans = Vec::new();
        for i in 0..n {
            let l = search(i as i32 - k);
            let r = search(i as i32 + k + 1) as i32 - 1;
            if l as i32 <= r {
                ans.push(i as i32);
            }
        }

        ans
    }
}
