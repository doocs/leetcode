# [2178. 拆分成最多数目的正偶数之和](https://leetcode.cn/problems/maximum-split-of-positive-even-integers)

[English Version](/solution/2100-2199/2178.Maximum%20Split%20of%20Positive%20Even%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>finalSum</code>&nbsp;。请你将它拆分成若干个&nbsp;<strong>互不相同</strong> 的正偶数之和，且拆分出来的正偶数数目&nbsp;<strong>最多</strong>&nbsp;。</p>

<ul>
	<li>比方说，给你&nbsp;<code>finalSum = 12</code>&nbsp;，那么这些拆分是&nbsp;<strong>符合要求</strong> 的（互不相同的正偶数且和为&nbsp;<code>finalSum</code>）：<code>(2 + 10)</code>&nbsp;，<code>(2 + 4 + 6)</code>&nbsp;和&nbsp;<code>(4 + 8)</code>&nbsp;。它们中，<code>(2 + 4 + 6)</code>&nbsp;包含最多数目的整数。注意&nbsp;<code>finalSum</code>&nbsp;不能拆分成&nbsp;<code>(2 + 2 + 4 + 4)</code>&nbsp;，因为拆分出来的整数必须互不相同。</li>
</ul>

<p>请你返回一个整数数组，表示将整数拆分成 <strong>最多</strong> 数目的正偶数数组。如果没有办法将&nbsp;<code>finalSum</code>&nbsp;进行拆分，请你返回一个&nbsp;<strong>空</strong>&nbsp;数组。你可以按 <b>任意</b>&nbsp;顺序返回这些整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>finalSum = 12
<b>输出：</b>[2,4,6]
<b>解释：</b>以下是一些符合要求的拆分：<code>(2 + 10)<span style="">，</span></code><code>(2 + 4 + 6) </code>和 <code>(4 + 8) 。</code>
(2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
[2,6,4] ，[6,2,4] 等等也都是可行的解。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>finalSum = 7
<b>输出：</b>[]
<b>解释：</b>没有办法将 finalSum 进行拆分。
所以返回空数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>finalSum = 28
<b>输出：</b>[6,8,2,12]
<b>解释：</b>以下是一些符合要求的拆分：<code>(2 + 26)<span style="">，</span></code><code>(6 + 8 + 2 + 12)</code> 和 <code>(4 + 24) 。</code>
<code>(6 + 8 + 2 + 12)</code> 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
[10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= finalSum &lt;= 10<sup>10</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumEvenSplit(self, finalSum: int) -> List[int]:
        if finalSum % 2:
            return []
        i = 2
        ans = []
        while i <= finalSum:
            ans.append(i)
            finalSum -= i
            i += 2
        ans[-1] += finalSum
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.add(i);
            finalSum -= i;
        }
        ans.add(ans.remove(ans.size() - 1) + finalSum);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<long long> maximumEvenSplit(long long finalSum) {
        vector<long long> ans;
        if (finalSum % 2) return ans;
        for (long long i = 2; i <= finalSum; i += 2) {
            ans.push_back(i);
            finalSum -= i;
        }
        ans.back() += finalSum;
        return ans;
    }
};
```

### **Go**

```go
func maximumEvenSplit(finalSum int64) []int64 {
	ans := []int64{}
	if finalSum%2 == 1 {
		return ans
	}
	for i := int64(2); i <= finalSum; i += 2 {
		ans = append(ans, i)
		finalSum -= i
	}
	ans[len(ans)-1] += finalSum
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
