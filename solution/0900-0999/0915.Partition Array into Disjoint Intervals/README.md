# [915. 分割数组](https://leetcode.cn/problems/partition-array-into-disjoint-intervals)

[English Version](/solution/0900-0999/0915.Partition%20Array%20into%20Disjoint%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组&nbsp;<code>nums</code>&nbsp;，将其划分为两个连续子数组&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>，&nbsp;使得：</p>

<ul>
	<li><code>left</code>&nbsp;中的每个元素都小于或等于&nbsp;<code>right</code>&nbsp;中的每个元素。</li>
	<li><code>left</code> 和&nbsp;<code>right</code>&nbsp;都是非空的。</li>
	<li><code>left</code> 的长度要尽可能小。</li>
</ul>

<p><em>在完成这样的分组后返回&nbsp;<code>left</code>&nbsp;的&nbsp;<strong>长度&nbsp;</strong></em>。</p>

<p>用例可以保证存在这样的划分方法。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,0,3,8,6]
<strong>输出：</strong>3
<strong>解释：</strong>left = [5,0,3]，right = [8,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,0,6,12]
<strong>输出：</strong>4
<strong>解释：</strong>left = [1,1,1,0]，right = [6,12]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>可以保证至少有一种方法能够按题目所描述的那样对 <code>nums</code> 进行划分。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀最大值 + 后缀最小值**

划分后的两个子数组要满足题目要求，需要保证“数组前缀最大值”小于等于“数组后缀最小值”。

因此，我们可以先预处理出数组的后缀最小值，记录在 `mi` 数组中。

然后从前往后遍历数组，维护数组前缀的最大值 `mx`，当遍历到某个位置时，如果数组前缀最大值小于等于数组后缀最小值，那么当前位置就是划分的分界点，直接返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionDisjoint(self, nums: List[int]) -> int:
        n = len(nums)
        mi = [inf] * (n + 1)
        for i in range(n - 1, -1, -1):
            mi[i] = min(nums[i], mi[i + 1])
        mx = 0
        for i, v in enumerate(nums, 1):
            mx = max(mx, v)
            if mx <= mi[i]:
                return i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] mi = new int[n + 1];
        mi[n] = nums[n - 1];
        for (int i = n - 1; i >= 0; --i) {
            mi[i] = Math.min(nums[i], mi[i + 1]);
        }
        int mx = 0;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            mx = Math.max(mx, v);
            if (mx <= mi[i]) {
                return i;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        int n = nums.size();
        vector<int> mi(n + 1, INT_MAX);
        for (int i = n - 1; ~i; --i) mi[i] = min(nums[i], mi[i + 1]);
        int mx = 0;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            mx = max(mx, v);
            if (mx <= mi[i]) return i;
        }
        return 0;
    }
};
```

### **Go**

```go
func partitionDisjoint(nums []int) int {
	n := len(nums)
	mi := make([]int, n+1)
	mi[n] = nums[n-1]
	for i := n - 1; i >= 0; i-- {
		mi[i] = min(nums[i], mi[i+1])
	}
	mx := 0
	for i := 1; i <= n; i++ {
		v := nums[i-1]
		mx = max(mx, v)
		if mx <= mi[i] {
			return i
		}
	}
	return 0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
