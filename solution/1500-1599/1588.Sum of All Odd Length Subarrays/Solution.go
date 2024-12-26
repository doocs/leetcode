func sumOddLengthSubarrays(arr []int) (ans int) {
	n := len(arr)
	f := make([]int, n)
	g := make([]int, n)
	f[0] = arr[0]
	ans = f[0]
	for i := 1; i < n; i++ {
		f[i] = g[i-1] + arr[i]*(i/2+1)
		g[i] = f[i-1] + arr[i]*((i+1)/2)
		ans += f[i]
	}
	return
}
