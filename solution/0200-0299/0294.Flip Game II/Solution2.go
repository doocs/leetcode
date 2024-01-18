func canWin(currentState string) bool {
	n := len(currentState)
	sg := make([]int, n+1)
	for i := range sg {
		sg[i] = -1
	}
	var win func(i int) int
	win = func(i int) int {
		if sg[i] != -1 {
			return sg[i]
		}
		vis := make([]bool, n)
		for j := 0; j < i-1; j++ {
			vis[win(j)^win(i-j-2)] = true
		}
		for j := 0; j < n; j++ {
			if !vis[j] {
				sg[i] = j
				return j
			}
		}
		return 0
	}
	ans, i := 0, 0
	for i < n {
		j := i
		for j < n && currentState[j] == '+' {
			j++
		}
		ans ^= win(j - i)
		i = j + 1
	}
	return ans > 0
}