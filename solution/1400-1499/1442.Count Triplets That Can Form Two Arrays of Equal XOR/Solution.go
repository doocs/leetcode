func countTriplets(arr []int) int {
	n := len(arr)
	pre := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre[i+1] = pre[i] ^ arr[i]
	}
	ans := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			for k := j; k < n; k++ {
				a, b := pre[j]^pre[i], pre[k+1]^pre[j]
				if a == b {
					ans++
				}
			}
		}
	}
	return ans
}