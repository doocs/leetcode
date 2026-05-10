func scoreValidator(events []string) []int {
	score := 0
	counter := 0
	for _, event := range events {
		if num, err := strconv.Atoi(event); err == nil {
			score += num
		} else if event == "W" {
			counter++
			if counter == 10 {
				break
			}
		} else {
			score++
		}
	}
	return []int{score, counter}
}
