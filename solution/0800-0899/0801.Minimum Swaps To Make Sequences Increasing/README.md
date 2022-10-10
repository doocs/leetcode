# [801. 使序列递增的最小交换次数](https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing)

[English Version](/solution/0800-0899/0801.Minimum%20Swaps%20To%20Make%20Sequences%20Increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有两个长度相等且不为空的整型数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;。在一次操作中，我们可以交换&nbsp;<code>nums1[i]</code>&nbsp;和&nbsp;<code>nums2[i]</code>的元素。</p>

<ul>
	<li>例如，如果 <code>nums1 = [1,2,3,<u>8</u>]</code> ， <code>nums2 =[5,6,7,<u>4</u>]</code> ，你可以交换 <code>i = 3</code> 处的元素，得到 <code>nums1 =[1,2,3,4]</code> 和 <code>nums2 =[5,6,7,8]</code> 。</li>
</ul>

<p>返回 <em>使 <code>nums1</code> 和 <code>nums2</code> <strong>严格递增&nbsp;</strong>所需操作的最小次数</em> 。</p>

<p>数组&nbsp;<code>arr</code>&nbsp;<strong>严格递增</strong> 且&nbsp;&nbsp;<code>arr[0] &lt; arr[1] &lt; arr[2] &lt; ... &lt; arr[arr.length - 1]</code>&nbsp;。</p>

<p><b>注意：</b></p>

<ul>
	<li>用例保证可以实现操作。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,3,5,4], nums2 = [1,2,3,7]
<strong>输出:</strong> 1
<strong>解释: </strong>
交换 A[3] 和 B[3] 后，两个数组如下:
A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
两个数组均为严格递增的。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $a$, $b$ 分别表示使得下标 $[0..i]$ 的元素序列严格递增，且第 $i$ 个元素不交换、交换的最小交换次数。下标从 $0$ 开始。

当 $i=0$ 时，有 $a = 0$, $b=1$。

当 $i\gt 0$ 时，我们先将此前 $a$, $b$ 的值保存在 $x$, $y$ 中，然后分情况讨论：

如果 $nums1[i - 1] \ge nums1[i]$ 或者 $nums2[i - 1] \ge nums2[i]$，为了使得两个序列均严格递增，下标 $i-1$ 和 $i$ 对应的元素的相对位置必须发生变化。也就是说，如果前一个位置交换了，那么当前位置不交换，因此有 $a = y$；如果前一个位置没有交换，那么当前位置必须交换，因此有 $b = x + 1$。

否则，下标 $i-1$ 和 $i$ 对应的元素的相对位置可以不发生变化，那么有 $b = y + 1$。另外，如果满足 $nums1[i - 1] \lt nums2[i]$ 并且 $nums2[i - 1] \lt nums1[i]$，那么下标 $i-1$ 和 $i$ 对应的元素的相对位置可以发生变化，此时 $a$ 和 $b$ 可以取较小值，因此有 $a = \min(a, y)$ 和 $b = \min(b, x + 1)$。

最后，返回 $a$ 和 $b$ 中较小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSwap(self, nums1: List[int], nums2: List[int]) -> int:
        a, b = 0, 1
        for i in range(1, len(nums1)):
            x, y = a, b
            if nums1[i - 1] >= nums1[i] or nums2[i - 1] >= nums2[i]:
                a, b = y, x + 1
            else:
                b = y + 1
                if nums1[i - 1] < nums2[i] and nums2[i - 1] < nums1[i]:
                    a, b = min(a, y), min(b, x + 1)
        return min(a, b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int a = 0, b = 1;
        for (int i = 1; i < nums1.length; ++i) {
            int x = a, y = b;
            if (nums1[i - 1] >= nums1[i] || nums2[i - 1] >= nums2[i]) {
                a = y;
                b = x + 1;
            } else {
                b = y + 1;
                if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                    a = Math.min(a, y);
                    b = Math.min(b, x + 1);
                }
            }
        }
        return Math.min(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwap(vector<int>& nums1, vector<int>& nums2) {
        int a = 0, b = 1, n = nums1.size();
        for (int i = 1; i < n; ++i) {
            int x = a, y = b;
            if (nums1[i - 1] >= nums1[i] || nums2[i - 1] >= nums2[i]) {
                a = y, b = x + 1;
            } else {
                b = y + 1;
                if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                    a = min(a, y);
                    b = min(b, x + 1);
                }
            }
        }
        return min(a, b);
    }
};
```

### **Go**

```go
func minSwap(nums1 []int, nums2 []int) int {
	a, b, n := 0, 1, len(nums1)
	for i := 1; i < n; i++ {
		x, y := a, b
		if nums1[i-1] >= nums1[i] || nums2[i-1] >= nums2[i] {
			a, b = y, x+1
		} else {
			b = y + 1
			if nums1[i-1] < nums2[i] && nums2[i-1] < nums1[i] {
				a = min(a, y)
				b = min(b, x+1)
			}
		}
	}
	return min(a, b)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
