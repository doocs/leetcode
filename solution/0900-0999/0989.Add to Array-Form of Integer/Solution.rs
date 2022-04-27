impl Solution {
    pub fn add_to_array_form(num: Vec<i32>, mut k: i32) -> Vec<i32> {
        let n = num.len();
        let mut res = vec![];
        let mut i = 0;
        let mut sum = 0;
        while i < n || sum != 0 || k != 0 {
            sum += num.get(n - i - 1).unwrap_or(&0);
            sum += k % 10;
            res.push(sum % 10);

            i += 1;
            k /= 10;
            sum /= 10;
        }
        res.reverse();
        res
    }
}
