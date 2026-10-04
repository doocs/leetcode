fn insertion_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    for i in 1..n {
        let mut j = i;
        let temp = nums[i];
        while j > 0 && nums[j - 1] > temp {
            nums[j] = nums[j - 1];
            j -= 1;
        }
        nums[j] = temp;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    insertion_sort(&mut nums);
    println!("{:?}", nums);
}
