# [2425. 所有数对的异或和](https://leetcode.cn/problems/bitwise-xor-of-all-pairings)

[English Version](/solution/2400-2499/2425.Bitwise%20XOR%20of%20All%20Pairings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，两个数组都只包含非负整数。请你求出另外一个数组&nbsp;<code>nums3</code>&nbsp;，包含 <code>nums1</code>&nbsp;和 <code>nums2</code>&nbsp;中 <strong>所有数对</strong>&nbsp;的异或和（<code>nums1</code>&nbsp;中每个整数都跟 <code>nums2</code>&nbsp;中每个整数 <strong>恰好</strong>&nbsp;匹配一次）。</p>

<p>请你返回 <code>nums3</code>&nbsp;中所有整数的 <strong>异或和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [2,1,3], nums2 = [10,2,5,0]
<b>输出：</b>13
<strong>解释：</strong>
一个可能的 nums3 数组是 [8,0,7,2,11,3,4,1,9,1,6,3] 。
所有这些数字的异或和是 13 ，所以我们返回 13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,2], nums2 = [3,4]
<b>输出：</b>0
<strong>解释：</strong>
所有数对异或和的结果分别为 nums1[0] ^ nums2[0] ，nums1[0] ^ nums2[1] ，nums1[1] ^ nums2[0] 和 nums1[1] ^ nums2[1] 。
所以，一个可能的 nums3 数组是 [2,5,1,6] 。
2 ^ 5 ^ 1 ^ 6 = 0 ，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯 + 位运算**

由于数组的每个元素都会与另一个数组的每个元素进行异或，我们知道，同一个数异或两次，结果不变，即 `a ^ a = 0`。因此，我们只需要统计数组的长度，就能知道每个元素与另一个数组的每个元素进行异或的次数。

如果 `nums2` 数组长度为奇数，那么相当于 `nums1` 中每个元素都与 `nums2` 中的每个元素进行了奇数次异或，因此 `nums1` 数组的最终异或结果即为 `nums1` 数组的所有元素异或的结果。如果为偶数，那么相当于 `nums1` 中每个元素都与 `nums2` 中的每个元素进行了偶数次异或，因此 `nums1` 数组的最终异或结果为 0。

同理，我们可以得知 `nums2` 数组的最终异或结果。

最终把两个异或结果再异或一次，即可得到最终结果。

时间复杂度 $O(m+n)$。其中 $m$ 和 $n$ 分别为数组 `nums1` 和 `nums2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def xorAllNums(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 0
        if len(nums2) & 1:
            for v in nums1:
                ans ^= v
        if len(nums1) & 1:
            for v in nums2:
                ans ^= v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int ans = 0;
        if (nums2.length % 2 == 1) {
            for (int v : nums1) {
                ans ^= v;
            }
        }
        if (nums1.length % 2 == 1) {
            for (int v : nums2) {
                ans ^= v;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int xorAllNums(vector<int>& nums1, vector<int>& nums2) {
        int ans = 0;
        if (nums2.size() % 2 == 1) {
            for (int v : nums1) {
                ans ^= v;
            }
        }
        if (nums1.size() % 2 == 1) {
            for (int v : nums2) {
                ans ^= v;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func xorAllNums(nums1 []int, nums2 []int) int {
	ans := 0
	if len(nums2)%2 == 1 {
		for _, v := range nums1 {
			ans ^= v
		}
	}
	if len(nums1)%2 == 1 {
		for _, v := range nums2 {
			ans ^= v
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function xorAllNums(nums1: number[], nums2: number[]): number {
    let ans = 0;
    if (nums2.length % 2 != 0) {
        ans ^= nums1.reduce((a, c) => a ^ c, 0);
    }
    if (nums1.length % 2 != 0) {
        ans ^= nums2.reduce((a, c) => a ^ c, 0);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
