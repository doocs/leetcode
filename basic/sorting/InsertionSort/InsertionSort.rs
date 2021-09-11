fn insertion_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 1..n {
        let mut j = i - 1;
        let temp = nums[i];
        while j >= 0 as usize && nums[j] > temp {
            nums[j + 1] = nums[j];
            j -= 1;
        }
        nums[j + 1] = temp;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    insertion_sort(&mut nums);
    println!("{:?}", nums);
}
