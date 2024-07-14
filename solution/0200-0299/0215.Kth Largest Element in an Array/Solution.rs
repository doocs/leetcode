impl Solution {
    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let len = nums.len();
        let k = len - k as usize;
        Self::quick_sort(&mut nums, 0, len - 1, k)
    }

    fn quick_sort(nums: &mut Vec<i32>, l: usize, r: usize, k: usize) -> i32 {
        if l == r {
            return nums[l];
        }

        let (mut i, mut j) = (l as isize - 1, r as isize + 1);
        let x = nums[(l + r) / 2];

        while i < j {
            i += 1;
            while nums[i as usize] < x {
                i += 1;
            }

            j -= 1;
            while nums[j as usize] > x {
                j -= 1;
            }

            if i < j {
                nums.swap(i as usize, j as usize);
            }
        }

        let j = j as usize;
        if j < k {
            Self::quick_sort(nums, j + 1, r, k)
        } else {
            Self::quick_sort(nums, l, j, k)
        }
    }
}
