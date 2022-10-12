# [1883. Minimum Skips to Arrive at Meeting On Time](https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time)

[中文文档](/solution/1800-1899/1883.Minimum%20Skips%20to%20Arrive%20at%20Meeting%20On%20Time/README.md)

## Description

<p>You are given an integer <code>hoursBefore</code>, the number of hours you have to travel to your meeting. To arrive at your meeting, you have to travel through <code>n</code> roads. The road lengths are given as an integer array <code>dist</code> of length <code>n</code>, where <code>dist[i]</code> describes the length of the <code>i<sup>th</sup></code> road in <strong>kilometers</strong>. In addition, you are given an integer <code>speed</code>, which is the speed (in <strong>km/h</strong>) you will travel at.</p>

<p>After you travel road <code>i</code>, you must rest and wait for the <strong>next integer hour</strong> before you can begin traveling on the next road. Note that you do not have to rest after traveling the last road because you are already at the meeting.</p>

<ul>
	<li>For example, if traveling a road takes <code>1.4</code> hours, you must wait until the <code>2</code> hour mark before traveling the next road. If traveling a road takes exactly&nbsp;<code>2</code>&nbsp;hours, you do not need to wait.</li>
</ul>

<p>However, you are allowed to <strong>skip</strong> some rests to be able to arrive on time, meaning you do not need to wait for the next integer hour. Note that this means you may finish traveling future roads at different hour marks.</p>

<ul>
	<li>For example, suppose traveling the first road takes <code>1.4</code> hours and traveling the second road takes <code>0.6</code> hours. Skipping the rest after the first road will mean you finish traveling the second road right at the <code>2</code> hour mark, letting you start traveling the third road immediately.</li>
</ul>

<p>Return <em>the <strong>minimum number of skips required</strong> to arrive at the meeting on time, or</em> <code>-1</code><em> if it is<strong> impossible</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], speed = 4, hoursBefore = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Without skipping any rests, you will arrive in (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 hours.
You can skip the first rest to arrive in ((1/4 + <u>0</u>) + (3/4 + 0)) + (2/4) = 1.5 hours.
Note that the second rest is shortened because you finish traveling the second road at an integer hour due to skipping the first rest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dist = [7,3,5,5], speed = 2, hoursBefore = 10
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Without skipping any rests, you will arrive in (7/2 + 1/2) + (3/2 + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 hours.
You can skip the first and third rest to arrive in ((7/2 + <u>0</u>) + (3/2 + 0)) + ((5/2 + <u>0</u>) + (5/2)) = 10 hours.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> dist = [7,3,5,5], speed = 1, hoursBefore = 10
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to arrive at the meeting on time even if you skip all the rests.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= dist[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= speed &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= hoursBefore &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSkips(self, dist: List[int], speed: int, hoursBefore: int) -> int:
        n = len(dist)
        dp = [[inf] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 0
        for i in range(1, n + 1):
            for j in range(i + 1):
                if i != j:
                    dp[i][j] = min(
                        dp[i][j],
                        ((dp[i - 1][j] + dist[i - 1] - 1) // speed + 1) * speed,
                    )
                if j > 0:
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1])
        for i in range(n + 1):
            if dp[n][i] <= hoursBefore * speed:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (i != j) {
                    // 没有跳过
                    dp[i][j] = Math.min(
                        dp[i][j], ((dp[i - 1][j] + dist[i - 1] - 1) / speed + 1) * speed);
                }
                if (j > 0) {
                    // 跳过
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1]);
                }
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (dp[n][i] <= hoursBefore * speed) {
                return i;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
