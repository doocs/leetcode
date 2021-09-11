fn bubble_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 0..n - 1 {
        for j in i..n {
            if nums[i] > nums[j] {
                let temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    bubble_sort(&mut nums);
    println!("{:?}", nums);
}
