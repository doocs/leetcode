func elementInNums(nums []int, queries [][]int) []int {
	n, m := len(nums), len(queries)
	ans := make([]int, m)
	for j, q := range queries {
		t, i := q[0], q[1]
		t %= (n * 2)
		ans[j] = -1
		if t < n && i < n-t {
			ans[j] = nums[i+t]
		} else if t > n && i < t-n {
			ans[j] = nums[i]
		}
	}
	return ans
}