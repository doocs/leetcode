impl Solution {
    pub fn minimum_pair_removal(nums: Vec<i32>) -> i32 {
        let mut arr: Vec<i32> = nums.clone();
        let mut ans: i32 = 0;

        fn is_non_decreasing(a: &Vec<i32>) -> bool {
            for i in 1..a.len() {
                if a[i] < a[i - 1] {
                    return false;
                }
            }
            true
        }

        while !is_non_decreasing(&arr) {
            let mut k: usize = 0;
            let mut s: i32 = arr[0] + arr[1];
            for i in 1..arr.len() - 1 {
                let t: i32 = arr[i] + arr[i + 1];
                if s > t {
                    s = t;
                    k = i;
                }
            }
            arr[k] = s;
            arr.remove(k + 1);
            ans += 1;
        }

        ans
    }
}
