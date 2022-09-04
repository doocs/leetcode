# [2395. 和相等的子数组](https://leetcode.cn/problems/find-subarrays-with-equal-sum)

[English Version](/solution/2300-2399/2395.Find%20Subarrays%20With%20Equal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，判断是否存在&nbsp;<strong>两个</strong>&nbsp;长度为&nbsp;<code>2</code>&nbsp;的子数组且它们的&nbsp;<strong>和</strong>&nbsp;相等。注意，这两个子数组起始位置的下标必须&nbsp;<strong>不相同</strong>&nbsp;。</p>

<p>如果这样的子数组存在，请返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code><em>&nbsp;</em>。</p>

<p><strong>子数组</strong> 是一个数组中一段连续非空的元素组成的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,2,4]
<b>输出：</b>true
<b>解释：</b>元素为 [4,2] 和 [2,4] 的子数组有相同的和 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>false
<b>解释：</b>没有长度为 2 的两个子数组和相等。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [0,0,0]
<b>输出：</b>true
<b>解释：</b>子数组 [nums[0],nums[1]] 和 [nums[1],nums[2]] 的和相等，都为 0 。
注意即使子数组的元素相同，这两个子数组也视为不相同的子数组，因为它们在原数组中的起始位置不同。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表 `s` 记录数组相邻两元素的和。

遍历数组 `nums`，若 `s` 中存在 `nums[i] + nums[i + 1]`，则返回 `true`；否则将 `nums[i] + nums[i + 1]` 加入 `s` 中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        s = set()
        for a, b in pairwise(nums):
            if (v := a + b) in s:
                return True
            s.add(v)
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length - 1; ++i) {
            int v = nums[i] + nums[i + 1];
            if (!s.add(v)) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool findSubarrays(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = 0; i < nums.size() - 1; ++i) {
            int v = nums[i] + nums[i + 1];
            if (s.count(v)) return true;
            s.insert(v);
        }
        return false;
    }
};
```

### **Go**

```go
func findSubarrays(nums []int) bool {
	s := map[int]bool{}
	for i := 0; i < len(nums)-1; i++ {
		v := nums[i] + nums[i+1]
		if s[v] {
			return true
		}
		s[v] = true
	}
	return false
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
