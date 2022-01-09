func slowestKey(releaseTimes []int, keysPressed string) byte {
	ans := keysPressed[0]
	mx := releaseTimes[0]
	for i := 1; i < len(releaseTimes); i++ {
		d := releaseTimes[i] - releaseTimes[i-1]
		if d > mx || (d == mx && keysPressed[i] > ans) {
			mx = d
			ans = keysPressed[i]
		}
	}
	return ans
}