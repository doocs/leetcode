# [453. 最小操作次数使数组元素相等](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements)

[English Version](/solution/0400-0499/0453.Minimum%20Moves%20to%20Equal%20Array%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组，每次操作将会使 <code>n - 1</code> 个元素增加 <code>1</code> 。返回让数组所有元素相等的最小操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>3
<strong>解释：</strong>
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =&gt;  [2,3,3]  =&gt;  [3,4,3]  =&gt;  [4,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>答案保证符合 <strong>32-bit</strong> 整数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

我们不妨记数组 $nums$ 的最小值为 $mi$，数组的和为 $s$，数组的长度为 $n$。

假设最小操作次数为 $k$，最终数组的所有元素都为 $x$，则有：

$$
\begin{aligned}
s + (n - 1) \times k &= n \times x \\
x &= mi + k \\
\end{aligned}
$$

将第二个式子代入第一个式子，得到：

$$
\begin{aligned}
s + (n - 1) \times k &= n \times (mi + k) \\
s + (n - 1) \times k &= n \times mi + n \times k \\
k &= s - n \times mi \\
\end{aligned}
$$

因此，最小操作次数为 $s - n \times mi$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMoves(self, nums: List[int]) -> int:
        return sum(nums) - min(nums) * len(nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMoves(int[] nums) {
        return Arrays.stream(nums).sum() - Arrays.stream(nums).min().getAsInt() * nums.length;
    }
}
```

```java
class Solution {
    public int minMoves(int[] nums) {
        int s = 0;
        int mi = 1 << 30;
        for (int x : nums) {
            s += x;
            mi = Math.min(mi, x);
        }
        return s - mi * nums.length;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int s = 0;
        int mi = 1 << 30;
        for (int x : nums) {
            s += x;
            mi = min(mi, x);
        }
        return s - mi * nums.size();
    }
};
```

### **Go**

```go
func minMoves(nums []int) int {
	mi := 1 << 30
	s := 0
	for _, x := range nums {
		s += x
		if x < mi {
			mi = x
		}
	}
	return s - mi*len(nums)
}
```

### **TypeScript**

```ts
function minMoves(nums: number[]): number {
    let mi = 1 << 30;
    let s = 0;
    for (const x of nums) {
        s += x;
        mi = Math.min(mi, x);
    }
    return s - mi * nums.length;
}
```

### **...**

```

```

<!-- tabs:end -->
