impl Solution {
    pub fn min_array(numbers: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = numbers.len() - 1;
        while l < r {
            let mid = l + r >> 1;
            match numbers[mid].cmp(&numbers[r]) {
                std::cmp::Ordering::Less => r = mid,
                std::cmp::Ordering::Equal => r -= 1,
                std::cmp::Ordering::Greater => l = mid + 1,
            }
        }
        numbers[l]
    }
}
