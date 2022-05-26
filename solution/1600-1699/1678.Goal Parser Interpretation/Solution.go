func interpret(command string) string {
	var res string
	i, n := 0, len(command)
	for i < n {
		c := command[i]
		if c == 'G' {
			res += "G"
			i += 1
		} else if c == '(' && command[i+1] != ')' {
			res += "al"
			i += 4
		} else {
			res += "o"
			i += 2
		}
	}
	return res
}