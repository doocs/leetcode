# [1186. 删除一次得到子数组最大和](https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion)

[English Version](/solution/1100-1199/1186.Maximum%20Subarray%20Sum%20with%20One%20Deletion/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组，返回它的某个&nbsp;<strong>非空</strong> 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。</p>

<p>注意，删除一个元素后，子数组 <strong>不能为空</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,-2,0,3]
<strong>输出：</strong>4
<strong>解释：</strong>我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,-2,-2,3]
<strong>输出：</strong>3
<strong>解释：</strong>我们直接选出 [3]，这就是最大和。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [-1,-1,-1,-1]
<strong>输出：</strong>-1
<strong>解释：</strong>最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
     我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 枚举**

我们可以先预处理出数组 `arr` 以每个元素结尾和开头的最大子数组和，分别存入数组 `left` 和 `right` 中。然后枚举 `arr` 中的每个元素，如果删除该元素，则最大子数组和为 `left[i - 1] + right[i + 1]`。最后取所有可能的最大值即可，注意也可能不删除任何元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSum(self, arr: List[int]) -> int:
        n = len(arr)
        left = [0] * n
        right = [0] * n
        t = 0
        for i, v in enumerate(arr):
            t = max(t, 0) + v
            left[i] = t
        t = 0
        for i in range(n - 1, -1, -1):
            t = max(t, 0) + arr[i]
            right[i] = t
        ans = max(left)
        for i in range(1, n - 1):
            ans = max(ans, left[i - 1] + right[i + 1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int t = 0;
        for (int i = 0; i < n; ++i) {
            t = Math.max(t, 0) + arr[i];
            left[i] = t;
        }
        t = 0;
        for (int i = n - 1; i >= 0; --i) {
            t = Math.max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = Arrays.stream(left).max().getAsInt();
        for (int i = 1; i < n - 1; ++i) {
            ans = Math.max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSum(vector<int>& arr) {
        int n = arr.size();
        int left[n];
        int right[n];
        for (int i = 0, t = 0; i < n; ++i) {
            t = max(t, 0) + arr[i];
            left[i] = t;
        }
        for (int i = n - 1, t = 0; ~i; --i) {
            t = max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = *max_element(left, left + n);
        for (int i = 1; i < n - 1; ++i) {
            ans = max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumSum(arr []int) int {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	t := 0
	ans := math.MinInt32
	for i, v := range arr {
		t = max(t, 0) + v
		left[i] = t
		ans = max(ans, left[i])
	}
	t = 0
	for i := n - 1; i >= 0; i-- {
		t = max(t, 0) + arr[i]
		right[i] = t
	}
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
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
