impl Solution {
    #[allow(dead_code)]
    pub fn summary_ranges(nums: Vec<i32>) -> Vec<String> {
        if nums.is_empty() {
            return vec![];
        }

        let mut ret = Vec::new();
        let mut start = nums[0];
        let mut prev = nums[0];
        let mut current = 0;
        let n = nums.len();

        for i in 1..n {
            current = nums[i];
            if current != prev + 1 {
                if start == prev {
                    ret.push(start.to_string());
                } else {
                    ret.push(start.to_string() + "->" + &prev.to_string());
                }
                start = current;
                prev = current;
            } else {
                prev = current;
            }
        }

        if start == prev {
            ret.push(start.to_string());
        } else {
            ret.push(start.to_string() + "->" + &prev.to_string());
        }

        ret
    }
}
