# [1348. 推文计数](https://leetcode.cn/problems/tweet-counts-per-frequency)

[English Version](/solution/1300-1399/1348.Tweet%20Counts%20Per%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一家社交媒体公司正试图通过分析特定时间段内出现的推文数量来监控其网站上的活动。这些时间段可以根据特定的频率（&nbsp;<strong>每分钟&nbsp;</strong>、<strong>每小时&nbsp;</strong>或 <strong>每一天</strong> ）划分为更小的 <strong>时间段</strong> 。</p>

<p>&nbsp;</p>

<p>例如，周期 <code>[10,10000]</code>&nbsp;（以 <strong>秒</strong> 为单位）将被划分为以下频率的 <strong>时间块</strong> :</p>

<ul>
	<li>每 <strong>分钟</strong> (60秒 块)：<meta charset="UTF-8" />&nbsp;<code>[10,69]</code>,&nbsp;<code>[70,129]</code>,&nbsp;<code>[130,189]</code>,&nbsp;<code>...</code>,&nbsp;<code>[9970,10000]</code></li>
	<li>每 <strong>小时</strong> (3600秒 块)：<meta charset="UTF-8" /><code>[10,3609]</code>,&nbsp;<code>[3610,7209]</code>,&nbsp;<code>[7210,10000]</code></li>
	<li>每 <strong>天</strong> (86400秒 块)：<meta charset="UTF-8" />&nbsp;<code>[10,10000]</code></li>
</ul>

<p>注意，最后一个块可能比指定频率的块大小更短，并且总是以时间段的结束时间结束(在上面的示例中为 <code>10000</code> )。</p>

<p>设计和实现一个API来帮助公司进行分析。</p>

<p>实现 <code>TweetCounts</code> 类:</p>

<ul>
	<li><code>TweetCounts()</code> 初始化 <code>TweetCounts</code> 对象。</li>
	<li>存储记录时间的 <code>tweetName</code> (以秒为单位)。</li>
	<li><code>List&lt;integer&gt; getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime)</code>&nbsp;返回一个整数列表，表示给定时间 <code>[startTime, endTime]</code>&nbsp;（单位秒）和频率频率中，每个 <strong>时间块</strong> 中带有 <code>tweetName</code> 的 <code>tweet</code> 的数量。
	<ul>
		<li><code>freq</code> 是 <code>“minute”</code> 、 <code>“hour”</code> 或 <code>“day”</code> 中的一个，分别表示 <strong>每分钟</strong> 、 <strong>每小时</strong> 或 <strong>每一天</strong> 的频率。</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
[[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]

<strong>输出：</strong>
[null,null,null,null,[2],[2,1],null,[4]]

<strong>解释：</strong>
TweetCounts tweetCounts = new TweetCounts();
tweetCounts.recordTweet("tweet3", 0);
tweetCounts.recordTweet("tweet3", 60);
tweetCounts.recordTweet("tweet3", 10);                             //&nbsp;"tweet3"&nbsp;发布推文的时间分别是&nbsp;0,&nbsp;10&nbsp;和&nbsp;60 。
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); //&nbsp;返回&nbsp;[2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60&gt;&nbsp;-&nbsp;&gt;&nbsp;2&nbsp;条推文。
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); //&nbsp;返回&nbsp;[2,1]。统计频率是每分钟（60 秒），因此有两个有效时间间隔&nbsp;<strong>1)</strong>&nbsp;[0,60&gt;&nbsp;-&nbsp;&gt;&nbsp;2&nbsp;条推文，和&nbsp;<strong>2)</strong>&nbsp;[60,61&gt;&nbsp;-&nbsp;&gt;&nbsp;1&nbsp;条推文。 
tweetCounts.recordTweet("tweet3", 120);                            // "tweet3"&nbsp;发布推文的时间分别是 0, 10, 60 和 120 。
tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  //&nbsp;返回&nbsp;[4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211&gt;&nbsp;-&nbsp;&gt;&nbsp;4&nbsp;条推文。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= time, startTime, endTime &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= endTime - startTime &lt;= 10<sup>4</sup></code></li>
	<li><code>recordTweet</code>&nbsp;和&nbsp;<code>getTweetCountsPerFrequency</code>，最多有<meta charset="UTF-8" />&nbsp;<code>10<sup>4</sup></code>&nbsp;次操作。</li>
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
