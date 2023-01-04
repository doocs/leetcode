func summaryRanges(nums []int) (ans []string) {
	f := func(i, j int) string {
		if i == j {
			return strconv.Itoa(nums[i])
		}
		return strconv.Itoa(nums[i]) + "->" + strconv.Itoa(nums[j])
	}
	for i, j, n := 0, 0, len(nums); i < n; i = j + 1 {
		j = i
		for j+1 < n && nums[j+1] == nums[j]+1 {
			j++
		}
		ans = append(ans, f(i, j))
	}
	return
}