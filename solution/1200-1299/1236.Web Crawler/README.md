---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1236.Web%20Crawler/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 字符串
    - 交互
---

<!-- problem:start -->

# [1236. 网络爬虫 🔒](https://leetcode.cn/problems/web-crawler)

[English Version](/solution/1200-1299/1236.Web%20Crawler/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个链接&nbsp;<code>startUrl</code> 和一个接口&nbsp;<code>HtmlParser</code>&nbsp;，请你实现一个网络爬虫，以实现爬取同&nbsp;<code>startUrl</code>&nbsp;拥有相同&nbsp;<strong>域名标签&nbsp;</strong>的全部链接。该爬虫得到的全部链接可以&nbsp;<strong>任何顺序&nbsp;</strong>返回结果。</p>

<p>你的网络爬虫应当按照如下模式工作：</p>

<ul>
	<li>自链接&nbsp;<code>startUrl</code>&nbsp;开始爬取</li>
	<li>调用&nbsp;<code>HtmlParser.getUrls(url)</code>&nbsp;来获得链接<code>url</code>页面中的全部链接</li>
	<li>同一个链接最多只爬取一次</li>
	<li>只输出&nbsp;<strong>域名&nbsp;</strong>与<strong>&nbsp;</strong><code>startUrl</code>&nbsp;<strong>相同&nbsp;</strong>的链接集合</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/urlhostname.png" style="height: 164px; width: 600px;" /></p>

<p>如上所示的一个链接，其域名为&nbsp;<code>example.org</code>。简单起见，你可以假设所有的链接都采用&nbsp;<strong>http协议&nbsp;</strong>并没有指定&nbsp;<strong>端口</strong>。例如，链接&nbsp;<code>http://leetcode.com/problems</code>&nbsp;和&nbsp;<code>http://leetcode.com/contest</code>&nbsp;是同一个域名下的，而链接<code>http://example.org/test</code>&nbsp;和&nbsp;<code>http://example.com/abc</code> 是不在同一域名下的。</p>

<p><code>HtmlParser</code> 接口定义如下：&nbsp;</p>

<pre>
interface HtmlParser {
  // 返回给定 url 对应的页面中的全部 url 。
  public List&lt;String&gt; getUrls(String url);
}</pre>

<p>下面是两个实例，用以解释该问题的设计功能，对于自定义测试，你可以使用三个变量&nbsp;&nbsp;<code>urls</code>,&nbsp;<code>edges</code>&nbsp;和&nbsp;<code>startUrl</code>。注意在代码实现中，你只可以访问&nbsp;<code>startUrl</code>&nbsp;，而&nbsp;<code>urls</code>&nbsp;和&nbsp;<code>edges</code>&nbsp;不可以在你的代码中被直接访问。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/sample_2_1497.png" style="height: 300px; width: 610px;" /></p>

<pre>
<strong>输入：
</strong>urls = [
&nbsp; "http://news.yahoo.com",
&nbsp; "http://news.yahoo.com/news",
&nbsp; "http://news.yahoo.com/news/topics/",
&nbsp; "http://news.google.com",
&nbsp; "http://news.yahoo.com/us"
]
edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
startUrl = "http://news.yahoo.com/news/topics/"
<strong>输出：</strong>[
&nbsp; "http://news.yahoo.com",
&nbsp; "http://news.yahoo.com/news",
&nbsp; "http://news.yahoo.com/news/topics/",
&nbsp; "http://news.yahoo.com/us"
]
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1236.Web%20Crawler/images/sample_3_1497.png" style="height: 270px; width: 540px;" /></strong></p>

<pre>
<strong>输入：</strong>
urls = [
&nbsp; "http://news.yahoo.com",
&nbsp; "http://news.yahoo.com/news",
&nbsp; "http://news.yahoo.com/news/topics/",
&nbsp; "http://news.google.com"
]
edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
startUrl = "http://news.google.com"
<strong>输出：</strong>["http://news.google.com"]
<strong>解释：</strong>startUrl 链接到所有其他不共享相同主机名的页面。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= urls.length &lt;= 1000</code></li>
	<li><code>1 &lt;= urls[i].length &lt;= 300</code></li>
	<li><code>startUrl</code>&nbsp;为&nbsp;<code>urls</code>&nbsp;中的一个。</li>
	<li>域名标签的长为1到63个字符（包括点），只能包含从‘a’到‘z’的ASCII字母、‘0’到‘9’的数字以及连字符即减号（‘-’）。</li>
	<li>域名标签不会以连字符即减号（‘-’）开头或结尾。</li>
	<li>关于域名有效性的约束可参考:&nbsp;&nbsp;<a href="https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames">https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames</a></li>
	<li>你可以假定url库中不包含重复项。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
