func minimumPossibleSum(n int, target int) (ans int64) {
	vis := make([]bool, n+target)
	for i := 1; n > 0; i, n = i+1, n-1 {
		for vis[i] {
			i++
		}
		ans += int64(i)
		if target >= i {
			vis[target-i] = true
		}
	}
	return
}