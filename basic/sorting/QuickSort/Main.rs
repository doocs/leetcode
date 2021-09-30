use rand::Rng; // 0.7.2
use std::io;

fn quick_sort(nums: &mut Vec<i32>, left: usize, right: usize) {
    if left >= right {
        return;
    }

    let random_index = rand::thread_rng().gen_range(left, right + 1);
    let temp = nums[random_index];
    nums[random_index] = nums[left];
    nums[left] = temp;

    let pivot = nums[left];
    let mut i = left;
    let mut j = right;
    while i < j {
        while i < j && nums[j] >= pivot {
            j -= 1;
        }
        nums[i] = nums[j];
        while i < j && nums[i] < pivot {
            i += 1;
        }
        nums[j] = nums[i];
    }
    nums[i] = pivot;

    quick_sort(nums, left, i);
    quick_sort(nums, i + 1, right);
}

fn main() -> io::Result<()> {
    let mut n = String::new();
    io::stdin().read_line(&mut n)?;
    let n = n.trim().parse::<usize>().unwrap();

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums.split(' ').map(|s| s.trim().parse().unwrap()).collect();

    quick_sort(&mut nums, 0, n - 1);
    for num in nums.iter() {
        print!("{} ", num);
    }

    Ok(())
}
