func sumDivisibleByK(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x, v := range cnt {
		if v%k == 0 {
			ans += x * v
		}
	}
	return
}
