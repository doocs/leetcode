func distanceTraveled(mainTank int, additionalTank int) (ans int) {
	cur := 0
	for mainTank > 0 {
		cur++
		ans += 10
		mainTank--
		if cur%5 == 0 && additionalTank > 0 {
			additionalTank--
			mainTank++
		}
	}
	return
}