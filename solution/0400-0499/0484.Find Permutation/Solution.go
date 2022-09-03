func findPermutation(s string) []int {
	n := len(s)
	ans := make([]int, n+1)
	for i := range ans {
		ans[i] = i + 1
	}
	i := 0
	for i < n {
		j := i
		for ; j < n && s[j] == 'D'; j++ {
		}
		reverse(ans, i, j)
		i = max(i+1, j)
	}
	return ans
}

func reverse(arr []int, i, j int) {
	for ; i < j; i, j = i+1, j-1 {
		arr[i], arr[j] = arr[j], arr[i]
	}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}