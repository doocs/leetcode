func numIdenticalPairs(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for _, v := range cnt {
		ans += v * (v - 1) / 2
	}
	return
}