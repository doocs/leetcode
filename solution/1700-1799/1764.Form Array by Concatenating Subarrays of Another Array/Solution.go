func canChoose(groups [][]int, nums []int) bool {
	check := func(a, b []int, j int) bool {
		m, n := len(a), len(b)
		i := 0
		for ; i < m && j < n; i, j = i+1, j+1 {
			if a[i] != b[j] {
				return false
			}
		}
		return i == m
	}
	n, m := len(groups), len(nums)
	i := 0
	for j := 0; i < n && j < m; {
		if check(groups[i], nums, j) {
			j += len(groups[i])
			i++
		} else {
			j++
		}
	}
	return i == n
}