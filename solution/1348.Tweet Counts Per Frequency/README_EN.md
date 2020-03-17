# [1348. Tweet Counts Per Frequency](https://leetcode.com/problems/tweet-counts-per-frequency)

## Description
<p>Implement the class <code>TweetCounts</code> that supports two methods:</p>

<p>1.<code> recordTweet(string tweetName, int time)</code></p>

<ul>
	<li>Stores the <code>tweetName</code> at the recorded <code>time</code> (in <strong>seconds</strong>).</li>
</ul>

<p>2.<code> getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)</code></p>

<ul>
	<li>Returns the total number of occurrences for the given <code>tweetName</code> per <strong>minute</strong>, <strong>hour</strong>, or <strong>day</strong> (depending on <code>freq</code>) starting from the <code>startTime</code> (in <strong>seconds</strong>) and ending at the <code>endTime</code> (in <strong>seconds</strong>).</li>
	<li><code>freq</code> is always <strong>minute</strong><em>, </em><strong>hour</strong><em>&nbsp;or <strong>day</strong></em>, representing the time interval to get the total number of occurrences for the given&nbsp;<code>tweetName</code>.</li>
	<li>The first time interval always starts from the <code>startTime</code>, so the time intervals are <code>[startTime, startTime + delta*1&gt;, &nbsp;[startTime + delta*1, startTime + delta*2&gt;, [startTime + delta*2, startTime + delta*3&gt;, ... , [startTime + delta*i, <strong>min</strong>(startTime + delta*(i+1), endTime + 1)&gt;</code> for some non-negative number <code>i</code> and <code>delta</code> (which depends on <code>freq</code>).&nbsp;&nbsp;</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TweetCounts&quot;,&quot;recordTweet&quot;,&quot;recordTweet&quot;,&quot;recordTweet&quot;,&quot;getTweetCountsPerFrequency&quot;,&quot;getTweetCountsPerFrequency&quot;,&quot;recordTweet&quot;,&quot;getTweetCountsPerFrequency&quot;]
[[],[&quot;tweet3&quot;,0],[&quot;tweet3&quot;,60],[&quot;tweet3&quot;,10],[&quot;minute&quot;,&quot;tweet3&quot;,0,59],[&quot;minute&quot;,&quot;tweet3&quot;,0,60],[&quot;tweet3&quot;,120],[&quot;hour&quot;,&quot;tweet3&quot;,0,210]]

<strong>Output</strong>
[null,null,null,null,[2],[2,1],null,[4]]

<strong>Explanation</strong>
TweetCounts tweetCounts = new TweetCounts();
tweetCounts.recordTweet(&quot;tweet3&quot;, 0);
tweetCounts.recordTweet(&quot;tweet3&quot;, 60);
tweetCounts.recordTweet(&quot;tweet3&quot;, 10);                             // All tweets correspond to &quot;tweet3&quot; with recorded times at 0, 10 and 60.
tweetCounts.getTweetCountsPerFrequency(&quot;minute&quot;, &quot;tweet3&quot;, 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60&gt; - &gt; 2 tweets.
tweetCounts.getTweetCountsPerFrequency(&quot;minute&quot;, &quot;tweet3&quot;, 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60&gt; - &gt; 2 tweets, and 2) [60,61&gt; - &gt; 1 tweet.
tweetCounts.recordTweet(&quot;tweet3&quot;, 120);                            // All tweets correspond to &quot;tweet3&quot; with recorded times at 0, 10, 60 and 120.
tweetCounts.getTweetCountsPerFrequency(&quot;hour&quot;, &quot;tweet3&quot;, 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211&gt; - &gt; 4 tweets.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>There will be at most <code>10000</code>&nbsp;operations considering both <code>recordTweet</code> and <code>getTweetCountsPerFrequency</code>.</li>
	<li><code>0 &lt;= time, startTime, endTime &lt;=&nbsp;10^9</code></li>
	<li><code>0 &lt;= endTime - startTime &lt;= 10^4</code></li>
</ul>



## Solution
<!-- Type common method here -->


### Python3
<!-- Type special method here -->

```python

```

### Java
<!-- Type special method here -->

```java

```

### ...
```

```

