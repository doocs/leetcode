# [219. 存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii)

[English Version](/solution/0200-0299/0219.Contains%20Duplicate%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组和一个整数&nbsp;<em>k</em>，判断数组中是否存在两个不同的索引<em>&nbsp;i</em>&nbsp;和<em>&nbsp;j</em>，使得&nbsp;<strong>nums [i] = nums [j]</strong>，并且 <em>i</em> 和 <em>j</em>&nbsp;的差的 <strong>绝对值</strong> 至多为 <em>k</em>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> nums = [1,2,3,1], k<em> </em>= 3
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1
<strong>输出:</strong> true</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入: </strong>nums = [1,2,3,1,2,3], k<em> </em>=<em> </em>2
<strong>输出:</strong> false</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        mp = {}
        for i, v in enumerate(nums):
            if v in mp and i - mp[v] <= k:
                return True
            mp[v] = i
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (mp.containsKey(nums[i]) && i - mp.get(nums[i]) <= k) {
                return true;
            }
            mp.put(nums[i], i);
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        for (int i = 0; i < nums.size(); ++i)
        {
            if (mp.count(nums[i]) && i - mp[nums[i]] <= k) return true;
            mp[nums[i]] = i;
        }
        return false;
    }
};
```

### **Go**

```go
func containsNearbyDuplicate(nums []int, k int) bool {
	mp := make(map[int]int)
	for i, v := range nums {
		if j, ok := mp[v]; ok {
			if i-j <= k {
				return true
			}
		}
		mp[v] = i
	}
	return false
}
```

### **C#**

```cs
public class Solution {
    public bool ContainsNearbyDuplicate(int[] nums, int k) {
        var mp = new Dictionary<int, int>();
        for (int i = 0; i < nums.Length; ++i)
        {
            if (mp.ContainsKey(nums[i]) && i - mp[nums[i]] <= k)
            {
                return true;
            }
            mp[nums[i]] = i;
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
