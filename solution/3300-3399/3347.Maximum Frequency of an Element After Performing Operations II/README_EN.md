---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3347.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20II/README_EN.md
rating: 2155
source: Biweekly Contest 143 Q3
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [3347. Maximum Frequency of an Element After Performing Operations II](https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii)

[中文文档](/solution/3300-3399/3347.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>k</code> and <code>numOperations</code>.</p>

<p>You must perform an <strong>operation</strong> <code>numOperations</code> times on <code>nums</code>, where in each operation you:</p>

<ul>
	<li>Select an index <code>i</code> that was <strong>not</strong> selected in any previous operations.</li>
	<li>Add an integer in the range <code>[-k, k]</code> to <code>nums[i]</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> possible <span data-keyword="frequency-array">frequency</span> of any element in <code>nums</code> after performing the <strong>operations</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,5], k = 1, numOperations = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can achieve a maximum frequency of two by:</p>

<ul>
	<li>Adding 0 to <code>nums[1]</code>, after which <code>nums</code> becomes <code>[1, 4, 5]</code>.</li>
	<li>Adding -1 to <code>nums[2]</code>, after which <code>nums</code> becomes <code>[1, 4, 4]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,11,20,20], k = 5, numOperations = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can achieve a maximum frequency of two by:</p>

<ul>
	<li>Adding 0 to <code>nums[1]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= numOperations &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

According to the problem description, for each element $x$ in the array $\textit{nums}$, we can change it to any integer within the range $[x-k, x+k]$. We want to perform operations on some elements in $\textit{nums}$ to maximize the frequency of a certain integer in the array.

The problem can be transformed into merging all elements in the interval $[x-k, x+k]$ corresponding to each element $x$, and finding the integer that contains the most original elements in the merged intervals. This can be implemented using a difference array.

We use a dictionary $d$ to record the difference array. For each element $x$, we perform the following operations on the difference array:

- Add $1$ at position $x-k$, indicating that a new interval starts from this position.
- Subtract $1$ at position $x+k+1$, indicating that an interval ends from this position.
- Add $0$ at position $x$, ensuring that position $x$ exists in the difference array for subsequent calculations.

At the same time, we need to record the number of occurrences of each element in the original array, using a dictionary $cnt$ to implement this.

Next, we perform prefix sum calculation on the difference array to get how many intervals cover each position. For each position $x$, we calculate the number of intervals covering it as $s$. Then we discuss by cases:

- If $x$ appears in the original array, operations on $x$ itself are meaningless. Therefore, there are $s - cnt[x]$ other elements that can be changed to $x$ through operations, but at most $\textit{numOperations}$ operations can be performed. So the maximum frequency at this position is $\textit{cnt}[x] + \min(s - \textit{cnt}[x], \textit{numOperations})$.
- If $x$ does not appear in the original array, then at most $\textit{numOperations}$ operations can be performed to change other elements to $x$. Therefore, the maximum frequency at this position is $\min(s, \textit{numOperations})$.

Combining the above two cases, we can uniformly express it as $\min(s, \textit{cnt}[x] + \textit{numOperations})$.

Finally, we traverse all positions, calculate the maximum frequency at each position, and take the maximum value among them as the answer.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for x in nums:
            cnt[x] += 1
            d[x] += 0
            d[x - k] += 1
            d[x + k + 1] -= 1
        ans = s = 0
        for x, t in sorted(d.items()):
            s += t
            ans = max(ans, min(s, cnt[x] + numOperations))
        return ans
```

#### Java

```java
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
            d.putIfAbsent(x, 0);
            d.merge(x - k, 1, Integer::sum);
            d.merge(x + k + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0;
        for (var e : d.entrySet()) {
            int x = e.getKey(), t = e.getValue();
            s += t;
            ans = Math.max(ans, Math.min(s, cnt.getOrDefault(x, 0) + numOperations));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        unordered_map<int, int> cnt;
        map<int, int> d;

        for (int x : nums) {
            cnt[x]++;
            d[x];
            d[x - k]++;
            d[x + k + 1]--;
        }

        int ans = 0, s = 0;
        for (const auto& [x, t] : d) {
            s += t;
            ans = max(ans, min(s, cnt[x] + numOperations));
        }

        return ans;
    }
};
```

#### Go

```go
func maxFrequency(nums []int, k int, numOperations int) (ans int) {
	cnt := make(map[int]int)
	d := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
		d[x] = d[x]
		d[x-k]++
		d[x+k+1]--
	}

	s := 0
	keys := make([]int, 0, len(d))
	for key := range d {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, x := range keys {
		s += d[x]
		ans = max(ans, min(s, cnt[x]+numOperations))
	}

	return
}
```

#### TypeScript

```ts
function maxFrequency(nums: number[], k: number, numOperations: number): number {
    const cnt: Record<number, number> = {};
    const d: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
        d[x] = d[x] || 0;
        d[x - k] = (d[x - k] || 0) + 1;
        d[x + k + 1] = (d[x + k + 1] || 0) - 1;
    }
    let [ans, s] = [0, 0];
    const keys = Object.keys(d)
        .map(Number)
        .sort((a, b) => a - b);
    for (const x of keys) {
        s += d[x];
        ans = Math.max(ans, Math.min(s, (cnt[x] || 0) + numOperations));
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, BTreeMap};

impl Solution {
    pub fn max_frequency(nums: Vec<i32>, k: i32, num_operations: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut d = BTreeMap::new();

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
            d.entry(x).or_insert(0);
            *d.entry(x - k).or_insert(0) += 1;
            *d.entry(x + k + 1).or_insert(0) -= 1;
        }

        let mut ans = 0;
        let mut s = 0;
        for (&x, &t) in d.iter() {
            s += t;
            let cur = s.min(cnt.get(&x).copied().unwrap_or(0) + num_operations);
            ans = ans.max(cur);
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxFrequency(int[] nums, int k, int numOperations) {
        var cnt = new Dictionary<int, int>();
        var d = new SortedDictionary<int, int>();

        foreach (var x in nums) {
            if (!cnt.ContainsKey(x)) {
                cnt[x] = 0;
            }
            cnt[x]++;

            if (!d.ContainsKey(x)) {
                d[x] = 0;
            }
            if (!d.ContainsKey(x - k)) {
                d[x - k] = 0;
            }
            if (!d.ContainsKey(x + k + 1)) {
                d[x + k + 1] = 0;
            }

            d[x - k] += 1;
            d[x + k + 1] -= 1;
        }

        int ans = 0, s = 0;
        foreach (var kvp in d) {
            int x = kvp.Key, t = kvp.Value;
            s += t;
            int cur = Math.Min(s, (cnt.ContainsKey(x) ? cnt[x] : 0) + numOperations);
            ans = Math.Max(ans, cur);
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
