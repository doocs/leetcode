func countNonDecreasingSubarrays(nums []int, k int) int64 {
	n := len(nums)
	q := []int{}
	var ans, cost int64
	r := n - 1
	for l := n - 1; l >= 0; l-- {
		x := nums[l]
		for len(q) > 0 && nums[q[len(q)-1]] <= x {
			s := q[len(q)-1]
			q = q[:len(q)-1]
			end := r + 1
			if len(q) > 0 {
				end = q[len(q)-1]
			}
			cost += int64(end-s) * int64(x-nums[s])
		}
		q = append(q, l)
		for cost > int64(k) {
			cost -= int64(nums[q[0]] - nums[r])
			if q[0] == r {
				q = q[1:]
			}
			r--
		}
		ans += int64(r - l + 1)
	}
	return ans
}
