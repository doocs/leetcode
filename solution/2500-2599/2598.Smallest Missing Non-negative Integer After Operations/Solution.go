func findSmallestInteger(nums []int, value int) int {
	cnt := make([]int, value)
	for _, x := range nums {
		cnt[(x%value+value)%value]++
	}
	for i := 0; ; i++ {
		if cnt[i%value] == 0 {
			return i
		}
		cnt[i%value]--
	}
}