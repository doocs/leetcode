/**
 * // This is HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * type HtmlParser struct {
 *     func GetUrls(url string) []string {}
 * }
 */

func crawl(startUrl string, htmlParser HtmlParser) []string {
	var ans []string
	vis := make(map[string]bool)
	var dfs func(url string)
	host := func(url string) string {
		return strings.Split(url[7:], "/")[0]
	}
	dfs = func(url string) {
		if vis[url] {
			return
		}
		vis[url] = true
		ans = append(ans, url)
		for _, next := range htmlParser.GetUrls(url) {
			if host(next) == host(url) {
				dfs(next)
			}
		}
	}
	dfs(startUrl)
	return ans
}