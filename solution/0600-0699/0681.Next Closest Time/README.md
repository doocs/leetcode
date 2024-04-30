# [681. 最近时刻 🔒](https://leetcode.cn/problems/next-closest-time)

[English Version](/solution/0600-0699/0681.Next%20Closest%20Time/README_EN.md)

<!-- tags:哈希表,字符串,回溯,枚举 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个形如<meta charset="UTF-8" />&nbsp;<code>"HH:MM"</code> 表示的时刻<meta charset="UTF-8" />&nbsp;<code>time</code>&nbsp;，利用当前出现过的数字构造下一个距离当前时间最近的时刻。每个出现数字都可以被无限次使用。</p>

<p>你可以认为给定的字符串一定是合法的。例如，<meta charset="UTF-8" />&nbsp;<code>"01:34"</code> 和 <meta charset="UTF-8" />&nbsp;<code>"12:09"</code> 是合法的，<code>“1:34”</code> 和 <code>“12:9”</code> 是不合法的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> "19:34"
<strong>输出:</strong> "19:39"
<strong>解释:</strong> 利用数字 <strong>1, 9, 3, 4</strong> 构造出来的最近时刻是 <strong>19:39</strong>，是 5 分钟之后。
结果不是 <strong>19:33</strong> 因为这个时刻是 23 小时 59 分钟之后。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> "23:59"
<strong>输出:</strong> "22:22"
<strong>解释:</strong> 利用数字 <strong>2, 3, 5, 9</strong> 构造出来的最近时刻是 <strong>22:22</strong>。 
答案一定是第二天的某一时刻，所以选择可构造的最小时刻。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>time.length == 5</code></li>
	<li><code>time</code>&nbsp;为有效时间，格式为&nbsp;<code>"HH:MM"</code>.</li>
	<li><code>0 &lt;= HH &lt; 24</code></li>
	<li><code>0 &lt;= MM &lt; 60</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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
            int p
                = Integer.parseInt(curr.substring(0, 2)) * 60 + Integer.parseInt(curr.substring(2));
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

<!-- tabs:end -->

<!-- end -->
