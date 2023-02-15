func maxSumDivThree(nums []int) int {
	f := [3]int{}
	for _, x := range nums {
		a, b, c := f[0]+x, f[1]+x, f[2]+x
		f[a%3] = max(f[a%3], a)
		f[b%3] = max(f[b%3], b)
		f[c%3] = max(f[c%3], c)
	}
	return f[0]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}