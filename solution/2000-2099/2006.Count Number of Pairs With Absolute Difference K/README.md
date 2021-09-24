# [2006. 差的绝对值为 K 的数对数目](https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k)

[English Version](/solution/2000-2099/2006.Count%20Number%20of%20Pairs%20With%20Absolute%20Difference%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回数对&nbsp;<code>(i, j)</code>&nbsp;的数目，满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>|nums[i] - nums[j]| == k</code>&nbsp;。</p>

<p><code>|x|</code>&nbsp;的值定义为：</p>

<ul>
	<li>如果&nbsp;<code>x &gt;= 0</code>&nbsp;，那么值为&nbsp;<code>x</code>&nbsp;。</li>
	<li>如果&nbsp;<code>x &lt; 0</code>&nbsp;，那么值为&nbsp;<code>-x</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,2,1], k = 1
<b>输出：</b>4
<b>解释：</b>差的绝对值为 1 的数对为：
- [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,1]
- [<em><strong>1</strong></em>,2,<em><strong>2</strong></em>,1]
- [1,<em><strong>2</strong></em>,2,<em><strong>1</strong></em>]
- [1,2,<em><strong>2</strong></em>,<em><strong>1</strong></em>]
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3], k = 3
<b>输出：</b>0
<b>解释：</b>没有任何数对差的绝对值为 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [3,2,1,5,4], k = 2
<b>输出：</b>3
<b>解释：</b>差的绝对值为 2 的数对为：
- [<em><strong>3</strong></em>,2,<em><strong>1</strong></em>,5,4]
- [<em><strong>3</strong></em>,2,1,<em><strong>5</strong></em>,4]
- [3,<em><strong>2</strong></em>,1,5,<em><strong>4</strong></em>]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 99</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于 nums 长度范围是 `[1,200]`，故可以直接双重循环遍历计数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countKDifference(self, nums: List[int], k: int) -> int:
        n = len(nums)
        res = 0
        for i in range(n):
            for j in range(i + 1, n):
                if abs(nums[i] - nums[j]) == k:
                    res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++res;
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countKDifference(vector<int>& nums, int k) {
        int n = nums.size();
        int res = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (abs(nums[i] - nums[j]) == k) ++ res;
        return res;
    }
};
```

### **Go**

```go
func countKDifference(nums []int, k int) int {
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if abs(nums[i]-nums[j]) == k {
				res++
			}
		}
	}
	return res
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
