func minNumber(nums []int) string {
	arr := []string{}
	for _, x := range nums {
		arr = append(arr, strconv.Itoa(x))
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i]+arr[j] < arr[j]+arr[i] })
	return strings.Join(arr, "")
}