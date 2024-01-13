func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	cnt := [1001]int{}
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		cnt[b] += a
	}
	for b := 1000; b > 0 && truckSize > 0; b-- {
		a := cnt[b]
		if a > 0 {
			ans += b * min(truckSize, a)
			truckSize -= a
		}
	}
	return
}