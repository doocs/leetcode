impl Solution {
    pub fn merge_arrays(nums1: Vec<Vec<i32>>, nums2: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut cnt = vec![0; 1001];

        for x in &nums1 {
            cnt[x[0] as usize] += x[1];
        }

        for x in &nums2 {
            cnt[x[0] as usize] += x[1];
        }

        let mut ans = vec![];
        for i in 0..cnt.len() {
            if cnt[i] > 0 {
                ans.push(vec![i as i32, cnt[i] as i32]);
            }
        }

        ans
    }
}
