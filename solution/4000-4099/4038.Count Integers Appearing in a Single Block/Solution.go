func countSpecialIntegers(nums []int) int {
	cnt := [101]int{}
	for i, x := range nums {
		if i == 0 || x != nums[i-1] {
			cnt[x]++
		}
	}
	ans := 0
	for _, c := range cnt {
		if c == 1 {
			ans++
		}
	}
	return ans
}
