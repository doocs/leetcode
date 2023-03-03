# [2110. 股票平滑下跌阶段的数目](https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock)

[English Version](/solution/2100-2199/2110.Number%20of%20Smooth%20Descent%20Periods%20of%20a%20Stock/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>prices</code>&nbsp;，表示一支股票的历史每日股价，其中&nbsp;<code>prices[i]</code>&nbsp;是这支股票第&nbsp;<code>i</code>&nbsp;天的价格。</p>

<p>一个 <strong>平滑下降的阶段</strong>&nbsp;定义为：对于&nbsp;<strong>连续一天或者多天</strong>&nbsp;，每日股价都比 <strong>前一日股价恰好少 </strong><code>1</code>&nbsp;，这个阶段第一天的股价没有限制。</p>

<p>请你返回 <strong>平滑下降阶段</strong>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>prices = [3,2,1,4]
<b>输出：</b>7
<b>解释：</b>总共有 7 个平滑下降阶段：
[3], [2], [1], [4], [3,2], [2,1] 和 [3,2,1]
注意，仅一天按照定义也是平滑下降阶段。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>prices = [8,6,7,7]
<b>输出：</b>4
<b>解释：</b>总共有 4 个连续平滑下降阶段：[8], [6], [7] 和 [7]
由于 8 - 6 ≠ 1 ，所以 [8,6] 不是平滑下降阶段。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>prices = [1]
<b>输出：</b>1
<b>解释：</b>总共有 1 个平滑下降阶段：[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们定义一个答案变量 `ans`，初始值为 $0$。

接下来，我们使用双指针 $i$ 和 $j$，分别指向当前平滑下降阶段的第一天和最后一天的下一天。初始时 $i = 0$, $j = 0$。

从左到右遍历数组 `prices`，对于每个位置 $i$，我们将 $j$ 向右移动，直到 $j$ 到达数组末尾或者 $prices[j - 1] - prices[j] \neq 1$ 为止。此时，$cnt = j - i$ 即为当前平滑下降阶段的长度，我们累加 $\frac{(1 + cnt) \times cnt}{2}$ 到答案变量 `ans` 中。接下来将 $i$ 更新为 $j$，继续遍历。

遍历结束后，返回答案变量 `ans` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `prices` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getDescentPeriods(self, prices: List[int]) -> int:
        ans = 0
        i, n = 0, len(prices)
        while i < n:
            j = i + 1
            while j < n and prices[j - 1] - prices[j] == 1:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            i = j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int n = prices.length;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1L + cnt) * cnt / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        long long ans = 0;
        int n = prices.size();
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1LL + cnt) * cnt / 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func getDescentPeriods(prices []int) (ans int64) {
	n := len(prices)
	for i, j := 0, 0; i < n; i = j {
		j = i + 1
		for j < n && prices[j-1]-prices[j] == 1 {
			j++
		}
		cnt := j - i
		ans += int64((1 + cnt) * cnt / 2)
	}
	return
}
```

### **TypeScript**

```ts
function getDescentPeriods(prices: number[]): number {
    let ans = 0;
    const n = prices.length;
    for (let i = 0, j = 0; i < n; i = j) {
        j = i + 1;
        while (j < n && prices[j - 1] - prices[j] === 1) {
            ++j;
        }
        const cnt = j - i;
        ans += Math.floor(((1 + cnt) * cnt) / 2);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
