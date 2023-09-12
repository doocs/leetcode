func countInterestingSubarrays(nums []int, modulo int, k int) (ans int64) {
	arr := make([]int, len(nums))
	for i, x := range nums {
		if x%modulo == k {
			arr[i] = 1
		}
	}
	cnt := map[int]int{}
	cnt[0] = 1
	s := 0
	for _, x := range arr {
		s += x
		ans += int64(cnt[(s-k+modulo)%modulo])
		cnt[s%modulo]++
	}
	return
}