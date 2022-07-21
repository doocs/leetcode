func maximumSum(nums []int) int {
	d := map[int][]int{}
	for i, v := range nums {
		t := 0
		for v > 0 {
			t += v % 10
			v /= 10
		}
		d[t] = append(d[t], nums[i])
	}
	ans := -1
	for _, v := range d {
		n := len(v)
		if n > 1 {
			sort.Ints(v)
			ans = max(ans, v[n-1]+v[n-2])
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}