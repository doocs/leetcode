func minSetSize(arr []int) (ans int) {
	mx := 0
	for _, x := range arr {
		mx = max(mx, x)
	}
	cnt := make([]int, mx+1)
	for _, x := range arr {
		cnt[x]++
	}
	sort.Ints(cnt)
	for i, m := mx, 0; ; i-- {
		if cnt[i] > 0 {
			m += cnt[i]
			ans++
			if m >= len(arr)/2 {
				return
			}
		}
	}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}