func minimizeError(prices []string, target int) string {
	arr := []float64{}
	mi := 0
	for _, p := range prices {
		price, _ := strconv.ParseFloat(p, 64)
		mi += int(math.Floor(price))
		d := price - float64(math.Floor(price))
		if d > 0 {
			arr = append(arr, d)
		}
	}
	if target < mi || target > mi+len(arr) {
		return "-1"
	}
	d := target - mi
	sort.Float64s(arr)
	ans := float64(d)
	for i := 0; i < d; i++ {
		ans -= arr[len(arr)-i-1]
	}
	for i := d; i < len(arr); i++ {
		ans += arr[len(arr)-i-1]
	}
	return fmt.Sprintf("%.3f", ans)
}