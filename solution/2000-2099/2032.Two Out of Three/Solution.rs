impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let get = |nums: Vec<i32>| -> [i32; 101] {
            let mut s = [0; 101];
            for v in nums {
                s[v as usize] = 1;
            }
            s
        };

        let s1 = get(nums1);
        let s2 = get(nums2);
        let s3 = get(nums3);
        let mut ans = Vec::new();

        for i in 1..=100 {
            if s1[i] + s2[i] + s3[i] > 1 {
                ans.push(i as i32);
            }
        }
        ans
    }
}
