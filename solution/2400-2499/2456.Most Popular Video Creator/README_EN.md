# [2456. Most Popular Video Creator](https://leetcode.com/problems/most-popular-video-creator)

[中文文档](/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README.md)

## Description

<p>You are given two string arrays <code>creators</code> and <code>ids</code>, and an integer array <code>views</code>, all of length <code>n</code>. The <code>i<sup>th</sup></code> video on a platform was created by <code>creator[i]</code>, has an id of <code>ids[i]</code>, and has <code>views[i]</code> views.</p>

<p>The <strong>popularity</strong> of a creator is the <strong>sum</strong> of the number of views on <strong>all</strong> of the creator&#39;s videos. Find the creator with the <strong>highest</strong> popularity and the id of their <strong>most</strong> viewed video.</p>

<ul>
	<li>If multiple creators have the highest popularity, find all of them.</li>
	<li>If multiple videos have the highest view count for a creator, find the lexicographically <strong>smallest</strong> id.</li>
</ul>

<p>Return<em> a 2D array of strings </em><code>answer</code><em> where </em><code>answer[i] = [creator<sub>i</sub>, id<sub>i</sub>]</code><em> means that </em><code>creator<sub>i</sub></code> <em>has the <strong>highest</strong> popularity and </em><code>id<sub>i</sub></code><em> is the id of their most popular video.</em> The answer can be returned in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> creators = [&quot;alice&quot;,&quot;bob&quot;,&quot;alice&quot;,&quot;chris&quot;], ids = [&quot;one&quot;,&quot;two&quot;,&quot;three&quot;,&quot;four&quot;], views = [5,10,5,4]
<strong>Output:</strong> [[&quot;alice&quot;,&quot;one&quot;],[&quot;bob&quot;,&quot;two&quot;]]
<strong>Explanation:</strong>
The popularity of alice is 5 + 5 = 10.
The popularity of bob is 10.
The popularity of chris is 4.
alice and bob are the most popular creators.
For bob, the video with the highest view count is &quot;two&quot;.
For alice, the videos with the highest view count are &quot;one&quot; and &quot;three&quot;. Since &quot;one&quot; is lexicographically smaller than &quot;three&quot;, it is included in the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> creators = [&quot;alice&quot;,&quot;alice&quot;,&quot;alice&quot;], ids = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], views = [1,2,2]
<strong>Output:</strong> [[&quot;alice&quot;,&quot;b&quot;]]
<strong>Explanation:</strong>
The videos with id &quot;b&quot; and &quot;c&quot; have the highest view count.
Since &quot;b&quot; is lexicographically smaller than &quot;c&quot;, it is included in the answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == creators.length == ids.length == views.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= creators[i].length, ids[i].length &lt;= 5</code></li>
	<li><code>creators[i]</code> and <code>ids[i]</code> consist only of lowercase English letters.</li>
	<li><code>0 &lt;= views[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostPopularCreator(self, creators: List[str], ids: List[str], views: List[int]) -> List[List[str]]:
        ans = []
        cnt = Counter()
        d = defaultdict(int)
        x = defaultdict(str)
        for c, idx, view in zip(creators, ids, views):
            cnt[c] += view
            if c not in d or d[c] < view or (d[c] == view and x[c] > idx):
                d[c] = view
                x[c] = idx
        ans = []
        pre = -1
        for a, b in cnt.most_common():
            if b >= pre:
                pre = b
                ans.append([a, x[a]])
            else:
                break
        return ans
```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
