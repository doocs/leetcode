# [1546. 和为目标值且不重叠的非空子数组的最大数目](https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target)

[English Version](/solution/1500-1599/1546.Maximum%20Number%20of%20Non-Overlapping%20Subarrays%20With%20Sum%20Equals%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>target</code>&nbsp;。</p>

<p>请你返回&nbsp;<strong>非空不重叠</strong>&nbsp;子数组的最大数目，且每个子数组中数字和都为 <code>target</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1,1], target = 2
<strong>输出：</strong>2
<strong>解释：</strong>总共有 2 个不重叠子数组（加粗数字表示） [<strong>1,1</strong>,1,<strong>1,1</strong>] ，它们的和为目标值 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-1,3,5,1,4,2,-9], target = 6
<strong>输出：</strong>2
<strong>解释：</strong>总共有 3 个子数组和为 6 。
([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [-2,6,6,3,5,4,1,2,8], target = 10
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,0], target = 0
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;=&nbsp;10^5</code></li>
	<li><code>-10^4 &lt;= nums[i] &lt;=&nbsp;10^4</code></li>
	<li><code>0 &lt;= target &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心 + 前缀和。ans 表示结果，初始值为 0。

贪心：当我们发现以下标 i 结尾的子数组和为 target 时，ans++，然后继续往后查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxNonOverlapping(self, nums: List[int], target: int) -> int:
        i, n = 0, len(nums)
        ans = 0
        while i < n:
            s = 0
            seen = {0}
            while i < n:
                s += nums[i]
                if s - target in seen:
                    ans += 1
                    break
                i += 1
                seen.add(s)
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int i = 0, n = nums.length;
        int ans = 0;
        while (i < n) {
            int s = 0;
            Set<Integer> seen = new HashSet<>();
            seen.add(0);
            while (i < n) {
                s += nums[i];
                if (seen.contains(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.add(s);
            }
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        int i = 0, n = nums.size();
        int ans = 0;
        while (i < n) {
            int s = 0;
            unordered_set<int> seen;
            seen.insert(0);
            while (i < n) {
                s += nums[i];
                if (seen.count(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.insert(s);
            }
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNonOverlapping(nums []int, target int) int {
	i, n, ans := 0, len(nums), 0
	for i < n {
		s := 0
		seen := map[int]bool{0: true}
		for i < n {
			s += nums[i]
			if seen[s-target] {
				ans++
				break
			}
			seen[s] = true
			i++
		}
		i++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
