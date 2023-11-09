# [2441. 与对应负数同时存在的最大正整数](https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative)

[English Version](/solution/2400-2499/2441.Largest%20Positive%20Integer%20That%20Exists%20With%20Its%20Negative/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>不包含</strong> 任何零的整数数组 <code>nums</code> ，找出自身与对应的负数都在数组中存在的最大正整数 <code>k</code> 。</p>

<p>返回正整数<em> </em><code>k</code> ，如果不存在这样的整数，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,2,-3,3]
<strong>输出：</strong>3
<strong>解释：</strong>3 是数组中唯一一个满足题目要求的 k 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,10,6,7,-7,1]
<strong>输出：</strong>7
<strong>解释：</strong>数组中存在 1 和 7 对应的负数，7 的值更大。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-10,8,6,7,-2,-3]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在满足题目要求的 k ，返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以用哈希表 $s$ 记录数组中出现的所有元素，用一个变量 $ans$ 记录满足题目要求的最大正整数，初始时 $ans = -1$。

接下来，我们遍历哈希表 $s$ 中的每个元素 $x$，如果 $s$ 中存在 $-x$，那么我们就更新 $ans = \max(ans, x)$。

遍历结束后，返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        s = set(nums)
        return max((x for x in s if -x in s), default=-1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int x : s) {
            if (s.contains(-x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaxK(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = -1;
        for (int x : s) {
            if (s.count(-x)) {
                ans = max(ans, x);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findMaxK(nums []int) int {
	ans := -1
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x := range s {
		if s[-x] && ans < x {
			ans = x
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findMaxK(nums: number[]): number {
    let ans = -1;
    const s = new Set(nums);
    for (const x of s) {
        if (s.has(-x)) {
            ans = Math.max(ans, x);
        }
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let s = nums.into_iter().collect::<HashSet<i32>>();
        let mut ans = -1;
        for &x in s.iter() {
            if s.contains(&-x) {
                ans = ans.max(x);
            }
        }
        ans
    }
}
```

```rust
use std::collections::HashSet;

impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        let mut h = HashSet::new();

        for &n in &nums {
            h.insert(n);
        }

        for &n in &nums {
            if h.contains(&-n) && n > ans {
                ans = n;
            }
        }

        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
