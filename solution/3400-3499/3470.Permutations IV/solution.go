func permute(n int, k int64) []int {
	var f [105]int64
	f[0] = 1
	for i := 1; i <= n; i++ {
		if f[i-1] >= k {
			f[i] = k
		} else {
			f[i] = f[i-1] * int64((i+1)>>1)
		}
	}
	if n%2 == 0 {
		f[n] *= 2
	}
	if f[n] < k {
		return []int{}
	}
	k--
	ans := make([]int, n)
	a := [2][]int{}
	for i := 0; i < n; i++ {
		a[i&1] = append(a[i&1], i)
	}

	if n%2 == 1 {
		ans[0] = int(k/f[n-1]) * 2
		k -= int64(ans[0]/2) * f[n-1]
	} else {
		ans[0] = int(k / f[n-1])
		k -= int64(ans[0]) * f[n-1]
	}

	index := sort.SearchInts(a[ans[0]&1], ans[0])
	a[ans[0]&1] = append(a[ans[0]&1][:index], a[ans[0]&1][index+1:]...)

	for i := 1; i < n; i++ {
		if n%2 == 1 {
			ans[i] = a[i&1][k/f[n-i-1]]
		} else {
			ans[i] = a[(ans[0]^i)&1][k/f[n-i-1]]
		}
		k %= f[n-i-1]

		index = sort.SearchInts(a[ans[i]&1], ans[i])
		a[ans[i]&1] = append(a[ans[i]&1][:index], a[ans[i]&1][index+1:]...)
	}

	for i := 0; i < n; i++ {
		ans[i]++
	}
	return ans
}
