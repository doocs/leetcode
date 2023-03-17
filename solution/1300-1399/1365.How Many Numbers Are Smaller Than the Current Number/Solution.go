func smallerNumbersThanCurrent(nums []int) (ans []int) {
	cnt := [102]int{}
	for _, x := range nums {
		cnt[x+1]++
	}
	for i := 1; i < len(cnt); i++ {
		cnt[i] += cnt[i-1]
	}
	for _, x := range nums {
		ans = append(ans, cnt[x])
	}
	return
}