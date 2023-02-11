# [1043. 分隔数组以得到最大和](https://leetcode.cn/problems/partition-array-for-maximum-sum)

[English Version](/solution/1000-1099/1043.Partition%20Array%20for%20Maximum%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code>，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。</p>

<p>返回将数组分隔变换后能够得到的元素最大和。</p>

<p> </p>

<p><strong>注意，</strong>原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,15,7,9,2,5,10], k = 3
<strong>输出：</strong>84
<strong>解释：</strong>
因为 k=3 可以分隔成 [1,15,7] [9] [2,5,10]，结果为 [15,15,15,9,10,10,10]，和为 84，是该数组所有分隔变换后元素总和最大的。
若是分隔成 [1] [15,7,9] [2,5,10]，结果就是 [1, 15, 15, 15, 10, 10, 10] 但这种分隔方式的元素总和（76）小于上一种。 </pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
<strong>输出：</strong>83
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1], k = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 500</code></li>
	<li><code>0 <= arr[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= k <= arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示将数组的前 $i$ 个元素分隔成若干个子数组，最终的最大元素和。那么 $f[i + 1]$ 的值可以通过枚举 $j$ 的值得到，其中 $j$ 的取值范围为 $[i - k + 1, i]$，对于每个 $j$，我们都可以将 $[j, i]$ 这一段分隔出来，这一段的最大值为 $mx$，那么 $f[i + 1]$ 的值可以通过 $f[j] + mx * (i - j + 1)$ 得到。最后的答案即为 $f[n]$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        n = len(arr)
        f = [0] * (n + 1)
        for i in range(n):
            mx = 0
            for j in range(i, max(-1, i - k), -1):
                mx = max(mx, arr[j])
                t = mx * (i - j + 1) + f[j]
                f[i + 1] = max(f[i + 1], t)
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= Math.max(0, i - k + 1); --j) {
                mx = Math.max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = Math.max(f[i + 1], t);
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        int f[n + 1];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= max(0, i - k + 1); --j) {
                mx = max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = max(f[i + 1], t);
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func maxSumAfterPartitioning(arr []int, k int) int {
	n := len(arr)
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		mx := 0
		for j := i; j >= max(0, i-k+1); j-- {
			mx = max(mx, arr[j])
			t := mx*(i-j+1) + f[j]
			f[i+1] = max(f[i+1], t)
		}
	}
	return f[n]
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
