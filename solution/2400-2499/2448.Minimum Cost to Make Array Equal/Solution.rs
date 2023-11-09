impl Solution {
    #[allow(dead_code)]
    pub fn min_cost(nums: Vec<i32>, cost: Vec<i32>) -> i64 {
        let mut zip_vec: Vec<_> = nums.into_iter().zip(cost.into_iter()).collect();

        // Sort the zip vector based on nums
        zip_vec.sort_by(|lhs, rhs| { lhs.0.cmp(&rhs.0) });

        let (nums, cost): (Vec<i32>, Vec<i32>) = zip_vec.into_iter().unzip();

        let mut sum: i64 = 0;
        for &c in &cost {
            sum += c as i64;
        }
        let middle_cost = (sum + 1) / 2;
        let mut cur_sum: i64 = 0;
        let mut i = 0;
        let n = nums.len();

        while i < n {
            if (cost[i] as i64) + cur_sum >= middle_cost {
                break;
            }
            cur_sum += cost[i] as i64;
            i += 1;
        }

        Self::compute_manhattan_dis(&nums, &cost, nums[i])
    }

    #[allow(dead_code)]
    fn compute_manhattan_dis(v: &Vec<i32>, c: &Vec<i32>, e: i32) -> i64 {
        let mut ret = 0;
        let n = v.len();

        for i in 0..n {
            if v[i] == e {
                continue;
            }
            ret += ((v[i] - e).abs() as i64) * (c[i] as i64);
        }

        ret
    }
}
