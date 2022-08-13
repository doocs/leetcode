# [2106. 摘水果](https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps)

[English Version](/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组 <code>fruits</code> ，其中 <code>fruits[i] = [position<sub>i</sub>, amount<sub>i</sub>]</code> 表示共有 <code>amount<sub>i</sub></code> 个水果放置在 <code>position<sub>i</sub></code> 上。<code>fruits</code> 已经按 <code>position<sub>i</sub></code> <strong>升序排列</strong> ，每个 <code>position<sub>i</sub></code> <strong>互不相同</strong> 。</p>

<p>另给你两个整数 <code>startPos</code> 和 <code>k</code> 。最初，你位于 <code>startPos</code> 。从任何位置，你可以选择 <strong>向左或者向右</strong> 走。在 x 轴上每移动 <strong>一个单位</strong> ，就记作 <strong>一步</strong> 。你总共可以走 <strong>最多</strong> <code>k</code> 步。你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。</p>

<p>返回你可以摘到水果的 <strong>最大总数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/1.png" style="width: 472px; height: 115px;">
<pre><strong>输入：</strong>fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
<strong>输出：</strong>9
<strong>解释：</strong>
最佳路线为：
- 向右移动到位置 6 ，摘到 3 个水果
- 向右移动到位置 8 ，摘到 6 个水果
移动 3 步，共摘到 3 + 6 = 9 个水果
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/2.png" style="width: 512px; height: 129px;">
<pre><strong>输入：</strong>fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
<strong>输出：</strong>14
<strong>解释：</strong>
可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。
最佳路线为：
- 在初始位置 5 ，摘到 7 个水果
- 向左移动到位置 4 ，摘到 1 个水果
- 向右移动到位置 6 ，摘到 2 个水果
- 向右移动到位置 7 ，摘到 4 个水果
移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/3.png" style="width: 476px; height: 100px;">
<pre><strong>输入：</strong>fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
<strong>输出：</strong>0
<strong>解释：</strong>
最多可以移动 k = 2 步，无法到达任一有水果的地方
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>fruits[i].length == 2</code></li>
	<li><code>0 &lt;= startPos, position<sub>i</sub> &lt;= 2 * 10<sup>5</sup></code></li>
	<li>对于任意 <code>i &gt; 0</code> ，<code>position<sub>i-1</sub> &lt; position<sub>i</sub></code> 均成立（下标从 <strong>0</strong> 开始计数）</li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

在满足最多走 k 步的条件下，只需要确定能到达的左右端点即可。

首先从左边开始，找到能到达的最左端点，并把从此处开始到 startPos 的所有水果累加，此过程中依次入队。

然后开始确定右端点，在确定右端点的时候，检查左右端点之间的步数，若超过 k，则要减去左端点的值，同时出队。此过程中更新最大值 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        q = deque()
        i, n = 0, len(fruits)
        ans = 0
        while i < n and fruits[i][0] <= startPos:
            if startPos - fruits[i][0] <= k:
                ans += fruits[i][1]
                q.append(fruits[i])
            i += 1

        t = ans
        while i < n and fruits[i][0] - startPos <= k:
            while (
                q
                and q[0][0] < startPos
                and fruits[i][0]
                - q[0][0]
                + min(startPos - q[0][0], fruits[i][0] - startPos)
                > k
            ):
                t -= q[0][1]
                q.popleft()
            t += fruits[i][1]
            ans = max(ans, t)
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        Deque<int[]> q = new ArrayDeque<>();
        int i = 0, n = fruits.length;
        int ans = 0;
        while (i < n && fruits[i][0] <= startPos) {
            if (startPos - fruits[i][0] <= k) {
                ans += fruits[i][1];
                q.offerLast(fruits[i]);
            }
            ++i;
        }
        int t = ans;
        while (i < n && fruits[i][0] - startPos <= k) {
            while (!q.isEmpty() && q.peekFirst()[0] < startPos && fruits[i][0] - q.peekFirst()[0] + Math.min(startPos - q.peekFirst()[0], fruits[i][0] - startPos) > k) {
                t -= q.pollFirst()[1];
            }
            t += fruits[i][1];
            ans = Math.max(ans, t);
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        queue<vector<int>> q;
        int i = 0, n = fruits.size();
        int ans = 0;
        while (i < n && fruits[i][0] <= startPos) {
            if (startPos - fruits[i][0] <= k) {
                ans += fruits[i][1];
                q.push(fruits[i]);
            }
            ++i;
        }
        int t = ans;
        while (i < n && fruits[i][0] - startPos <= k) {
            while (!q.empty() && q.front()[0] < startPos && fruits[i][0] - q.front()[0] + min(startPos - q.front()[0], fruits[i][0] - startPos) > k) {
                t -= q.front()[1];
                q.pop();
            }
            t += fruits[i][1];
            ans = max(ans, t);
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxTotalFruits(fruits [][]int, startPos int, k int) int {
	var q [][]int
	i, n := 0, len(fruits)
	ans := 0
	for i < n && fruits[i][0] <= startPos {
		if startPos-fruits[i][0] <= k {
			ans += fruits[i][1]
			q = append(q, fruits[i])
		}
		i++
	}
	t := ans
	for i < n && fruits[i][0]-startPos <= k {
		for len(q) > 0 && q[0][0] < startPos && fruits[i][0]-q[0][0]+min(startPos-q[0][0], fruits[i][0]-startPos) > k {
			t -= q[0][1]
			q = q[1:]
		}
		t += fruits[i][1]
		ans = max(ans, t)
		i++
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
