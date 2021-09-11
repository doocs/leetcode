fn heap_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
    // 自底向上的下沉
    for i in (0..n / 2).rev() {
        sink(nums, i, n);
    }
    for i in (1..n).rev() {
        let temp = nums[0];
        nums[0] = nums[i];
        nums[i] = temp;
        sink(nums, 0, i);
    }
}

fn sink(nums: &mut Vec<i32>, mut i: usize, n: usize) {
    loop {
        let left = i * 2 + 1;
        let right = left + 1;
        let mut largest = i;
        if left < n && nums[left] > nums[largest] {
            largest = left;
        }
        if right < n && nums[right] > nums[largest] {
            largest = right;
        }
        if largest == i {
            break;
        }
        let temp = nums[i];
        nums[i] = nums[largest];
        nums[largest] = temp;
        i = largest;
    }
}

fn main() {
    let mut nums = vec![1, 2, 7, 9, 5, 8];
    heap_sort(&mut nums);
    println!("{:?}", nums);
}
