# [548. 将数组分割成和相等的子数组](https://leetcode.cn/problems/split-array-with-equal-sum)

[English Version](/solution/0500-0599/0548.Split%20Array%20with%20Equal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个有 <code>n</code> 个整数的数组 <code>nums</code>&nbsp;，如果能找到满足以下条件的三元组&nbsp; <code>(i, j, k)</code>&nbsp; 则返回 <code>true</code> ：</p>

<ol>
	<li><code>0 &lt; i, i + 1 &lt; j, j + 1 &lt; k &lt; n - 1</code></li>
	<li>子数组 <code>(0, i - 1)</code>&nbsp;， <code>(i + 1, j - 1)</code> ， <code>(j + 1, k - 1)</code> ， <code>(k + 1, n - 1)</code> 的和应该相等。</li>
</ol>

<p>这里我们定义子数组&nbsp;<code>(l, r)</code>&nbsp;表示原数组从索引为&nbsp;<code>l</code>&nbsp;的元素开始至索引为&nbsp;<code>r</code> 的元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1:&nbsp;</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,1,2,1,2,1]
<strong>输出:</strong> True
<strong>解释:</strong>
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,1,2,1,2,1,2]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n ==&nbsp;nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 哈希表。

先求出前缀和数组 s。

然后遍历 j 所有可能的位置，对于每个 j，找出 i，使得前两个子数组的和相等。同时将和添加到哈希表中。

接着对于每个 j，找出 k，使得后两个子数组的和相等，然后判断哈希表中是否存在该和，如果存在，则找到满足条件的三元组 `(i, j, k)`，返回 true。

否则遍历结束返回 false。

时间复杂度 O(n²)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitArray(self, nums: List[int]) -> bool:
        n = len(nums)
        s = [0] * (n + 1)
        for i, v in enumerate(nums):
            s[i + 1] = s[i] + v
        for j in range(3, n - 3):
            seen = set()
            for i in range(1, j - 1):
                if s[i] == s[j] - s[i + 1]:
                    seen.add(s[i])
            for k in range(j + 2, n - 1):
                if s[n] - s[k + 1] == s[k] - s[j + 1] and s[n] - s[k + 1] in seen:
                    return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int j = 3; j < n - 3; ++j) {
            Set<Integer> seen = new HashSet<>();
            for (int i = 1; i < j - 1; ++i) {
                if (s[i] == s[j] - s[i + 1]) {
                    seen.add(s[i]);
                }
            }
            for (int k = j + 2; k < n - 1; ++k) {
                if (s[n] - s[k + 1] == s[k] - s[j + 1] && seen.contains(s[n] - s[k + 1])) {
                    return true;
                }
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
    bool splitArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        for (int j = 3; j < n - 3; ++j) {
            unordered_set<int> seen;
            for (int i = 1; i < j - 1; ++i)
                if (s[i] == s[j] - s[i + 1])
                    seen.insert(s[i]);
            for (int k = j + 2; k < n - 1; ++k)
                if (s[n] - s[k + 1] == s[k] - s[j + 1] && seen.count(s[n] - s[k + 1]))
                    return true;
        }
        return false;
    }
};
```

### **Go**

```go
func splitArray(nums []int) bool {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for j := 3; j < n-3; j++ {
		seen := map[int]bool{}
		for i := 1; i < j-1; i++ {
			if s[i] == s[j]-s[i+1] {
				seen[s[i]] = true
			}
		}
		for k := j + 2; k < n-1; k++ {
			if s[n]-s[k+1] == s[k]-s[j+1] && seen[s[n]-s[k+1]] {
				return true
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
