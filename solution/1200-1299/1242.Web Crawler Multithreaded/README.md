# [1242. 多线程网页爬虫](https://leetcode.cn/problems/web-crawler-multithreaded)

[English Version](/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个初始地址&nbsp;<code>startUrl</code>&nbsp;和一个 HTML 解析器接口&nbsp;<code>HtmlParser</code>，请你实现一个&nbsp;<strong>多线程的网页爬虫</strong>，用于获取与&nbsp;<code>startUrl</code>&nbsp;有&nbsp;<strong>相同主机名&nbsp;</strong>的所有链接。&nbsp;</p>

<p>以&nbsp;<strong>任意&nbsp;</strong>顺序返回爬虫获取的路径。</p>

<p>爬虫应该遵循：</p>

<ul>
	<li>从&nbsp;<code>startUrl</code>&nbsp;开始</li>
	<li>调用&nbsp;<code>HtmlParser.getUrls(url)</code> 从指定网页路径获得的所有路径。</li>
	<li>不要抓取相同的链接两次。</li>
	<li>仅浏览与&nbsp;<code>startUrl</code>&nbsp;<strong>相同主机名&nbsp;</strong>的链接。</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/16e463265c7086cb?w=975&amp;h=266&amp;f=png&amp;s=24624" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/urlhostname.png" style="height:164px; width:600px" /></p>

<p>如上图所示，主机名是&nbsp;<code>example.org</code>&nbsp;。简单起见，你可以假设所有链接都采用&nbsp;<strong>http 协议</strong>，并且没有指定&nbsp;<strong>端口号</strong>。举个例子，链接&nbsp;<code>http://leetcode.com/problems</code> 和链接&nbsp;<code>http://leetcode.com/contest</code> 属于同一个&nbsp;<strong>主机名</strong>， 而&nbsp;<code>http://example.org/test</code>&nbsp;与&nbsp;<code>http://example.com/abc</code> 并不属于同一个&nbsp;<strong>主机名</strong>。</p>

<p><code>HtmlParser</code> 的接口定义如下：</p>

<pre>
interface HtmlParser {
  // Return a list of all urls from a webpage of given <em>url</em>.
  // This is a blocking call, that means it will do HTTP request and return when this request is finished.
  public List&lt;String&gt; getUrls(String url);
}</pre>

<p>注意一点，<code>getUrls(String url)</code>&nbsp;模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。&nbsp;<code>getUrls(String url)</code>&nbsp;保证会在&nbsp;<strong>15ms&nbsp;</strong>内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？</p>

<p>对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量&nbsp;<code>urls</code>，<code>edges</code>&nbsp;和&nbsp;<code>startUrl</code>。但要注意你只能在代码中访问&nbsp;<code>startUrl</code>，并不能直接访问&nbsp;<code>urls</code>&nbsp;和&nbsp;<code>edges</code>。</p>

<p>&nbsp;</p>

<p><strong>拓展问题：</strong></p>

<ol>
	<li>假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每个节点负载均衡。你将如何设计这个网页爬虫？</li>
	<li>如果有一个节点发生故障不工作该怎么办？</li>
	<li>如何确认爬虫任务已经完成？</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/sample_2_1497.png" style="height:287px; width:600px" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/16e46559da0c446a?w=875&amp;h=418&amp;f=png&amp;s=43518" /></p>

<pre>
<strong>输入：
</strong>urls = [
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.google.com&quot;,
&nbsp; &quot;http://news.yahoo.com/us&quot;
]
edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
startUrl = &quot;http://news.yahoo.com/news/topics/&quot;
<strong>输出：</strong>[
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.yahoo.com/us&quot;
]
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/16e4657b399a5fd2?w=654&amp;h=431&amp;f=png&amp;s=33838" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1242.Web%20Crawler%20Multithreaded/images/sample_3_1497.png" style="height:395px; width:530px" /></strong></p>

<pre>
<strong>输入：</strong>
urls = [
&nbsp; &quot;http://news.yahoo.com&quot;,
&nbsp; &quot;http://news.yahoo.com/news&quot;,
&nbsp; &quot;http://news.yahoo.com/news/topics/&quot;,
&nbsp; &quot;http://news.google.com&quot;
]
edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
startUrl = &quot;http://news.google.com&quot;
<strong>输出：</strong>[&quot;http://news.google.com&quot;]
<strong>解释：</strong>startUrl 链接与其他页面不共享一个主机名。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= urls.length &lt;= 1000</code></li>
	<li><code>1 &lt;= urls[i].length &lt;= 300</code></li>
	<li><code>startUrl</code>&nbsp;是&nbsp;<code>urls</code>&nbsp;中的一个。</li>
	<li>主机名的长度必须为 1 到 63 个字符（包括点 <code>.</code> 在内），只能包含从 &ldquo;a&rdquo; 到 &ldquo;z&rdquo; 的 ASCII 字母和 &ldquo;0&rdquo; 到 &ldquo;9&rdquo; 的数字，以及中划线 &ldquo;-&rdquo;。</li>
	<li>主机名开头和结尾不能是中划线 &ldquo;-&rdquo;。</li>
	<li>参考资料：<a href="https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames">https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames</a></li>
	<li>你可以假设路径都是不重复的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
