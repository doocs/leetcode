# [2865. 美丽塔 I](https://leetcode.cn/problems/beautiful-towers-i)

[English Version](/solution/2800-2899/2865.Beautiful%20Towers%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>maxHeights</code>&nbsp;。</p>

<p>你的任务是在坐标轴上建 <code>n</code>&nbsp;座塔。第&nbsp;<code>i</code>&nbsp;座塔的下标为 <code>i</code>&nbsp;，高度为&nbsp;<code>heights[i]</code>&nbsp;。</p>

<p>如果以下条件满足，我们称这些塔是 <strong>美丽</strong>&nbsp;的：</p>

<ol>
	<li><code>1 &lt;= heights[i] &lt;= maxHeights[i]</code></li>
	<li><code>heights</code>&nbsp;是一个 <strong>山状</strong>&nbsp;数组。</li>
</ol>

<p>如果存在下标 <code>i</code>&nbsp;满足以下条件，那么我们称数组&nbsp;<code>heights</code>&nbsp;是一个 <strong>山状</strong>&nbsp;数组：</p>

<ul>
	<li>对于所有&nbsp;<code>0 &lt; j &lt;= i</code>&nbsp;，都有&nbsp;<code>heights[j - 1] &lt;= heights[j]</code></li>
	<li>对于所有&nbsp;<code>i &lt;= k &lt; n - 1</code>&nbsp;，都有&nbsp;<code>heights[k + 1] &lt;= heights[k]</code></li>
</ul>

<p>请你返回满足 <b>美丽塔</b>&nbsp;要求的方案中，<strong>高度和的最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>maxHeights = [5,3,4,1,1]
<b>输出：</b>13
<b>解释：</b>和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
- 1 &lt;= heights[i] &lt;= maxHeights[i]  
- heights 是个山状数组，峰值在 i = 0 处。
13 是所有美丽塔方案中的最大高度和。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>maxHeights = [6,5,3,9,2,7]
<b>输出：</b>22
<strong>解释：</strong> 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
- 1 &lt;= heights[i] &lt;= maxHeights[i]
- heights 是个山状数组，峰值在 i = 3 处。
22 是所有美丽塔方案中的最大高度和。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>maxHeights = [3,2,5,5,2,3]
<b>输出：</b>18
<strong>解释：</strong>和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
- 1 &lt;= heights[i] &lt;= maxHeights[i]
- heights 是个山状数组，最大值在 i = 2 处。
注意，在这个方案中，i = 3 也是一个峰值。
18 是所有美丽塔方案中的最大高度和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == maxHeights &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= maxHeights[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSumOfHeights(self, maxHeights: List[int]) -> int:
        ans, n = 0, len(maxHeights)
        for i, x in enumerate(maxHeights):
            y = t = x
            for j in range(i - 1, -1, -1):
                y = min(y, maxHeights[j])
                t += y
            y = x
            for j in range(i + 1, n):
                y = min(y, maxHeights[j])
                t += y
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long ans = 0;
        int n = maxHeights.size();
        for (int i = 0; i < n; ++i) {
            int y = maxHeights.get(i);
            long t = y;
            for (int j = i - 1; j >= 0; --j) {
                y = Math.min(y, maxHeights.get(j));
                t += y;
            }
            y = maxHeights.get(i);
            for (int j = i + 1; j < n; ++j) {
                y = Math.min(y, maxHeights.get(j));
                t += y;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumSumOfHeights(vector<int>& maxHeights) {
        long long ans = 0;
        int n = maxHeights.size();
        for (int i = 0; i < n; ++i) {
            long long t = maxHeights[i];
            int y = t;
            for (int j = i - 1; ~j; --j) {
                y = min(y, maxHeights[j]);
                t += y;
            }
            y = maxHeights[i];
            for (int j = i + 1; j < n; ++j) {
                y = min(y, maxHeights[j]);
                t += y;
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumSumOfHeights(maxHeights []int) (ans int64) {
	n := len(maxHeights)
	for i, x := range maxHeights {
		y, t := x, x
		for j := i - 1; j >= 0; j-- {
			y = min(y, maxHeights[j])
			t += y
		}
		y = x
		for j := i + 1; j < n; j++ {
			y = min(y, maxHeights[j])
			t += y
		}
		ans = max(ans, int64(t))
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maximumSumOfHeights(maxHeights: number[]): number {
    let ans = 0;
    const n = maxHeights.length;
    for (let i = 0; i < n; ++i) {
        const x = maxHeights[i];
        let [y, t] = [x, x];
        for (let j = i - 1; ~j; --j) {
            y = Math.min(y, maxHeights[j]);
            t += y;
        }
        y = x;
        for (let j = i + 1; j < n; ++j) {
            y = Math.min(y, maxHeights[j]);
            t += y;
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
