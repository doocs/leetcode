func sumOfPower(nums []int, k int) int {
	const mod int = 1e9 + 7
	f := make([]int, k+1)
	f[0] = 1
	for _, x := range nums {
		for j := k; j >= 0; j-- {
			f[j] = f[j] * 2 % mod
			if j >= x {
				f[j] = (f[j] + f[j-x]) % mod
			}
		}
	}
	return f[k]
}
