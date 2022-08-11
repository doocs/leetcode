# [1. 两数之和](https://leetcode.cn/problems/two-sum)

[English Version](/solution/0000-0099/0001.Two%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值 </strong><em><code>target</code></em>&nbsp; 的那&nbsp;<strong>两个</strong>&nbsp;整数，并返回它们的数组下标。</p>

<p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>

<p>你可以按任意顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,7,11,15], target = 9
<strong>输出：</strong>[0,1]
<strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4], target = 6
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3], target = 6
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>只会存在一个有效答案</strong></li>
</ul>

<p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表（字典）存放数组值以及对应的下标。

遍历数组，当发现 `target - nums[i]` 在哈希表中，说明找到了目标值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        m = {}
        for i, v in enumerate(nums):
            x = target - v
            if x in m:
                return [m[x], i]
            m[v] = i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            int x = target - v;
            if (m.containsKey(x)) {
                return new int[]{m.get(x), i};
            }
            m.put(v, i);
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        for (int i = 0; i < nums.size(); ++i) {
            int v = nums[i];
            int x = target - v;
            if (m.count(x)) return {m[x], i};
            m[v] = i;
        }
        return {};
    }
};
```

### **Go**

```go
func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for i, v := range nums {
		x := target - v
		if j, ok := m[x]; ok {
			return []int{j, i}
		}
		m[v] = i
	}
	return nil
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    const m = new Map();
    for (let i = 0; i < nums.length; ++i) {
        const v = nums[i];
        const x = target - v;
        if (m.has(x)) {
            return [m.get(x), i];
        }
        m.set(v, i);
    }
    return [];
};
```

### **C#**

```cs
public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var m = new Dictionary<int, int>();
        for (var i = 0; i < nums.Length; ++i)
        {
            int j;
            int v = nums[i];
            int x = target - v;
            if (m.TryGetValue(x, out j))
            {
                return new [] {j, i};
            }
            if (!m.ContainsKey(v))
            {
                m.Add(v, i);
            }

        }
        return null;
    }
}
```

### **Swift**

```swift
class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var map = [Int: Int]()
        var i = 0
        for num in nums {
            map[num] = i
            i = i + 1
        }
        i = 0
        for num in nums {
            if let otherIndex = map[target - num], otherIndex != i {
                return [i, otherIndex]
            }
            i = i + 1
        }
        return []
    }
}
```

### **Nim**

```nim
import std/enumerate

proc twoSum(nums: seq[int], target: int): seq[int] =
    var
        bal: int
        tdx: int
    for idx, val in enumerate(nums):
        bal = target - val
        if bal in nums:
            tdx = nums.find(bal)
            if idx != tdx:
                return @[idx, tdx]

```

### **Rust**

```rust
use std::collections::HashMap;

pub fn soluation(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut map = HashMap::new();
    for (i, item) in nums.iter().enumerate() {
        if map.contains_key(item) {
            return vec![i as i32, map[item]];
        } else {
            let x = target - nums[i];
            map.insert(x, i as i32);
        }
    }
    unreachable!()
}
```

### **...**

```

```

<!-- tabs:end -->
