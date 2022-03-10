impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        fn my_search(nums: &Vec<i32>, target: i32, left: i32, right: i32) -> i32 {
            if left > right {
                return 0;
            }

            let index = (right - left) / 2 + left;
            let num = nums[index as usize];
            if num > target {
                my_search(nums, target, left, index - 1)
            } else if num < target {
                my_search(nums, target, index + 1, right)
            } else {
                // 搜索边界
                let mut count = 1;
                for i in (0..index).rev() {
                    if nums[i as usize] == target {
                        count += 1;
                    } else {
                        break;
                    }
                }
                for i in (index + 1)..nums.len() as i32 {
                    if nums[i as usize] == target {
                        count += 1;
                    } else {
                        break;
                    }
                }
                count
            }
        }

        my_search(&nums, target, 0, nums.len() as i32 - 1)
    }
}
