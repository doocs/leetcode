# [219. 存在重复元素 II](https://leetcode.cn/problems/contains-duplicate-ii)

[English Version](/solution/0200-0299/0219.Contains%20Duplicate%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code> ，判断数组中是否存在两个 <strong>不同的索引</strong><em>&nbsp;</em><code>i</code>&nbsp;和<em>&nbsp;</em><code>j</code> ，满足 <code>nums[i] == nums[j]</code> 且 <code>abs(i - j) &lt;= k</code> 。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3
<strong>输出：</strong>true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1
<strong>输出：</strong>true</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1,2,3], k<em> </em>=<em> </em>2
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用哈希表 $d$ 存放最近遍历到的数以及对应的下标。

遍历数组 `nums`，对于当前遍历到的元素 $nums[i]$，如果在哈希表中存在，并且下标与当前元素的下标之差不超过 $k$，则返回 `true`，否则将当前元素加入哈希表中。

遍历结束后，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        d = {}
        for i, x in enumerate(nums):
            if x in d and i - d[x] <= k:
                return True
            d[x] = i
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (i - d.getOrDefault(nums[i], -1000000) <= k) {
                return true;
            }
            d.put(nums[i], i);
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
        unordered_map<int, int> d;
        for (int i = 0; i < nums.size(); ++i) {
            if (d.count(nums[i]) && i - d[nums[i]] <= k) {
                return true;
            }
            d[nums[i]] = i;
        }
        return false;
    }
};
```

### **Go**

```go
func containsNearbyDuplicate(nums []int, k int) bool {
	d := map[int]int{}
	for i, x := range nums {
		if j, ok := d[x]; ok && i-j <= k {
			return true
		}
		d[x] = i
	}
	return false
}
```

### **C#**

```cs
public class Solution {
    public bool ContainsNearbyDuplicate(int[] nums, int k) {
        var d = new Dictionary<int, int>();
        for (int i = 0; i < nums.Length; ++i) {
            if (d.ContainsKey(nums[i]) && i - d[nums[i]] <= k) {
                return true;
            }
            d[nums[i]] = i;
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function containsNearbyDuplicate(nums: number[], k: number): boolean {
    const d: Map<number, number> = new Map();
    for (let i = 0; i < nums.length; ++i) {
        if (d.has(nums[i]) && i - d.get(nums[i])! <= k) {
            return true;
        }
        d.set(nums[i], i);
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
