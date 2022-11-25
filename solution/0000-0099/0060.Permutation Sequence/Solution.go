func getPermutation(n int, k int) string {
	ans := make([]byte, n)
	vis := make([]bool, n+1)
	for i := 0; i < n; i++ {
		fact := 1
		for j := 1; j < n-i; j++ {
			fact *= j
		}
		for j := 1; j <= n; j++ {
			if !vis[j] {
				if k > fact {
					k -= fact
				} else {
					ans[i] = byte('0' + j)
					vis[j] = true
					break
				}
			}
		}
	}
	return string(ans)
}