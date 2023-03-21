# [1144. 递减元素使数组呈锯齿状](https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag)

[English Version](/solution/1100-1199/1144.Decrease%20Elements%20To%20Make%20Array%20Zigzag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，每次 <strong>操作</strong>&nbsp;会从中选择一个元素并 <strong>将该元素的值减少&nbsp;1</strong>。</p>

<p>如果符合下列情况之一，则数组&nbsp;<code>A</code>&nbsp;就是 <strong>锯齿数组</strong>：</p>

<ul>
	<li>每个偶数索引对应的元素都大于相邻的元素，即&nbsp;<code>A[0] &gt; A[1] &lt; A[2] &gt; A[3] &lt; A[4] &gt; ...</code></li>
	<li>或者，每个奇数索引对应的元素都大于相邻的元素，即&nbsp;<code>A[0] &lt; A[1] &gt; A[2] &lt; A[3] &gt; A[4] &lt; ...</code></li>
</ul>

<p>返回将数组&nbsp;<code>nums</code>&nbsp;转换为锯齿数组所需的最小操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>我们可以把 2 递减到 0，或把 3 递减到 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,6,1,6,2]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 贪心**

我们可以分别枚举偶数位和奇数位作为“比相邻元素小”的元素，然后计算需要的操作次数。取两者的最小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def movesToMakeZigzag(self, nums: List[int]) -> int:
        ans = [0, 0]
        n = len(nums)
        for i in range(2):
            for j in range(i, n, 2):
                d = 0
                if j:
                    d = max(d, nums[j] - nums[j - 1] + 1)
                if j < n - 1:
                    d = max(d, nums[j] - nums[j + 1] + 1)
                ans[i] += d
        return min(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int[] ans = new int[2];
        int n = nums.length;
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j > 0) {
                    d = Math.max(d, nums[j] - nums[j - 1] + 1);
                }
                if (j < n - 1) {
                    d = Math.max(d, nums[j] - nums[j + 1] + 1);
                }
                ans[i] += d;
            }
        }
        return Math.min(ans[0], ans[1]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int movesToMakeZigzag(vector<int>& nums) {
        vector<int> ans(2);
        int n = nums.size();
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j) d = max(d, nums[j] - nums[j - 1] + 1);
                if (j < n - 1) d = max(d, nums[j] - nums[j + 1] + 1);
                ans[i] += d;
            }
        }
        return min(ans[0], ans[1]);
    }
};
```

### **Go**

```go
func movesToMakeZigzag(nums []int) int {
	ans := [2]int{}
	n := len(nums)
	for i := 0; i < 2; i++ {
		for j := i; j < n; j += 2 {
			d := 0
			if j > 0 {
				d = max(d, nums[j]-nums[j-1]+1)
			}
			if j < n-1 {
				d = max(d, nums[j]-nums[j+1]+1)
			}
			ans[i] += d
		}
	}
	return min(ans[0], ans[1])
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

### **C#**

```cs
public class Solution {
    public int MovesToMakeZigzag(int[] nums) {
        int[] ans = new int[2];
        int n = nums.Length;
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j > 0) {
                    d = Math.Max(d, nums[j] - nums[j - 1] + 1);
                }
                if (j < n - 1) {
                    d = Math.Max(d, nums[j] - nums[j + 1] + 1);
                }
                ans[i] += d;
            }
        }
        return Math.Min(ans[0], ans[1]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
