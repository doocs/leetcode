# [2401. 最长优雅子数组](https://leetcode.cn/problems/longest-nice-subarray)

[English Version](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>正</strong> 整数组成的数组 <code>nums</code> 。</p>

<p>如果&nbsp;<code>nums</code> 的子数组中位于 <strong>不同</strong> 位置的每对元素按位 <strong>与（AND）</strong>运算的结果等于 <code>0</code> ，则称该子数组为 <strong>优雅</strong> 子数组。</p>

<p>返回 <strong>最长</strong> 的优雅子数组的长度。</p>

<p><strong>子数组</strong> 是数组中的一个 <strong>连续</strong> 部分。</p>

<p><strong>注意：</strong>长度为 <code>1</code> 的子数组始终视作优雅子数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,8,48,10]
<strong>输出：</strong>3
<strong>解释：</strong>最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
- 3 AND 8 = 0
- 3 AND 48 = 0
- 8 AND 48 = 0
可以证明不存在更长的优雅子数组，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,5,11,13]
<strong>输出：</strong>1
<strong>解释：</strong>最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们定义一个变量 $mask$，用于记录当前子数组中的元素按位或的结果，初始时 $mask = 0$。另外，使用双指针 $j$ 和 $i$ 分别指向当前子数组的左右端点，初始时 $i = j = 0$。

接下来，我们从左到右遍历数组 $nums$，对于遍历到的每个元素 $x$：

我们将其与 $mask$ 按位与，如果结果不为 $0$，则说明 $x$ 和 $mask$ 中至少有一个元素的二进制表示中的某一位为 $1$，而另一个元素的二进制表示中的对应位为 $0$，这样的元素对不可能满足题目要求，因此我们需要将 $j$ 右移，直到 $x$ 和 $mask$ 按位与的结果为 $0$ 为止。

此时，我们就找到了一个满足题目要求的子数组，其长度为 $i - j + 1$，我们将其与当前的最长优雅子数组的长度进行比较，如果大于当前的最长优雅子数组的长度，则更新最长优雅子数组的长度。

然后我们将 $mask$ 和 $x$ 按位或，继续遍历下一个元素。

最终，我们得到的最长优雅子数组的长度即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = j = mask = 0
        for i, x in enumerate(nums):
            while mask & x:
                mask ^= nums[j]
                j += 1
            ans = max(ans, i - j + 1)
            mask |= x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            while ((mask & nums[i]) != 0) {
                mask ^= nums[j++];
            }
            ans = Math.max(ans, i - j + 1);
            mask |= nums[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            while (mask & nums[i]) {
                mask ^= nums[j++];
            }
            ans = max(ans, i - j + 1);
            mask |= nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubarray(nums []int) (ans int) {
	mask, j := 0, 0
	for i, x := range nums {
		for ; mask&x != 0; j++ {
			mask ^= nums[j]
		}
		if k := i - j + 1; ans < k {
			ans = k
		}
		mask |= x
	}
	return
}
```

### **TypeScript**

```ts
function longestNiceSubarray(nums: number[]): number {
    let mask = 0;
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        while ((mask & nums[i]) !== 0) {
            mask ^= nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
        mask |= nums[i];
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
