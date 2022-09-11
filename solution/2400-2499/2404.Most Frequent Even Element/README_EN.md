# [2404. Most Frequent Even Element](https://leetcode.com/problems/most-frequent-even-element)

[中文文档](/solution/2400-2499/2404.Most%20Frequent%20Even%20Element/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the most frequent even element</em>.</p>

<p>If there is a tie, return the <strong>smallest</strong> one. If there is no such element, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2,4,4,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
We return the smallest one, which is 2.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,4,9,2,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 4 is the even element appears the most.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [29,47,21,41,13,37,25,7]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no even element.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
