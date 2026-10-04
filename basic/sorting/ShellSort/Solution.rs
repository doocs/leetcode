fn shell_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    let mut gap = n / 2;
    while gap > 0 {
        for i in gap..n {
            let mut j = i;
            let temp = nums[i];
            while j >= gap && nums[j - gap] > temp {
                nums[j] = nums[j - gap];
                j -= gap;
            }
            nums[j] = temp;
        }
        gap /= 2;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    shell_sort(&mut nums);
    println!("{:?}", nums);
}
