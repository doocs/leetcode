impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        let n = target.len();
        let mut count = [0; 1001];
        for i in 0..n {
            count[target[i] as usize] += 1;
            count[arr[i] as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
