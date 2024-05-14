---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1537.Get%20the%20Maximum%20Score/README.md
rating: 1961
tags:
    - 贪心
    - 数组
    - 双指针
    - 动态规划
---

# [1537. 最大得分](https://leetcode.cn/problems/get-the-maximum-score)

[English Version](/solution/1500-1599/1537.Get%20the%20Maximum%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有两个 <strong>有序</strong>&nbsp;且数组内元素互不相同的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;。</p>

<p>一条&nbsp;<strong>合法路径</strong>&nbsp;定义如下：</p>

<ul>
	<li>选择数组 <code>nums1</code> 或者 <code>nums2</code> 开始遍历（从下标 0 处开始）。</li>
	<li>从左到右遍历当前数组。</li>
	<li>如果你遇到了 <code>nums1</code>&nbsp;和 <code>nums2</code>&nbsp;中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。</li>
</ul>

<p><strong>得分</strong> 定义为合法路径中不同数字的和。</p>

<p>请你返回 <em>所有可能 <strong>合法路径</strong> 中的最大得分。</em>由于答案可能很大，请你将它对 10^9 + 7 取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1537.Get%20the%20Maximum%20Score/images/sample_1_1893.png" style="height: 163px; width: 538px;" /></strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
<strong>输出：</strong>30
<strong>解释：</strong>合法路径包括：
[2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],（从 nums1 开始遍历）
[4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]  （从 nums2 开始遍历）
最大得分为上图中的绿色路径 <strong>[2,4,6,8,10]</strong>&nbsp;。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,3,5,7,9], nums2 = [3,5,100]
<strong>输出：</strong>109
<strong>解释：</strong>最大得分由路径 <strong>[1,3,5,100]</strong> 得到。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
<strong>输出：</strong>40
<strong>解释：</strong>nums1 和 nums2 之间无相同数字。
最大得分由路径[6,7,8,9,10]得到。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;都是严格递增的数组。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maxSum(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        m, n = len(nums1), len(nums2)
        i = j = 0
        f = g = 0
        while i < m or j < n:
            if i == m:
                g += nums2[j]
                j += 1
            elif j == n:
                f += nums1[i]
                i += 1
            elif nums1[i] < nums2[j]:
                f += nums1[i]
                i += 1
            elif nums1[i] > nums2[j]:
                g += nums2[j]
                j += 1
            else:
                f = g = max(f, g) + nums1[i]
                i += 1
                j += 1
        return max(f, g) % mod
```

```java
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        final int mod = (int) 1e9 + 7;
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        long f = 0, g = 0;
        while (i < m || j < n) {
            if (i == m) {
                g += nums2[j++];
            } else if (j == n) {
                f += nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                f += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                g += nums2[j++];
            } else {
                f = g = Math.max(f, g) + nums1[i];
                i++;
                j++;
            }
        }
        return (int) (Math.max(f, g) % mod);
    }
}
```

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        int m = nums1.size(), n = nums2.size();
        int i = 0, j = 0;
        long long f = 0, g = 0;
        while (i < m || j < n) {
            if (i == m) {
                g += nums2[j++];
            } else if (j == n) {
                f += nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                f += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                g += nums2[j++];
            } else {
                f = g = max(f, g) + nums1[i];
                i++;
                j++;
            }
        }
        return max(f, g) % mod;
    }
};
```

```go
func maxSum(nums1 []int, nums2 []int) int {
	const mod int = 1e9 + 7
	m, n := len(nums1), len(nums2)
	i, j := 0, 0
	f, g := 0, 0
	for i < m || j < n {
		if i == m {
			g += nums2[j]
			j++
		} else if j == n {
			f += nums1[i]
			i++
		} else if nums1[i] < nums2[j] {
			f += nums1[i]
			i++
		} else if nums1[i] > nums2[j] {
			g += nums2[j]
			j++
		} else {
			f = max(f, g) + nums1[i]
			g = f
			i++
			j++
		}
	}
	return max(f, g) % mod
}
```

```ts
function maxSum(nums1: number[], nums2: number[]): number {
    const mod = 1e9 + 7;
    const m = nums1.length;
    const n = nums2.length;
    let [f, g] = [0, 0];
    let [i, j] = [0, 0];
    while (i < m || j < n) {
        if (i === m) {
            g += nums2[j++];
        } else if (j === n) {
            f += nums1[i++];
        } else if (nums1[i] < nums2[j]) {
            f += nums1[i++];
        } else if (nums1[i] > nums2[j]) {
            g += nums2[j++];
        } else {
            f = g = Math.max(f, g) + nums1[i];
            i++;
            j++;
        }
    }
    return Math.max(f, g) % mod;
}
```

<!-- tabs:end -->

<!-- end -->
