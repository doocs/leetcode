---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1186.Maximum%20Subarray%20Sum%20with%20One%20Deletion/README.md
rating: 1799
tags:
    - 数组
    - 动态规划
---

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

### 方法一：预处理 + 枚举

我们可以先预处理出数组 $arr$ 以每个元素结尾和开头的最大子数组和，分别存入数组 $left$ 和 $right$ 中。

如果我们不删除任何元素，那么最大子数组和就是 $left[i]$ 或 $right[i]$ 中的最大值；如果我们删除一个元素，我们可以枚举 $[1..n-2]$ 中的每个位置 $i$，计算 $left[i-1] + right[i+1]$ 的值，取最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumSum(self, arr: List[int]) -> int:
        n = len(arr)
        left = [0] * n
        right = [0] * n
        s = 0
        for i, x in enumerate(arr):
            s = max(s, 0) + x
            left[i] = s
        s = 0
        for i in range(n - 1, -1, -1):
            s = max(s, 0) + arr[i]
            right[i] = s
        ans = max(left)
        for i in range(1, n - 1):
            ans = max(ans, left[i - 1] + right[i + 1])
        return ans
```

```java
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = -(1 << 30);
        for (int i = 0, s = 0; i < n; ++i) {
            s = Math.max(s, 0) + arr[i];
            left[i] = s;
            ans = Math.max(ans, left[i]);
        }
        for (int i = n - 1, s = 0; i >= 0; --i) {
            s = Math.max(s, 0) + arr[i];
            right[i] = s;
        }
        for (int i = 1; i < n - 1; ++i) {
            ans = Math.max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumSum(vector<int>& arr) {
        int n = arr.size();
        int left[n];
        int right[n];
        for (int i = 0, s = 0; i < n; ++i) {
            s = max(s, 0) + arr[i];
            left[i] = s;
        }
        for (int i = n - 1, s = 0; ~i; --i) {
            s = max(s, 0) + arr[i];
            right[i] = s;
        }
        int ans = *max_element(left, left + n);
        for (int i = 1; i < n - 1; ++i) {
            ans = max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
};
```

```go
func maximumSum(arr []int) int {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	for i, s := 0, 0; i < n; i++ {
		s = max(s, 0) + arr[i]
		left[i] = s
	}
	for i, s := n-1, 0; i >= 0; i-- {
		s = max(s, 0) + arr[i]
		right[i] = s
	}
	ans := slices.Max(left)
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
	}
	return ans
}
```

```ts
function maximumSum(arr: number[]): number {
    const n = arr.length;
    const left: number[] = Array(n).fill(0);
    const right: number[] = Array(n).fill(0);
    for (let i = 0, s = 0; i < n; ++i) {
        s = Math.max(s, 0) + arr[i];
        left[i] = s;
    }
    for (let i = n - 1, s = 0; i >= 0; --i) {
        s = Math.max(s, 0) + arr[i];
        right[i] = s;
    }
    let ans = Math.max(...left);
    for (let i = 1; i < n - 1; ++i) {
        ans = Math.max(ans, left[i - 1] + right[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
