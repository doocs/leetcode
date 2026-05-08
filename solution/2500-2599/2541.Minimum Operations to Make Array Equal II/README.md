---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README.md
rating: 1619
source: 第 96 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 数学
---

<!-- problem:start -->

# [2541. 使数组中所有元素相等的最小操作数 II](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii)

[English Version](/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，两个数组长度都是&nbsp;<code>n</code>&nbsp;，再给你一个整数&nbsp;<code>k</code>&nbsp;。你可以对数组&nbsp;<code>nums1</code>&nbsp;进行以下操作：</p>

<ul>
	<li>选择两个下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，将&nbsp;<code>nums1[i]</code>&nbsp;增加&nbsp;<code>k</code>&nbsp;，将&nbsp;<code>nums1[j]</code>&nbsp;减少&nbsp;<code>k</code>&nbsp;。换言之，<code>nums1[i] = nums1[i] + k</code> 且&nbsp;<code>nums1[j] = nums1[j] - k</code>&nbsp;。</li>
</ul>

<p>如果对于所有满足&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;都有&nbsp;<code>num1[i] == nums2[i]</code>&nbsp;，那么我们称&nbsp;<code>nums1</code> <strong>等于</strong>&nbsp;<code>nums2</code>&nbsp;。</p>

<p>请你返回使<em>&nbsp;</em><code>nums1</code><em> </em>等于<em>&nbsp;</em><code>nums2</code>&nbsp;的&nbsp;<strong>最少</strong>&nbsp;操作数。如果没办法让它们相等，请你返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
<b>输出：</b>2
<b>解释：</b>我们可以通过 2 个操作将 nums1 变成 nums2 。
第 1 个操作：i = 2 ，j = 0 。操作后得到 nums1 = [1,3,4,4] 。
第 2 个操作：i = 2 ，j = 3 。操作后得到 nums1 = [1,3,7,1] 。
无法用更少操作使两个数组相等。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
<b>输出：</b>-1
<b>解释：</b>无法使两个数组相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用两个变量 $a$ 和 $b$ 分别记录将 $\textit{nums1}$ 中的元素增加 $k$ 和减少 $k$ 的次数。

我们遍历两个数组，如果两个指针指向的元素相等，则继续；如果两个指针指向的元素不相等，则如果 $k$ 等于 $0$ 或者两个元素之差不能被 $k$ 整除，则返回 $-1$；否则将两个元素之差除以 $k$ 得到操作数 $t$，如果 $t$ 小于 $0$，则将 $-t$ 加到 $a$ 上，否则将 $t$ 加到 $b$ 上。

最后如果 $a$ 和 $b$ 相等，则返回 $a$，否则返回 $-1$。

时间复杂度 $O(n)$，其中 $n$ 是两个数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int], k: int) -> int:
        a = b = 0
        for x, y in zip(nums1, nums2):
            if x == y:
                continue
            if k == 0 or (x - y) % k:
                return -1
            t = (x - y) // k
            if t < 0:
                a += -t
            else:
                b += t
        return a if a == b else -1
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        long a = 0, b = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int x = nums1[i], y = nums2[i];
            if (x == y) {
                continue;
            }
            if (k == 0 || (x - y) % k != 0) {
                return -1;
            }
            int t = (x - y) / k;
            if (t < 0) {
                a += -t;
            } else {
                b += t;
            }
        }
        return a == b ? a : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long a = 0, b = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int x = nums1[i], y = nums2[i];
            if (x == y) {
                continue;
            }
            if (k == 0 || (x - y) % k != 0) {
                return -1;
            }
            int t = (x - y) / k;
            if (t < 0) {
                a += -t;
            } else {
                b += t;
            }
        }
        return a == b ? a : -1;
    }
};
```

#### Go

```go
func minOperations(nums1 []int, nums2 []int, k int) int64 {
	var a, b int64
	for i, x := range nums1 {
		y := nums2[i]
		if x == y {
			continue
		}
		if k == 0 || (x-y)%k != 0 {
			return -1
		}
		t := (x - y) / k
		if t < 0 {
			a += int64(-t)
		} else {
			b += int64(t)
		}
	}
	if a == b {
		return a
	}
	return -1
}
```

#### TypeScript

```ts
function minOperations(nums1: number[], nums2: number[], k: number): number {
    let [a, b] = [0, 0];
    for (let i = 0; i < nums1.length; ++i) {
        const [x, y] = [nums1[i], nums2[i]];
        if (x === y) {
            continue;
        }
        if (k === 0 || (x - y) % k !== 0) {
            return -1;
        }
        const t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a === b ? a : -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let mut a: i64 = 0;
        let mut b: i64 = 0;
        for (&x, &y) in nums1.iter().zip(nums2.iter()) {
            if x == y {
                continue;
            }
            if k == 0 || (x - y) % k != 0 {
                return -1;
            }
            let t = (x - y) / k;
            if t < 0 {
                a += (-t) as i64;
            } else {
                b += t as i64;
            }
        }
        if a == b {
            a
        } else {
            -1
        }
    }
}
```

#### C

```c
long long minOperations(int* nums1, int nums1Size, int* nums2, int nums2Size, int k) {
    long long a = 0, b = 0;
    for (int i = 0; i < nums1Size; ++i) {
        int x = nums1[i], y = nums2[i];
        if (x == y) {
            continue;
        }
        if (k == 0 || (x - y) % k != 0) {
            return -1;
        }
        int t = (x - y) / k;
        if (t < 0) {
            a += -t;
        } else {
            b += t;
        }
    }
    return a == b ? a : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
