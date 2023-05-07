func colorTheArray(n int, queries [][]int) (ans []int) {
	nums := make([]int, n)
	x := 0
	for _, q := range queries {
		i, c := q[0], q[1]
		if i > 0 && nums[i] > 0 && nums[i-1] == nums[i] {
			x--
		}
		if i < n-1 && nums[i] > 0 && nums[i+1] == nums[i] {
			x--
		}
		if i > 0 && nums[i-1] == c {
			x++
		}
		if i < n-1 && nums[i+1] == c {
			x++
		}
		ans = append(ans, x)
		nums[i] = c
	}
	return
}