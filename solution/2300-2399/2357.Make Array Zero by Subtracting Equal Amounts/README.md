# [2357. 使数组中所有元素都等于零](https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts)

[English Version](/solution/2300-2399/2357.Make%20Array%20Zero%20by%20Subtracting%20Equal%20Amounts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数数组 <code>nums</code> 。在一步操作中，你必须：</p>

<ul>
	<li>选出一个正整数 <code>x</code> ，<code>x</code> 需要小于或等于 <code>nums</code> 中 <strong>最小</strong> 的 <strong>非零</strong> 元素。</li>
	<li><code>nums</code> 中的每个正整数都减去 <code>x</code>。</li>
</ul>

<p>返回使 <code>nums</code> 中所有元素都等于<em> </em><code>0</code> 需要的 <strong>最少</strong> 操作数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,0,3,5]
<strong>输出：</strong>3
<strong>解释：</strong>
第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>0
<strong>解释：</strong>nums 中的每个元素都已经是 0 ，所以不需要执行任何操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表或数组**

我们观察到，每一次操作，都可以把数组 `nums` 中相同且非零的元素减少到 $0$，因此，我们只需要统计数组 `nums` 中有多少个不同的非零元素，即为最少操作数。统计不同的非零元素，可以使用哈希表或数组来实现。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        return len({x for x in nums if x})
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumOperations(int[] nums) {
        boolean[] s = new boolean[101];
        s[0] = true;
        int ans = 0;
        for (int x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
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
    int minimumOperations(vector<int>& nums) {
        bool s[101]{};
        s[0] = true;
        int ans = 0;
        for (int& x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumOperations(nums []int) (ans int) {
	s := [101]bool{true}
	for _, x := range nums {
		if !s[x] {
			s[x] = true
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function minimumOperations(nums: number[]): number {
    const set = new Set(nums);
    set.delete(0);
    return set.size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        let mut set = nums.iter().collect::<HashSet<&i32>>();
        set.remove(&0);
        set.len() as i32
    }
}
```

### **C**

```c
int minimumOperations(int *nums, int numsSize) {
    int vis[101] = {0};
    vis[0] = 1;
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        if (vis[nums[i]]) {
            continue;
        }
        vis[nums[i]] = 1;
        ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
