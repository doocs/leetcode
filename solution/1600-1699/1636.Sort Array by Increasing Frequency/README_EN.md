# [1636. Sort Array by Increasing Frequency](https://leetcode.com/problems/sort-array-by-increasing-frequency)

[中文文档](/solution/1600-1699/1636.Sort%20Array%20by%20Increasing%20Frequency/README.md)

## Description

<p>Given an array of integers <code>nums</code>, sort the array in <strong>increasing</strong> order based on the frequency of the values. If multiple values have the same frequency, sort them in <strong>decreasing</strong> order.</p>

<p>Return the <em>sorted array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,2,3]
<strong>Output:</strong> [3,1,1,2,2,2]
<strong>Explanation:</strong> &#39;3&#39; has a frequency of 1, &#39;1&#39; has a frequency of 2, and &#39;2&#39; has a frequency of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,3,2]
<strong>Output:</strong> [1,3,3,2,2]
<strong>Explanation:</strong> &#39;2&#39; and &#39;3&#39; both have a frequency of 2, so they are sorted in decreasing order.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1,-6,4,5,-6,1,4,1]
<strong>Output:</strong> [5,-1,4,4,-6,-6,1,1,1]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def frequencySort(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return sorted(nums, key=lambda x: (cnt[x], -x))
```

### **Java**

```java
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        List<Integer> t = new ArrayList<>();
        for (int v : nums) {
            v += 100;
            ++cnt[v];
            t.add(v);
        }
        t.sort((a, b) -> cnt[a] == cnt[b] ? b - a : cnt[a] - cnt[b]);
        int[] ans = new int[nums.length];
        int i = 0;
        for (int v : t) {
            ans[i++] = v - 100;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> frequencySort(vector<int>& nums) {
        vector<int> cnt(201);
        for (int v : nums) {
            ++cnt[v + 100];
        }
        sort(nums.begin(), nums.end(), [&](const int a, const int b) {
            if (cnt[a + 100] == cnt[b + 100]) return a > b;
            return cnt[a + 100] < cnt[b + 100];
        });
        return nums;
    }
};
```

### **Go**

```go
func frequencySort(nums []int) []int {
	cnt := make([]int, 201)
	for _, v := range nums {
		cnt[v+100]++
	}
	sort.Slice(nums, func(i, j int) bool {
		a, b := nums[i]+100, nums[j]+100
		return cnt[a] < cnt[b] || cnt[a] == cnt[b] && a > b
	})
	return nums
}
```

### **TypeScript**

```ts
function frequencySort(nums: number[]): number[] {
    const map = new Map<number, number>();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }
    return nums.sort((a, b) => map.get(a) - map.get(b) || b - a);
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut map = HashMap::new();
        for &num in nums.iter() {
            *map.entry(num).or_insert(0) += 1;
        }
        nums.sort_by(|a, b| {
            if map.get(a) == map.get(b) {
                return b.cmp(a);
            }
            map.get(a).cmp(&map.get(b))
        });
        nums
    }
}
```

### **...**

```

```

<!-- tabs:end -->
