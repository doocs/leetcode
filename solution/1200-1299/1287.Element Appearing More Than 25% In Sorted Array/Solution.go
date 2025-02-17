func findSpecialInteger(arr []int) int {
	for i := 0; ; i++ {
		if arr[i] == arr[i+len(arr)/4] {
			return arr[i]
		}
	}
}
