func countPairs(nums []int, k int) (ans int) {
	for j, y := range nums {
		for i, x := range nums[:j] {
			if x == y && (i*j%k) == 0 {
				ans++
			}
		}
	}
	return
}
