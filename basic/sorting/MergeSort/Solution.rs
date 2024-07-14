use std::io;

fn merge_sort(nums: &mut Vec<i32>, left: usize, right: usize) {
    if left >= right {
        return;
    }

    let mid = left + (right - left) / 2;
    merge_sort(nums, left, mid);
    merge_sort(nums, mid + 1, right);

    let mut temp = Vec::new();
    let mut i = left;
    let mut j = mid + 1;

    while i <= mid && j <= right {
        if nums[i] < nums[j] {
            temp.push(nums[i]);
            i += 1;
        } else {
            temp.push(nums[j]);
            j += 1;
        }
    }
    while i <= mid {
        temp.push(nums[i]);
        i += 1;
    }
    while j <= right {
        temp.push(nums[j]);
        j += 1;
    }

    for i in left..=right {
        nums[i] = temp[i - left];
    }
}

fn main() -> io::Result<()> {
    let mut n = String::new();
    io::stdin().read_line(&mut n)?;
    let n = n.trim().parse::<usize>().unwrap();

    let mut nums = String::new();
    io::stdin().read_line(&mut nums)?;
    let mut nums: Vec<i32> = nums.split(' ').map(|s| s.trim().parse().unwrap()).collect();

    merge_sort(&mut nums, 0, n - 1);
    for num in nums.iter() {
        print!("{} ", num);
    }

    Ok(())
}
