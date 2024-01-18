use std::io;

fn heap_sort(nums: &mut Vec<i32>) {
    let n = nums.len();
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

fn main() -> io::Result<()> {
    let mut s = String::new();
    io::stdin().read_line(&mut s)?;
    let s: Vec<usize> = s
        .split(' ')
        .map(|s| s.trim().parse().unwrap())
        .collect();
    // let n = s[0];
    let m = s[1];

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums
        .split(' ')
        .map(|s| s.trim().parse().unwrap())
        .collect();

    heap_sort(&mut nums);
    for num in nums.iter().take(m) {
        print!("{} ", num);
    }

    Ok(())
}
