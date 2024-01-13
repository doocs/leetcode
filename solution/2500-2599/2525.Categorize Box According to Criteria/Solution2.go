func categorizeBox(length int, width int, height int, mass int) string {
	v := length * width * height
	bulky := length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9
	heavy := mass >= 100
	if bulky && heavy {
		return "Both"
	}
	if bulky {
		return "Bulky"
	}
	if heavy {
		return "Heavy"
	}
	return "Neither"
}