func numberOfRounds(startTime string, finishTime string) int {
	start, finish := get(startTime), get(finishTime)
	if start > finish {
		finish += 24 * 60
	}
	start = (start + 14) / 15
	finish /= 15
	if start > finish {
		return 0
	}
	return finish - start

}

func get(s string) int {
	var a, b int
	fmt.Sscanf(s, "%d:%d", &a, &b)
	return a*60 + b
}