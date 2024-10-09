func relativeSortArray(arr1 []int, arr2 []int) []int {
	cnt := make([]int, 1001)
	mi, mx := 1001, 0
	for _, x := range arr1 {
		cnt[x]++
		mi = min(mi, x)
		mx = max(mx, x)
	}
	ans := make([]int, 0, len(arr1))
	for _, x := range arr2 {
		for cnt[x] > 0 {
			ans = append(ans, x)
			cnt[x]--
		}
	}
	for x := mi; x <= mx; x++ {
		for cnt[x] > 0 {
			ans = append(ans, x)
			cnt[x]--
		}
	}
	return ans
}
