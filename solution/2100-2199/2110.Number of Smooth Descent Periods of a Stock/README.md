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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getDescentPeriods(self, prices: List[int]) -> int:
        i, n = 0, len(prices)
        ans = 0
        while i < n:
            j = i
            while j + 1 < n and prices[j] - prices[j + 1] == 1:
                j += 1
            t = j - i + 1
            ans += t * (t + 1) // 2
            i = j + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        for (int i = 0, n = prices.length; i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j);
            int t = j - i + 1;
            ans += (long) t * (t + 1) / 2;
            i = j + 1;
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
        for (int i = 0, n = prices.size(); i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j)
                ;
            int t = j - i + 1;
            ans += (long long)t * (t + 1) / 2;
            i = j + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func getDescentPeriods(prices []int) int64 {
	var ans int64
	for i, n := 0, len(prices); i < n; {
		j := i
		for ; j+1 < n && prices[j]-prices[j+1] == 1; j++ {
		}
		t := j - i + 1
		ans += int64(t * (t + 1) / 2)
		i = j + 1
	}
	return ans
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
