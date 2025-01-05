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

### Solution 1

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
