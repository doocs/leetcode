type Codec struct {
	m   map[int]string
	idx int
}

func Constructor() Codec {
	m := map[int]string{}
	return Codec{m, 0}
}

// Encodes a URL to a shortened URL.
func (this *Codec) encode(longUrl string) string {
	this.idx++
	this.m[this.idx] = longUrl
	return "https://tinyurl.com/" + strconv.Itoa(this.idx)
}

// Decodes a shortened URL to its original URL.
func (this *Codec) decode(shortUrl string) string {
	i := strings.LastIndexByte(shortUrl, '/')
	v, _ := strconv.Atoi(shortUrl[i+1:])
	return this.m[v]
}

/**
 * Your Codec object will be instantiated and called as such:
 * obj := Constructor();
 * url := obj.encode(longUrl);
 * ans := obj.decode(url);
 */