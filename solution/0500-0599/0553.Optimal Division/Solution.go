func optimalDivision(nums []int) string {
	n := len(nums)
	if n == 1 {
		return strconv.Itoa(nums[0])
	}
	if n == 2 {
		return fmt.Sprintf("%d/%d", nums[0], nums[1])
	}
	ans := &strings.Builder{}
	ans.WriteString(fmt.Sprintf("%d/(", nums[0]))
	for _, num := range nums[1 : n-1] {
		ans.WriteString(strconv.Itoa(num))
		ans.WriteByte('/')
	}
	ans.WriteString(fmt.Sprintf("%d)", nums[n-1]))
	return ans.String()
}