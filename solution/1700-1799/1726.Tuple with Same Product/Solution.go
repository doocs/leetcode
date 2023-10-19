func tupleSameProduct(nums []int) (ans int) {
	cnt := map[int]int{}
	for i := 1; i < len(nums); i++ {
		for j := 0; j < i; j++ {
			x := nums[i] * nums[j]
			cnt[x]++
		}
	}
	for _, v := range cnt {
		ans += v * (v - 1) / 2
	}
	return ans << 3
}