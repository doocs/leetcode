func threeEqualParts(arr []int) []int {
	n := len(arr)
	cnt1 := 0
	for _, v := range arr {
		cnt1 += v
	}
	cnt := cnt1 / 3
	mod := cnt1 % 3
	if mod != 0 {
		return []int{-1, -1}
	}
	if cnt == 0 {
		return []int{0, n - 1}
	}
	find := func(cnt int) int {
		s := 0
		for i, v := range arr {
			s += v
			if s == cnt {
				return i
			}
		}
		return -1
	}
	i, j, k := find(1), find(cnt+1), find(cnt*2+1)
	for k < n && arr[i] == arr[j] && arr[j] == arr[k] {
		i++
		j++
		k++
	}
	if k == n {
		return []int{i - 1, j}
	}
	return []int{-1, -1}
}