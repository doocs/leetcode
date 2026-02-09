func countSubarrays(nums []int, k int64) int64 {
	var ans int64 = 0
	q1 := make([]int, 0)
	q2 := make([]int, 0)
	l := 0

	for r, x := range nums {
		for len(q1) > 0 && nums[q1[len(q1)-1]] <= x {
			q1 = q1[:len(q1)-1]
		}
		for len(q2) > 0 && nums[q2[len(q2)-1]] >= x {
			q2 = q2[:len(q2)-1]
		}
		q1 = append(q1, r)
		q2 = append(q2, r)

		for l < r &&
			int64(nums[q1[0]]-nums[q2[0]])*int64(r-l+1) > k {
			l++
			if q1[0] < l {
				q1 = q1[1:]
			}
			if q2[0] < l {
				q2 = q2[1:]
			}
		}
		ans += int64(r - l + 1)
	}
	return ans
}
