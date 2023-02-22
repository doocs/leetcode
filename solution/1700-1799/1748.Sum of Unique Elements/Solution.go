func sumOfUnique(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := 0; x < 101; x++ {
		if cnt[x] == 1 {
			ans += x
		}
	}
	return
}