# [1043. 分隔数组以得到最大和](https://leetcode.cn/problems/partition-array-for-maximum-sum)

[English Version](/solution/1000-1099/1043.Partition%20Array%20for%20Maximum%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code>，请你将该数组分隔为长度 <strong>最多 </strong>为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。</p>

<p>返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,15,7,9,2,5,10], k = 3
<strong>输出：</strong>84
<strong>解释：</strong>数组变为 [15,15,15,9,10,10,10]</pre>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示将数组的前 $i$ 个元素分隔成若干个子数组，最终的最大元素和。初始时 $f[i]=0$，答案为 $f[n]$。

我们考虑如何计算 $f[i]$，其中 $i \geq 1$。

对于 $f[i]$，它的最后一个元素是 $arr[i-1]$。由于每个子数组的长度最多为 $k$，并且我们需要求得子数组中的最大值，因此，我们可以从右往左枚举最后一个子数组的第一个元素 $arr[j - 1]$，其中 $\max(0, i - k) \lt j \leq i$，过程中维护一个变量 $mx$，表示最后一个子数组中的最大值，那么状态转移方程为：

$$
f[i] = \max\{f[i], f[j - 1] + mx \times (i - j + 1)\}
$$

最终的答案即为 $f[n]$。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        n = len(arr)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            mx = 0
            for j in range(i, max(0, i - k), -1):
                mx = max(mx, arr[j - 1])
                f[i] = max(f[i], f[j - 1] + mx * (i - j + 1))
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int mx = 0;
            for (int j = i; j > Math.max(0, i - k); --j) {
                mx = Math.max(mx, arr[j - 1]);
                f[i] = Math.max(f[i], f[j - 1] + mx * (i - j + 1));
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
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            int mx = 0;
            for (int j = i; j > max(0, i - k); --j) {
                mx = max(mx, arr[j - 1]);
                f[i] = max(f[i], f[j - 1] + mx * (i - j + 1));
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
	for i := 1; i <= n; i++ {
		mx := 0
		for j := i; j > max(0, i-k); j-- {
			mx = max(mx, arr[j-1])
			f[i] = max(f[i], f[j-1]+mx*(i-j+1))
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

### **TypeScript**

```ts
function maxSumAfterPartitioning(arr: number[], k: number): number {
    const n: number = arr.length;
    const f: number[] = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        let mx: number = 0;
        for (let j = i; j > Math.max(0, i - k); --j) {
            mx = Math.max(mx, arr[j - 1]);
            f[i] = Math.max(f[i], f[j - 1] + mx * (i - j + 1));
        }
    }
    return f[n];
}
```

### **...**

```

```

<!-- tabs:end -->
