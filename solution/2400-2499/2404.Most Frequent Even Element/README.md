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

我们用哈希表 $cnt$ 统计所有偶数元素出现的次数，然后找出出现次数最多且值最小的偶数元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostFrequentEven(self, nums: List[int]) -> int:
        cnt = Counter(x for x in nums if x % 2 == 0)
        ans, mx = -1, 0
        for x, v in cnt.items():
            if v > mx or (v == mx and ans > x):
                ans, mx = x, v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            if (x % 2 == 0) {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        int ans = -1, mx = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (mx < v || (mx == v && ans > x)) {
                ans = x;
                mx = v;
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
        for (int x : nums) {
            if (x % 2 == 0) {
                ++cnt[x];
            }
        }
        int ans = -1, mx = 0;
        for (auto& [x, v] : cnt) {
            if (mx < v || (mx == v && ans > x)) {
                ans = x;
                mx = v;
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
	for _, x := range nums {
		if x%2 == 0 {
			cnt[x]++
		}
	}
	ans, mx := -1, 0
	for x, v := range cnt {
		if mx < v || (mx == v && x < ans) {
			ans, mx = x, v
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function mostFrequentEven(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        if (x % 2 === 0) {
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
        }
    }
    let ans = -1;
    let mx = 0;
    for (const [x, v] of cnt) {
        if (mx < v || (mx === v && ans > x)) {
            ans = x;
            mx = v;
        }
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn most_frequent_even(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for &x in nums.iter() {
            if x % 2 == 0 {
                *cnt.entry(x).or_insert(0) += 1;
            }
        }
        let mut ans = -1;
        let mut mx = 0;
        for (&x, &v) in cnt.iter() {
            if mx < v || (mx == v && ans > x) {
                ans = x;
                mx = v;
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
