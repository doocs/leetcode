impl Solution {
    pub fn sort_array(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        Self::quick_sort(&mut nums, 0, n - 1);
        return nums;
    }

    fn quick_sort(nums: &mut Vec<i32>, left: usize, right: usize) {
        if left >= right {
            return;
        }
        let mut i = left as i32 - 1;
        let mut j = right as i32 + 1;
        let pivot = nums[left];
        while i < j {
            loop {
                i += 1;
                if nums[i as usize] >= pivot {
                    break;
                }
            }
            loop {
                j -= 1;
                if nums[j as usize] <= pivot {
                    break;
                }
            }
            if i < j {
                nums.swap(i as usize, j as usize);
            }
        }
        Self::quick_sort(nums, left, j as usize);
        Self::quick_sort(nums, j as usize + 1, right);
    }
}
