func sumOddLengthSubarrays(arr []int) (ans int) {
	f, g := arr[0], 0
	ans = f
	for i := 1; i < len(arr); i++ {
		ff := g + arr[i]*(i/2+1)
		gg := f + arr[i]*((i+1)/2)
		f, g = ff, gg
		ans += f
	}
	return
}
