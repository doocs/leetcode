func countTestedDevices(batteryPercentages []int) (ans int) {
	for _, x := range batteryPercentages {
		x -= ans
		if x > 0 {
			ans++
		}
	}
	return
}