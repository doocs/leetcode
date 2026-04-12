func trafficSignal(timer int) string {
	switch {
	case timer == 0:
		return "Green"
	case timer == 30:
		return "Orange"
	case timer > 30 && timer <= 90:
		return "Red"
	default:
		return "Invalid"
	}
}
