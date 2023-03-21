func countTriplets(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		mx = max(mx, x)
	}
	cnt := make([]int, mx+1)
	for _, x := range nums {
		for _, y := range nums {
			cnt[x&y]++
		}
	}
	for xy := 0; xy <= mx; xy++ {
		for _, z := range nums {
			if xy&z == 0 {
				ans += cnt[xy]
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}