# [1236. Web Crawler](https://leetcode.com/problems/web-crawler)

[中文文档](/solution/1200-1299/1236.Web%20Crawler/README.md)

## Description

<p>Given a url <code>startUrl</code> and an interface <code>HtmlParser</code>, implement a web&nbsp;crawler to crawl all links that are under the&nbsp;<strong>same hostname</strong> as&nbsp;<code>startUrl</code>.&nbsp;</p>

<p>Return&nbsp;all urls obtained by your web crawler in <strong>any</strong> order.</p>

<p>Your crawler should:</p>

<ul>
	<li>Start from the page: <code>startUrl</code></li>
	<li>Call <code>HtmlParser.getUrls(url)</code> to get all urls from a webpage of given url.</li>
	<li>Do not crawl the same link twice.</li>
	<li>Explore only the links that are under the <strong>same hostname</strong> as <code>startUrl</code>.</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/urlhostname.png" style="width: 600px; height: 164px;" /></p>

<p>As shown in the example url above, the hostname is <code>example.org</code>. For simplicity sake, you may assume all&nbsp;urls use <strong>http protocol</strong> without any&nbsp;<strong>port</strong> specified. For example, the urls&nbsp;<code>http://leetcode.com/problems</code> and&nbsp;<code>http://leetcode.com/contest</code> are under the same hostname, while urls <code>http://example.org/test</code> and <code>http://example.com/abc</code> are not under the same hostname.</p>

<p>The <code>HtmlParser</code> interface is defined as such:&nbsp;</p>

<pre>
interface HtmlParser {
  // Return a list of all urls from a webpage of given <em>url</em>.
  public List&lt;String&gt; getUrls(String url);
}</pre>

<p>Below&nbsp;are two examples explaining the functionality of the problem, for custom testing purposes you&#39;ll have three&nbsp;variables&nbsp;<code data-stringify-type="code">urls</code>,&nbsp;<code data-stringify-type="code">edges</code>&nbsp;and&nbsp;<code data-stringify-type="code">startUrl</code>. Notice that you will only have access to&nbsp;<code data-stringify-type="code">startUrl</code>&nbsp;in your code, while&nbsp;<code data-stringify-type="code">urls</code>&nbsp;and&nbsp;<code data-stringify-type="code">edges</code>&nbsp;are not directly accessible to you in code.</p>

<p>Note: Consider the same URL with the trailing slash &quot;/&quot; as a different URL. For example, &quot;http://news.yahoo.com&quot;, and &quot;http://news.yahoo.com/&quot; are different urls.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/sample_2_1497.png" style="width: 610px; height: 300px;" /></p>

<pre>
<strong>Input:
</strong>urls = [
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.google.com&quot;,
&nbsp; &quot;http://news.yahoo.com/us&quot;
]
edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
startUrl = &quot;http://news.yahoo.com/news/topics/&quot;
<strong>Output:</strong> [
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.yahoo.com/us&quot;
]
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/sample_3_1497.png" style="width: 540px; height: 270px;" /></strong></p>

<pre>
<strong>Input:</strong> 
urls = [
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.google.com&quot;
]
edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
startUrl = &quot;http://news.google.com&quot;
<strong>Output:</strong> [&quot;http://news.google.com&quot;]
<strong>Explanation: </strong>The startUrl links to all other pages that do not share the same hostname.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= urls.length &lt;= 1000</code></li>
	<li><code>1 &lt;= urls[i].length &lt;= 300</code></li>
	<li><code>startUrl</code>&nbsp;is one of the <code>urls</code>.</li>
	<li>Hostname label must be from 1 to 63 characters long, including the dots, may contain only the ASCII letters from &#39;a&#39; to&nbsp;&#39;z&#39;, digits&nbsp; from &#39;0&#39; to &#39;9&#39; and the&nbsp;hyphen-minus&nbsp;character (&#39;-&#39;).</li>
	<li>The hostname may not start or end with&nbsp;the hyphen-minus character (&#39;-&#39;).&nbsp;</li>
	<li>See:&nbsp;&nbsp;<a href="https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames">https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames</a></li>
	<li>You may assume there&#39;re&nbsp;no duplicates in url library.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
# """
# This is HtmlParser's API interface.
# You should not implement it, or speculate about its implementation
# """
# class HtmlParser(object):
#    def getUrls(self, url):
#        """
#        :type url: str
#        :rtype List[str]
#        """


class Solution:
    def crawl(self, startUrl: str, htmlParser: 'HtmlParser') -> List[str]:
        def host(url):
            url = url[7:]
            return url.split('/')[0]

        def dfs(url):
            if url in ans:
                return
            ans.add(url)
            for next in htmlParser.getUrls(url):
                if host(url) == host(next):
                    dfs(next)

        ans = set()
        dfs(startUrl)
        return list(ans)
```

### **Java**

```java
/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

class Solution {
    private Set<String> ans;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        ans = new HashSet<>();
        dfs(startUrl, htmlParser);
        return new ArrayList<>(ans);
    }

    private void dfs(String url, HtmlParser htmlParser) {
        if (ans.contains(url)) {
            return;
        }
        ans.add(url);
        for (String next : htmlParser.getUrls(url)) {
            if (host(next).equals(host(url))) {
                dfs(next, htmlParser);
            }
        }
    }

    private String host(String url) {
        url = url.substring(7);
        return url.split("/")[0];
    }
}
```

### **C++**

```cpp
/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * class HtmlParser {
 *   public:
 *     vector<string> getUrls(string url);
 * };
 */

class Solution {
public:
    vector<string> ans;
    unordered_set<string> vis;

    vector<string> crawl(string startUrl, HtmlParser htmlParser) {
        dfs(startUrl, htmlParser);
        return ans;
    }

    void dfs(string& url, HtmlParser& htmlParser) {
        if (vis.count(url)) return;
        vis.insert(url);
        ans.push_back(url);
        for (string next : htmlParser.getUrls(url))
            if (host(url) == host(next))
                dfs(next, htmlParser);
    }

    string host(string url) {
        int i = 7;
        string res;
        for (; i < url.size(); ++i) {
            if (url[i] == '/') break;
            res += url[i];
        }
        return res;
    }
};
```

### **Go**

```go
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
```

### **...**

```

```

<!-- tabs:end -->
