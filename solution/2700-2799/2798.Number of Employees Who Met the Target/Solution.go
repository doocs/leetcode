func numberOfEmployeesWhoMetTarget(hours []int, target int) (ans int) {
	for _, x := range hours {
		if x >= target {
			ans++
		}
	}
	return
}