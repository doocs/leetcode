# [2404. Most Frequent Even Element](https://leetcode.com/problems/most-frequent-even-element)

[中文文档](/solution/2400-2499/2404.Most%20Frequent%20Even%20Element/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the most frequent even element</em>.</p>

<p>If there is a tie, return the <strong>smallest</strong> one. If there is no such element, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2,4,4,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
We return the smallest one, which is 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,4,9,2,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 4 is the even element appears the most.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        cnt = Counter(x for x in nums if x % 2 == 0)
        ans, mx = -1, 0
        for x, v in cnt.items():
            if v > mx or (v == mx and ans > x):
                ans, mx = x, v
        return ans
```

### **Java**

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
