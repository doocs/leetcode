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

我们可以遍历数组 $nums$，用哈希表 $vis$ 记录数组中每两个相邻元素的和，如果当前两个元素的和已经在哈希表中出现过，则返回 `true`，否则将当前两个元素的和加入哈希表中。

遍历结束后，说明没有找到满足条件的两个子数组，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        vis = set()
        for a, b in pairwise(nums):
            if (x := a + b) in vis:
                return True
            vis.add(x)
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < nums.length; ++i) {
            if (!vis.add(nums[i - 1] + nums[i])) {
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
        unordered_set<int> vis;
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i - 1] + nums[i];
            if (vis.count(x)) {
                return true;
            }
            vis.insert(x);
        }
        return false;
    }
};
```

### **Go**

```go
func findSubarrays(nums []int) bool {
	vis := map[int]bool{}
	for i, b := range nums[1:] {
		x := nums[i] + b
		if vis[x] {
			return true
		}
		vis[x] = true
	}
	return false
}
```

### **TypeScript**

```ts
function findSubarrays(nums: number[]): boolean {
    const vis: Set<number> = new Set<number>();
    for (let i = 1; i < nums.length; ++i) {
        const x = nums[i - 1] + nums[i];
        if (vis.has(x)) {
            return true;
        }
        vis.add(x);
    }
    return false;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_subarrays(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut set = HashSet::new();
        for i in 1..n {
            if !set.insert(nums[i - 1] + nums[i]) {
                return true;
            }
        }
        false
    }
}
```

### **C**

```c
bool findSubarrays(int *nums, int numsSize) {
    for (int i = 1; i < numsSize - 1; i++) {
        for (int j = i + 1; j < numsSize; j++) {
            if (nums[i - 1] + nums[i] == nums[j - 1] + nums[j]) {
                return true;
            }
        }
    }
    return false;
}
```

### **...**

```


```

<!-- tabs:end -->
