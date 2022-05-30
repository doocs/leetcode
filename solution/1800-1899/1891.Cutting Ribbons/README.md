# [1891. 割绳子](https://leetcode.cn/problems/cutting-ribbons)

[English Version](/solution/1800-1899/1891.Cutting%20Ribbons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>ribbons</code> 和一个整数 <code>k</code>，数组每项 <code>ribbons[i]</code> 表示第 <code>i</code> 条绳子的长度。对于每条绳子，你可以将任意切割成一系列长度为<strong>正整数</strong>的部分，或者选择不进行切割。</p>

<p>例如，如果给你一条长度为 <code>4</code> 的绳子，你可以：</p>

<ul>
	<li>保持绳子的长度为 <code>4</code> 不变；</li>
	<li>切割成一条长度为 <code>3</code> 和一条长度为 <code>1</code> 的绳子；</li>
	<li>切割成两条长度为 <code>2</code> 的绳子；</li>
	<li>切割成一条长度为 <code>2</code> 和两条长度为 <code>1</code> 的绳子；</li>
	<li>切割成四条长度为 <code>1</code> 的绳子。</li>
</ul>

<p>你的任务是最终得到 <code>k</code> 条完全一样的绳子，他们的长度均为<strong>相同的正整数</strong>。如果绳子切割后有剩余，你可以直接舍弃掉多余的部分。</p>

<p>对于这 <code>k</code> 根绳子，返回你能得到的绳子<strong>最大</strong>长度；如果你无法得到 <code>k</code> 根相同长度的绳子，返回 <code>0</code>。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> ribbons = [9,7,5], k = 3
<strong>输出:</strong> 5
<strong>解释:</strong>
- 把第一条绳子切成两部分，一条长度为 5，一条长度为 4；
- 把第二条绳子切成两部分，一条长度为 5，一条长度为 2；
- 第三条绳子不进行切割；
现在，你得到了 3 条长度为 5 的绳子。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> ribbons = [7,5,9], k = 4
<strong>输出:</strong> 4
<strong>解释:</strong>
- 把第一条绳子切成两部分，一条长度为 4，一条长度为 3；
- 把第二条绳子切成两部分，一条长度为 4，一条长度为 1；
- 把第二条绳子切成三部分，一条长度为 4，一条长度为 4，还有一条长度为 1；
现在，你得到了 4 条长度为 4 的绳子。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> ribbons = [5,7,9], k = 22
<strong>输出:</strong> 0
<strong>解释:</strong> 由于绳子长度需要为正整数，你无法得到 22 条长度相同的绳子。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= ribbons.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ribbons[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“二分法”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxLength(self, ribbons: List[int], k: int) -> int:
        low, high = 0, 100000
        while low < high:
            mid = (low + high + 1) >> 1
            cnt = 0
            for ribbon in ribbons:
                cnt += ribbon // mid
            if cnt < k:
                high = mid - 1
            else:
                low = mid
        return low
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxLength(int[] ribbons, int k) {
        int low = 0, high = 100000;
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            int cnt = 0;
            for (int ribbon : ribbons) {
                cnt += ribbon / mid;
            }
            if (cnt < k) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} ribbons
 * @param {number} k
 * @return {number}
 */
var maxLength = function (ribbons, k) {
    let low = 0;
    let high = 100000;
    while (low < high) {
        const mid = (low + high + 1) >> 1;
        let cnt = 0;
        for (let ribbon of ribbons) {
            cnt += Math.floor(ribbon / mid);
        }
        if (cnt < k) {
            high = mid - 1;
        } else {
            low = mid;
        }
    }
    return low;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxLength(vector<int>& ribbons, int k) {
        int low = 0, high = 100000;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            int cnt = 0;
            for (auto ribbon : ribbons) {
                cnt += ribbon / mid;
            }
            if (cnt < k) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }
};
```

### **Go**

```go
func maxLength(ribbons []int, k int) int {
	low, high := 0, 100000
	for low < high {
		mid := (low + high + 1) >> 1
		cnt := 0
		for _, ribbon := range ribbons {
			cnt += ribbon / mid
		}
		if cnt < k {
			high = mid - 1
		} else {
			low = mid
		}
	}
	return low
}
```

### **...**

```

```

<!-- tabs:end -->
