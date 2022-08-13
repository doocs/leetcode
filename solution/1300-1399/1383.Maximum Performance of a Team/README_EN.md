# [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team)

[中文文档](/solution/1300-1399/1383.Maximum%20Performance%20of%20a%20Team/README.md)

## Description

<p>You are given two integers <code>n</code> and <code>k</code> and two integer arrays <code>speed</code> and <code>efficiency</code> both of length <code>n</code>. There are <code>n</code> engineers numbered from <code>1</code> to <code>n</code>. <code>speed[i]</code> and <code>efficiency[i]</code> represent the speed and efficiency of the <code>i<sup>th</sup></code> engineer respectively.</p>

<p>Choose <strong>at most</strong> <code>k</code> different engineers out of the <code>n</code> engineers to form a team with the maximum <strong>performance</strong>.</p>

<p>The performance of a team is the sum of their engineers&#39; speeds multiplied by the minimum efficiency among their engineers.</p>

<p>Return <em>the maximum performance of this team</em>. Since the answer can be a huge number, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
<strong>Output:</strong> 60
<strong>Explanation:</strong> 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
<strong>Output:</strong> 68
<strong>Explanation:
</strong>This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
<strong>Output:</strong> 72
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>speed.length == n</code></li>
	<li><code>efficiency.length == n</code></li>
	<li><code>1 &lt;= speed[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= efficiency[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPerformance(
        self, n: int, speed: List[int], efficiency: List[int], k: int
    ) -> int:
        team = [(s, e) for s, e in zip(speed, efficiency)]
        team.sort(key=lambda x: -x[1])
        q = []
        t = 0
        ans = 0
        mod = int(1e9) + 7
        for s, e in team:
            t += s
            ans = max(ans, t * e)
            heappush(q, s)
            if len(q) >= k:
                t -= heappop(q)
        return ans % mod
```

### **Java**

```java
class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] team = new int[n][2];
        for (int i = 0; i < n; ++i) {
            team[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.sort(team, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        long t = 0;
        long ans = 0;
        int mod = (int) 1e9 + 7;
        for (int[] x : team) {
            int s = x[0], e = x[1];
            t += s;
            ans = Math.max(ans, t * e);
            q.offer(s);
            if (q.size() >= k) {
                t -= q.poll();
            }
        }
        return (int) (ans % mod);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPerformance(int n, vector<int>& speed, vector<int>& efficiency, int k) {
        vector<pair<int, int>> team;
        for (int i = 0; i < n; ++i) team.push_back({-efficiency[i], speed[i]});
        sort(team.begin(), team.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0;
        int mod = 1e9 + 7;
        long long t = 0;
        for (auto& x : team) {
            int s = x.second, e = -x.first;
            t += s;
            ans = max(ans, e * t);
            q.push(s);
            if (q.size() >= k) {
                t -= q.top();
                q.pop();
            }
        }
        return (int)(ans % mod);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
