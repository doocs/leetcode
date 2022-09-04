# [2398. 预算内的最多机器人数目](https://leetcode.cn/problems/maximum-number-of-robots-within-budget)

[English Version](/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有&nbsp;<code>n</code>&nbsp;个机器人，给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>chargeTimes</code> 和&nbsp;<code>runningCosts</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;个机器人充电时间为&nbsp;<code>chargeTimes[i]</code>&nbsp;单位时间，花费&nbsp;<code>runningCosts[i]</code>&nbsp;单位时间运行。再给你一个整数&nbsp;<code>budget</code>&nbsp;。</p>

<p>运行&nbsp;<code>k</code>&nbsp;个机器人 <strong>总开销</strong>&nbsp;是&nbsp;<code>max(chargeTimes) + k * sum(runningCosts)</code>&nbsp;，其中&nbsp;<code>max(chargeTimes)</code>&nbsp;是这&nbsp;<code>k</code>&nbsp;个机器人中最大充电时间，<code>sum(runningCosts)</code>&nbsp;是这 <code>k</code>&nbsp;个机器人的运行时间之和。</p>

<p>请你返回在 <strong>不超过</strong>&nbsp;<code>budget</code>&nbsp;的前提下，你 <strong>最多</strong>&nbsp;可以 <strong>连续</strong>&nbsp;运行的机器人数目为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
<b>输出：</b>3
<b>解释：</b>
可以在 budget 以内运行所有单个机器人或者连续运行 2 个机器人。
选择前 3 个机器人，可以得到答案最大值 3 。总开销是 max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 ，小于 25 。
可以看出无法在 budget 以内连续运行超过 3 个机器人，所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
<b>输出：</b>0
<b>解释：</b>即使运行任何一个单个机器人，还是会超出 budget，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>chargeTimes.length == runningCosts.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= chargeTimes[i], runningCosts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>15</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 单调队列**

问题实际上是求滑动窗口内的最大值，可以用单调队列来求解。只需要二分枚举窗口 $k$ 的大小，找到一个最大的 $k$，使得满足题目要求。

实现过程中，实际上不需要进行二分枚举，只需要将固定窗口改为双指针非固定窗口即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是题目中机器人的数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
