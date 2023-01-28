# [2541. 使数组中所有元素相等的最小操作数 II](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii)

[English Version](/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

我们用变量 $x$ 记录加减次数的差值，用变量 $ans$ 记录操作次数。

遍历数组，对于每个位置 $i$，如果存在 $k=0$ 并且 $a_i \neq b_i$，则无法使两个数组相等，返回 $-1$。否则，如果 $k \neq 0$，则 $a_i - b_i$ 必须是 $k$ 的倍数，否则无法使两个数组相等，返回 $-1$。接下来，我们更新 $x$ 和 $ans$。

最后，如果 $x \neq 0$，则无法使两个数组相等，返回 $-1$。否则，返回 $\frac{ans}{2}$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int], k: int) -> int:
        ans = x = 0
        for a, b in zip(nums1, nums2):
            if k == 0:
                if a != b:
                    return -1
                continue
            if (a - b) % k:
                return -1
            y = (a - b) // k
            ans += abs(y)
            x += y
        return -1 if x else ans // 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        long ans = 0, x = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += Math.abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long ans = 0, x = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
};
```

### **Go**

```go
func minOperations(nums1 []int, nums2 []int, k int) int64 {
	ans, x := 0, 0
	for i, a := range nums1 {
		b := nums2[i]
		if k == 0 {
			if a != b {
				return -1
			}
			continue
		}
		if (a-b)%k != 0 {
			return -1
		}
		y := (a - b) / k
		ans += abs(y)
		x += y
	}
	if x != 0 {
		return -1
	}
	return int64(ans / 2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minOperations(nums1: number[], nums2: number[], k: number): number {
    const n = nums1.length;
    if (k === 0) {
        return nums1.every((v, i) => v === nums2[i]) ? 0 : -1;
    }
    let sum1 = 0;
    let sum2 = 0;
    for (let i = 0; i < n; i++) {
        const diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k !== 0) {
            return -1;
        }
        sum2 += Math.abs(diff);
    }
    if (sum1 !== 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let k = k as i64;
        let n = nums1.len();
        if k == 0 {
            return if nums1.iter().enumerate().all(|(i, &v)| v == nums2[i]) {
                0
            } else {
                -1
            };
        }
        let mut sum1 = 0;
        let mut sum2 = 0;
        for i in 0..n {
            let diff = (nums1[i] - nums2[i]) as i64;
            sum1 += diff;
            if diff % k != 0 {
                return -1;
            }
            sum2 += diff.abs();
        }
        if sum1 != 0 {
            return -1;
        }
        sum2 / (k * 2)
    }
}
```

### **C**

```c
long long minOperations(int *nums1, int nums1Size, int *nums2, int nums2Size, int k) {
    if (k == 0) {
        for (int i = 0; i < nums1Size; i++) {
            if (nums1[i] != nums2[i]) {
                return -1;
            }
        }
        return 0;
    }
    long long sum1 = 0;
    long long sum2 = 0;
    for (int i = 0; i < nums1Size; i++) {
        long long diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k != 0) {
            return -1;
        }
        sum2 += llabs(diff);
    }
    if (sum1 != 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
```

### **...**

```

```

<!-- tabs:end -->
