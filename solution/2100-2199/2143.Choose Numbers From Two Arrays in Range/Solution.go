func countSubranges(nums1 []int, nums2 []int) (ans int) {
	n := len(nums1)
	s1, s2 := sum(nums1), sum(nums2)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, s1+s2+1)
	}
	const mod int = 1e9 + 7
	for i, a := range nums1 {
		b := nums2[i]
		f[i][a+s2]++
		f[i][-b+s2]++
		if i > 0 {
			for j := 0; j <= s1+s2; j++ {
				if j >= a {
					f[i][j] = (f[i][j] + f[i-1][j-a]) % mod
				}
				if j+b <= s1+s2 {
					f[i][j] = (f[i][j] + f[i-1][j+b]) % mod
				}
			}
		}
		ans = (ans + f[i][s2]) % mod
	}
	return
}

func sum(nums []int) (ans int) {
	for _, x := range nums {
		ans += x
	}
	return
}