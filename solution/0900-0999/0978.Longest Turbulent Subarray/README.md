# [978. 最长湍流子数组](https://leetcode.cn/problems/longest-turbulent-subarray)

[English Version](/solution/0900-0999/0978.Longest%20Turbulent%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>arr</code>&nbsp;，返回 <code>arr</code>&nbsp;的&nbsp;<em>最大湍流子数组的<strong>长度</strong></em><strong>&nbsp;</strong>。</p>

<p>如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是&nbsp;<strong>湍流子数组</strong>&nbsp;。</p>

<p>更正式地来说，当 <code>arr</code>&nbsp;的子数组&nbsp;<code>A[i], A[i+1], ..., A[j]</code>&nbsp;满足仅满足下列条件时，我们称其为<em>湍流子数组</em>：</p>

<ul>
	<li>若&nbsp;<code>i &lt;= k &lt; j</code>&nbsp;：
    <ul>
    	<li>当 <code>k</code>&nbsp;为奇数时，&nbsp;<code>A[k] &gt; A[k+1]</code>，且</li>
    	<li>当 <code>k</code> 为偶数时，<code>A[k] &lt; A[k+1]</code>；</li>
    </ul>
    </li>
    <li><strong>或 </strong>若&nbsp;<code>i &lt;= k &lt; j</code>&nbsp;：
    <ul>
    	<li>当 <code>k</code> 为偶数时，<code>A[k] &gt; A[k+1]</code>&nbsp;，且</li>
    	<li>当 <code>k</code>&nbsp;为奇数时，&nbsp;<code>A[k] &lt; A[k+1]</code>。</li>
    </ul>
    </li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [9,4,2,10,7,8,8,1,9]
<strong>输出：</strong>5
<strong>解释：</strong>arr[1] &gt; arr[2] &lt; arr[3] &gt; arr[4] &lt; arr[5]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [4,8,12,16]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [100]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示以 $nums[i]$ 结尾且结尾处于上升状态的最长湍流子数组的长度，定义 $g[i]$ 表示以 $nums[i]$ 结尾且结尾处于下降状态的最长湍流子数组的长度。初始时 $f[0] = 1$, $g[0] = 1$。答案为 $max(f[i], g[i])$。

对于 $i \gt 0$，若 $nums[i] \gt nums[i - 1]$，则 $f[i] = g[i - 1] + 1$，否则 $f[i] = 1$；若 $nums[i] \lt nums[i - 1]$，则 $g[i] = f[i - 1] + 1$，否则 $g[i] = 1$。

由于 $f[i]$ 和 $g[i]$ 只与 $f[i - 1]$ 和 $g[i - 1]$ 有关，因此可以使用两个变量代替数组。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTurbulenceSize(self, arr: List[int]) -> int:
        ans = f = g = 1
        for a, b in pairwise(arr):
            ff = g + 1 if a < b else 1
            gg = f + 1 if a > b else 1
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int ans = 1, f = 1, g = 1;
        for (int i = 1; i < arr.length; ++i) {
            int ff = arr[i - 1] < arr[i] ? g + 1 : 1;
            int gg = arr[i - 1] > arr[i] ? f + 1 : 1;
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTurbulenceSize(vector<int>& arr) {
        int ans = 1, f = 1, g = 1;
        for (int i = 1; i < arr.size(); ++i) {
            int ff = arr[i - 1] < arr[i] ? g + 1 : 1;
            int gg = arr[i - 1] > arr[i] ? f + 1 : 1;
            f = ff;
            g = gg;
            ans = max({ans, f, g});
        }
        return ans;
    }
};
```

### **Go**

```go
func maxTurbulenceSize(arr []int) int {
	ans, f, g := 1, 1, 1
	for i, x := range arr[1:] {
		ff, gg := 1, 1
		if arr[i] < x {
			ff = g + 1
		}
		if arr[i] > x {
			gg = f + 1
		}
		f, g = ff, gg
		ans = max(ans, max(f, g))
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
function maxTurbulenceSize(arr: number[]): number {
    let f = 1;
    let g = 1;
    let ans = 1;
    for (let i = 1; i < arr.length; ++i) {
        const ff = arr[i - 1] < arr[i] ? g + 1 : 1;
        const gg = arr[i - 1] > arr[i] ? f + 1 : 1;
        f = ff;
        g = gg;
        ans = Math.max(ans, f, g);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
