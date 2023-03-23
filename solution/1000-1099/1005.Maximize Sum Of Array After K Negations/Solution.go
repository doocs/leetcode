func largestSumAfterKNegations(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := -100; x < 0 && k > 0; x++ {
		if cnt[x] > 0 {
			m := min(k, cnt[x])
			cnt[x] -= m
			cnt[-x] += m
			k -= m
		}
	}
	if k&1 == 1 && cnt[0] == 0 {
		for x := 1; x <= 100; x++ {
			if cnt[x] > 0 {
				cnt[x]--
				cnt[-x]++
				break
			}
		}
	}
	for x, v := range cnt {
		ans += x * v
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}