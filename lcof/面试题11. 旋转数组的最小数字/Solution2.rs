impl Solution {
    pub fn min_array(numbers: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = numbers.len() - 1;
        while l < r {
            if numbers[l] < numbers[r] {
                break;
            }
            let mid = (l + r) >> 1;
            match numbers[mid].cmp(&numbers[l]) {
                std::cmp::Ordering::Less => {
                    r = mid;
                }
                std::cmp::Ordering::Equal => {
                    l += 1;
                }
                std::cmp::Ordering::Greater => {
                    l = mid + 1;
                }
            }
        }
        numbers[l]
    }
}
