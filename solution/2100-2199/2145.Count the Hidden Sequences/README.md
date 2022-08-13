# [2145. 统计隐藏数组数目](https://leetcode.cn/problems/count-the-hidden-sequences)

[English Version](/solution/2100-2199/2145.Count%20the%20Hidden%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>differences</code>&nbsp;，它表示一个长度为&nbsp;<code>n + 1</code>&nbsp;的&nbsp;<strong>隐藏</strong>&nbsp;数组&nbsp;<strong>相邻</strong>&nbsp;元素之间的&nbsp;<strong>差值</strong>&nbsp;。更正式的表述为：我们将隐藏数组记作&nbsp;<code>hidden</code>&nbsp;，那么&nbsp;<code>differences[i] = hidden[i + 1] - hidden[i]</code>&nbsp;。</p>

<p>同时给你两个整数&nbsp;<code>lower</code> 和&nbsp;<code>upper</code>&nbsp;，它们表示隐藏数组中所有数字的值都在 <strong>闭</strong>&nbsp;区间&nbsp;<code>[lower, upper]</code>&nbsp;之间。</p>

<ul>
	<li>比方说，<code>differences = [1, -3, 4]</code>&nbsp;，<code>lower = 1</code>&nbsp;，<code>upper = 6</code>&nbsp;，那么隐藏数组是一个长度为 <code>4</code>&nbsp;且所有值都在&nbsp;<code>1</code>&nbsp;和&nbsp;<code>6</code>&nbsp;（包含两者）之间的数组。
    <ul>
    	<li><code>[3, 4, 1, 5]</code> 和&nbsp;<code>[4, 5, 2, 6]</code>&nbsp;都是符合要求的隐藏数组。</li>
    	<li><code>[5, 6, 3, 7]</code>&nbsp;不符合要求，因为它包含大于 <code>6</code>&nbsp;的元素。</li>
    	<li><code>[1, 2, 3, 4]</code>&nbsp;不符合要求，因为相邻元素的差值不符合给定数据。</li>
    </ul>
    </li>
</ul>

<p>请你返回 <strong>符合</strong>&nbsp;要求的隐藏数组的数目。如果没有符合要求的隐藏数组，请返回 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>differences = [1,-3,4], lower = 1, upper = 6
<b>输出：</b>2
<b>解释：</b>符合要求的隐藏数组为：
- [3, 4, 1, 5]
- [4, 5, 2, 6]
所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>differences = [3,-4,5,1,-2], lower = -4, upper = 5
<b>输出：</b>4
<b>解释：</b>符合要求的隐藏数组为：
- [-3, 0, -4, 1, 2, 0]
- [-2, 1, -3, 2, 3, 1]
- [-1, 2, -2, 3, 4, 2]
- [0, 3, -1, 4, 5, 3]
所以返回 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>differences = [4,-7,2], lower = 3, upper = 6
<b>输出：</b>0
<b>解释：</b>没有符合要求的隐藏数组，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == differences.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= differences[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        num = mi = mx = 0
        for d in differences:
            num += d
            mi = min(mi, num)
            mx = max(mx, num)
        return max(0, upper - lower - (mx - mi) + 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long num = 0, mi = 0, mx = 0;
        for (int d : differences) {
            num += d;
            mi = Math.min(mi, num);
            mx = Math.max(mx, num);
        }
        return Math.max(0, (int) (upper - lower - (mx - mi) + 1));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArrays(vector<int>& differences, int lower, int upper) {
        long long num = 0, mi = 0, mx = 0;
        for (int& d : differences) {
            num += d;
            mi = min(mi, num);
            mx = max(mx, num);
        }
        return max(0, (int)(upper - lower - (mx - mi) + 1));
    }
};
```

### **Go**

```go
func numberOfArrays(differences []int, lower int, upper int) int {
	num, mi, mx := 0, 0, 0
	for _, d := range differences {
		num += d
		mi = min(mi, num)
		mx = max(mx, num)
	}
	return max(0, upper-lower-(mx-mi)+1)
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
