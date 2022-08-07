# [681. Next Closest Time](https://leetcode.com/problems/next-closest-time)

[中文文档](/solution/0600-0699/0681.Next%20Closest%20Time/README.md)

## Description

<p>Given a <code>time</code> represented in the format <code>&quot;HH:MM&quot;</code>, form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.</p>

<p>You may assume the given input string is always valid. For example, <code>&quot;01:34&quot;</code>, <code>&quot;12:09&quot;</code> are all valid. <code>&quot;1:34&quot;</code>, <code>&quot;12:9&quot;</code> are all invalid.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;19:34&quot;
<strong>Output:</strong> &quot;19:39&quot;
<strong>Explanation:</strong> The next closest time choosing from digits <strong>1</strong>, <strong>9</strong>, <strong>3</strong>, <strong>4</strong>, is <strong>19:39</strong>, which occurs 5 minutes later.
It is not <strong>19:33</strong>, because this occurs 23 hours and 59 minutes later.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;23:59&quot;
<strong>Output:</strong> &quot;22:22&quot;
<strong>Explanation:</strong> The next closest time choosing from digits <strong>2</strong>, <strong>3</strong>, <strong>5</strong>, <strong>9</strong>, is <strong>22:22</strong>.
It may be assumed that the returned time is next day&#39;s time since it is smaller than the input time numerically.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>time.length == 5</code></li>
	<li><code>time</code> is a valid time in the form <code>&quot;HH:MM&quot;</code>.</li>
	<li><code>0 &lt;= HH &lt; 24</code></li>
	<li><code>0 &lt;= MM &lt; 60</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nextClosestTime(self, time: str) -> str:
        def check(t):
            h, m = int(t[:2]), int(t[2:])
            return 0 <= h < 24 and 0 <= m < 60

        def dfs(curr):
            if len(curr) == 4:
                if not check(curr):
                    return
                nonlocal ans, d
                p = int(curr[:2]) * 60 + int(curr[2:])
                if t < p < t + d:
                    d = p - t
                    ans = curr[:2] + ':' + curr[2:]
                return
            for c in s:
                dfs(curr + c)

        s = {c for c in time if c != ':'}
        t = int(time[:2]) * 60 + int(time[3:])
        d = inf
        ans = None
        dfs('')
        if ans is None:
            mi = min(int(c) for c in s)
            ans = f'{mi}{mi}:{mi}{mi}'
        return ans
```

### **Java**

```java
class Solution {
    private int t;
    private int d;
    private String ans;
    private Set<Character> s;

    public String nextClosestTime(String time) {
        t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        d = Integer.MAX_VALUE;
        s = new HashSet<>();
        char mi = 'z';
        for (char c : time.toCharArray()) {
            if (c != ':') {
                s.add(c);
                if (c < mi) {
                    mi = c;
                }
            }
        }
        ans = null;
        dfs("");
        if (ans == null) {
            ans = "" + mi + mi + ":" + mi + mi;
        }
        return ans;
    }

    private void dfs(String curr) {
        if (curr.length() == 4) {
            if (!check(curr)) {
                return;
            }
            int p = Integer.parseInt(curr.substring(0, 2)) * 60 + Integer.parseInt(curr.substring(2));
            if (p > t && p - t < d) {
                d = p - t;
                ans = curr.substring(0, 2) + ":" + curr.substring(2);
            }
            return;
        }
        for (char c : s) {
            dfs(curr + c);
        }
    }

    private boolean check(String t) {
        int h = Integer.parseInt(t.substring(0, 2));
        int m = Integer.parseInt(t.substring(2));
        return 0 <= h && h < 24 && 0 <= m && m < 60;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
