# [1846. 减小和重新排列数组后的最大元素](https://leetcode.cn/problems/maximum-element-after-decreasing-and-rearranging)

[English Version](/solution/1800-1899/1846.Maximum%20Element%20After%20Decreasing%20and%20Rearranging/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>arr</code> 。请你对 <code>arr</code> 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：</p>

<ul>
	<li><code>arr</code> 中 <strong>第一个</strong> 元素必须为 <code>1</code> 。</li>
	<li>任意相邻两个元素的差的绝对值 <strong>小于等于</strong> <code>1</code> ，也就是说，对于任意的 <code>1 <= i < arr.length</code> （<strong>数组下标从 0 开始</strong>），都满足 <code>abs(arr[i] - arr[i - 1]) <= 1</code> 。<code>abs(x)</code> 为 <code>x</code> 的绝对值。</li>
</ul>

<p>你可以执行以下 2 种操作任意次：</p>

<ul>
	<li><strong>减小</strong> <code>arr</code> 中任意元素的值，使其变为一个 <strong>更小的正整数</strong> 。</li>
	<li><strong>重新排列</strong> <code>arr</code> 中的元素，你可以以任意顺序重新排列。</li>
</ul>

<p>请你返回执行以上操作后，在满足前文所述的条件下，<code>arr</code> 中可能的 <strong>最大值</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>arr = [2,2,1,2,1]
<b>输出：</b>2
<b>解释：</b>
我们可以重新排列 arr 得到 <code>[1,2,2,2,1] ，该数组满足所有条件。</code>
arr 中最大元素为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [100,1,1000]
<b>输出：</b>3
<b>解释：</b>
一个可行的方案如下：
1. 重新排列 <code>arr</code> 得到 <code>[1,100,1000] 。</code>
2. 将第二个元素减小为 2 。
3. 将第三个元素减小为 3 。
现在 <code>arr = [1,2,3] ，满足所有条件。</code>
arr 中最大元素为 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [1,2,3,4,5]
<b>输出：</b>5
<b>解释：</b>数组已经满足所有条件，最大元素为 5 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= arr[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumElementAfterDecrementingAndRearranging(self, arr: List[int]) -> int:
        arr.sort()
        arr[0] = 1
        for i in range(1, len(arr)):
            d = max(0, arr[i] - arr[i - 1] - 1)
            arr[i] -= d
        return max(arr)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.length; ++i) {
            int d = Math.max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.size(); ++i) {
            int d = max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = max(ans, arr[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumElementAfterDecrementingAndRearranging(arr []int) int {
	sort.Ints(arr)
	ans := 1
	arr[0] = 1
	for i := 1; i < len(arr); i++ {
		d := max(0, arr[i]-arr[i-1]-1)
		arr[i] -= d
		ans = max(ans, arr[i])
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

### **...**

```

```

<!-- tabs:end -->
