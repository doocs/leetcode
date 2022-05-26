fn selection_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 0..n - 1 {
        let mut min_index = i;
        for j in i..n {
            if nums[j] < nums[min_index] {
                min_index = j;
            }
        }
        let temp = nums[min_index];
        nums[min_index] = nums[i];
        nums[i] = temp;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    selection_sort(&mut nums);
    println!("{:?}", nums);
}
