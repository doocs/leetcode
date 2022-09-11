# [2404. 出现最频繁的偶数元素](https://leetcode.cn/problems/most-frequent-even-element)

[English Version](/solution/2400-2499/2404.Most%20Frequent%20Even%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，返回出现最频繁的偶数元素。</p>

<p>如果存在多个满足条件的元素，只需要返回 <strong>最小</strong> 的一个。如果不存在这样的元素，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,2,2,4,4,1]
<strong>输出：</strong>2
<strong>解释：</strong>
数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
返回最小的那个，即返回 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4,4,4,9,2,4]
<strong>输出：</strong>4
<strong>解释：</strong>4 是出现最频繁的偶数元素。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [29,47,21,41,13,37,25,7]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在偶数元素。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表统计所有偶数元素出现的次数，然后找出出现次数最多且值最小的偶数元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostFrequentEven(self, nums: List[int]) -> int:
        cnt = Counter(v for v in nums if v % 2 == 0)
        ans, mx = -1, 0
        for v, t in cnt.items():
            if mx < t or (mx == t and ans > v):
                mx = t
                ans = v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            if (v % 2 == 0) {
                cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            }
        }
        int ans = -1, mx = 0;
        for (var e : cnt.entrySet()) {
            int v = e.getKey(), t = e.getValue();
            if (mx < t || (mx == t && ans > v)) {
                mx = t;
                ans = v;
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
    int mostFrequentEven(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int v : nums) {
            if (v % 2 == 0) {
                ++cnt[v];
            }
        }
        int ans = -1, mx = 0;
        for (auto [v, t] : cnt) {
            if (mx < t || (mx == t && ans > v)) {
                mx = t;
                ans = v;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostFrequentEven(nums []int) int {
	cnt := map[int]int{}
	for _, v := range nums {
		if v%2 == 0 {
			cnt[v]++
		}
	}
	ans, mx := -1, 0
	for v, t := range cnt {
		if mx < t || (mx == t && ans > v) {
			mx = t
			ans = v
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function mostFrequentEven(nums: number[]): number {
    const map = new Map();
    for (const num of nums) {
        if (num % 2 === 0) {
            map.set(num, (map.get(num) ?? 0) + 1);
        }
    }
    if (map.size === 0) {
        return -1;
    }

    let res = 0;
    let max = 0;
    for (const [k, v] of map.entries()) {
        if (v > max || (v == max && k < res)) {
            max = v;
            res = k;
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn most_frequent_even(nums: Vec<i32>) -> i32 {
        let mut map = HashMap::new();
        for &num in nums.iter() {
            if num % 2 == 0 {
                *map.entry(num).or_insert(0) += 1;
            }
        }
        if map.len() == 0 {
            return -1;
        }

        let mut res = 0;
        let mut max = 0;
        for (&k, &v) in map.iter() {
            if v > max || (v == max && k < res) {
                max = v;
                res = k;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
