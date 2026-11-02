func minMergedInteger(nums []int) int {
	ans := math.MaxInt32
	for i := 0; i < len(nums); i += 2 {
		v, _ := strconv.Atoi(strconv.Itoa(nums[i]) + strconv.Itoa(nums[i+1]))
		ans = min(ans, v)
	}
	return ans
}
