type Codec struct {
}

// Encodes a list of strings to a single string.
func (codec *Codec) Encode(strs []string) string {
	ans := &bytes.Buffer{}
	for _, s := range strs {
		t := fmt.Sprintf("%04d", len(s))
		ans.WriteString(t)
		ans.WriteString(s)
	}
	return ans.String()
}

// Decodes a single string to a list of strings.
func (codec *Codec) Decode(strs string) []string {
	ans := []string{}
	i, n := 0, len(strs)
	for i < n {
		t := strs[i : i+4]
		i += 4
		size, _ := strconv.Atoi(t)
		ans = append(ans, strs[i:i+size])
		i += size
	}
	return ans
}

// Your Codec object will be instantiated and called as such:
// var codec Codec
// codec.Decode(codec.Encode(strs));