func largestUniqueNumber(nums []int) int {
	cnt := [1001]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := 1000; x >= 0; x-- {
		if cnt[x] == 1 {
			return x
		}
	}
	return -1
}