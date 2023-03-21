# [1121. 将数组分成几个递增序列](https://leetcode.cn/problems/divide-array-into-increasing-sequences)

[English Version](/solution/1100-1199/1121.Divide%20Array%20Into%20Increasing%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>非递减</strong> 的正整数数组&nbsp;<code>nums</code>&nbsp;和整数&nbsp;<code>K</code>，判断该数组是否可以被分成一个或几个&nbsp;<strong>长度至少&nbsp;为 </strong><code>K</code><strong> 的 不相交的递增子序列</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,2,3,3,4,4], K = 3
<strong>输出：</strong>true
<strong>解释：</strong>
该数组可以分成两个子序列 [1,2,3,4] 和 [2,3,4]，每个子序列的长度都至少是 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,6,6,7,8], K = 3
<strong>输出：</strong>false
<strong>解释：</strong>
没有办法根据条件来划分数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 10^5</code></li>
	<li><code>1 &lt;= K &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^5</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

我们假设可以将数组分成 $m$ 个长度至少为 $k$ 的严格递增子序列，如果数组中出现次数最多的数字的个数为 $cnt$，那么这 $cnt$ 个数字必须在不同的子序列中，所以 $m \geq cnt$，又因为 $m$ 个子序列的长度至少为 $k$，因此，子序列的个数越少越好，所以 $m = cnt$。那么 $cnt \times k \leq n$，才能满足题意。因此，我们只需要统计数组中出现次数最多的数字的个数 $cnt$，然后判断 $cnt \times k \leq n$ 即可。如果是，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canDivideIntoSubsequences(self, nums: List[int], k: int) -> bool:
        mx = max(len(list(x)) for _, x in groupby(nums))
        return mx * k <= len(nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, cnt.merge(x, 1, Integer::sum));
        }
        return mx * k <= nums.length;
    }
}
```

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.length) {
                return false;
            }
            a = b;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canDivideIntoSubsequences(vector<int>& nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int& b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.size()) {
                return false;
            }
            a = b;
        }
        return true;
    }
};
```

### **Go**

```go
func canDivideIntoSubsequences(nums []int, k int) bool {
	cnt, a := 0, 0
	for _, b := range nums {
		cnt++
		if a != b {
			cnt = 1
		}
		if cnt*k > len(nums) {
			return false
		}
		a = b
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
