# [2398. Maximum Number of Robots Within Budget](https://leetcode.com/problems/maximum-number-of-robots-within-budget)

[中文文档](/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README.md)

## Description

<p>You have <code>n</code> robots. You are given two <strong>0-indexed</strong> integer arrays, <code>chargeTimes</code> and <code>runningCosts</code>, both of length <code>n</code>. The <code>i<sup>th</sup></code> robot costs <code>chargeTimes[i]</code> units to charge and costs <code>runningCosts[i]</code> units to run. You are also given an integer <code>budget</code>.</p>

<p>The <strong>total cost</strong> of running <code>k</code> chosen robots is equal to <code>max(chargeTimes) + k * sum(runningCosts)</code>, where <code>max(chargeTimes)</code> is the largest charge cost among the <code>k</code> robots and <code>sum(runningCosts)</code> is the sum of running costs among the <code>k</code> robots.</p>

<p>Return<em> the <strong>maximum</strong> number of <strong>consecutive</strong> robots you can run such that the total cost <strong>does not</strong> exceed </em><code>budget</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
It is possible to run all individual and consecutive pairs of robots within budget.
To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
<strong>Output:</strong> 0
<strong>Explanation:</strong> No robot can be run that does not exceed the budget, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>chargeTimes.length == runningCosts.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= chargeTimes[i], runningCosts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumRobots(
        self, chargeTimes: List[int], runningCosts: List[int], budget: int
    ) -> int:
        q = deque()
        ans = j = s = 0
        for i, (a, b) in enumerate(zip(chargeTimes, runningCosts)):
            while q and chargeTimes[q[-1]] <= a:
                q.pop()
            q.append(i)
            s += b
            while q and chargeTimes[q[0]] + (i - j + 1) * s > budget:
                if q[0] == j:
                    q.popleft()
                s -= runningCosts[j]
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = chargeTimes.length;
        long s = 0;
        int j = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = chargeTimes[i], b = runningCosts[i];
            while (!q.isEmpty() && chargeTimes[q.getLast()] <= a) {
                q.pollLast();
            }
            q.offer(i);
            s += b;
            while (!q.isEmpty() && chargeTimes[q.getFirst()] + (i - j + 1) * s > budget) {
                if (q.getFirst() == j) {
                    q.pollFirst();
                }
                s -= runningCosts[j++];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumRobots(vector<int>& chargeTimes, vector<int>& runningCosts, long long budget) {
        deque<int> q;
        long long s = 0;
        int ans = 0, j = 0, n = chargeTimes.size();
        for (int i = 0; i < n; ++i) {
            int a = chargeTimes[i], b = runningCosts[i];
            while (!q.empty() && chargeTimes[q.back()] <= a) q.pop_back();
            q.push_back(i);
            s += b;
            while (!q.empty() && chargeTimes[q.front()] + (i - j + 1) * s > budget) {
                if (q.front() == j) {
                    q.pop_front();
                }
                s -= runningCosts[j++];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) int {
	s := int64(0)
	ans, j := 0, 0
	q := []int{}
	for i, a := range chargeTimes {
		for len(q) > 0 && chargeTimes[q[len(q)-1]] <= a {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		s += int64(runningCosts[i])
		for len(q) > 0 && int64(chargeTimes[q[0]])+int64(i-j+1)*s > budget {
			if q[0] == j {
				q = q[1:]
			}
			s -= int64(runningCosts[j])
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
