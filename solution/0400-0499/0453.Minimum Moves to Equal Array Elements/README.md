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

定义 s 表示数组元素之和，mi 表示数组中最小的元素，n 表示数组的长度，经过最小的 k 次操作过后，每个元素都变成 v。

那么：

```
k * (n - 1) + s = v * n     ①
```

实际上，v 与 mi 存在着这样的关系：

```
v = mi + k                  ②
```

这是因为，最小的数每次都会被增加，直至变成 v。因此，如果最终数字是 v，那么操作的次数 `k = v - mi`。

整合 ①②，可得

```
k = s - mi * n
```

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
        int mi = Integer.MAX_VALUE;
        for (int num : nums) {
            s += num;
            mi = Math.min(mi, num);
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
        int mi = INT_MAX;
        for (int num : nums) {
            s += num;
            mi = min(mi, num);
        }
        return s - mi * nums.size();
    }
};
```

### **Go**

```go
func minMoves(nums []int) int {
	mi := math.MaxInt32
	s := 0
	for _, num := range nums {
		s += num
		if num < mi {
			mi = num
		}
	}
	return s - mi*len(nums)

}
```

### **...**

```

```

<!-- tabs:end -->
