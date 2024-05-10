func countTestedDevices(batteryPercentages []int) (ans int) {
	for _, x := range batteryPercentages {
		if x > ans {
			ans++
		}
	}
	return
}