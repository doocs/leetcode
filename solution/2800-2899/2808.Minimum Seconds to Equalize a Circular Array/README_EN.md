# [2808. Minimum Seconds to Equalize a Circular Array](https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array)

[中文文档](/solution/2800-2899/2808.Minimum%20Seconds%20to%20Equalize%20a%20Circular%20Array/README.md)

<!-- tags:Array,Hash Table -->

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> containing <code>n</code> integers.</p>

<p>At each second, you perform the following operation on the array:</p>

<ul>
	<li>For every index <code>i</code> in the range <code>[0, n - 1]</code>, replace <code>nums[i]</code> with either <code>nums[i]</code>, <code>nums[(i - 1 + n) % n]</code>, or <code>nums[(i + 1) % n]</code>.</li>
</ul>

<p><strong>Note</strong> that all the elements get replaced simultaneously.</p>

<p>Return <em>the <strong>minimum</strong> number of seconds needed to make all elements in the array</em> <code>nums</code> <em>equal</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can equalize the array in 1 second in the following way:
- At 1<sup>st</sup> second, replace values at each index with [nums[3],nums[1],nums[3],nums[3]]. After replacement, nums = [2,2,2,2].
It can be proven that 1 second is the minimum amount of seconds needed for equalizing the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,3,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can equalize the array in 2 seconds in the following way:
- At 1<sup>st</sup> second, replace values at each index with [nums[0],nums[2],nums[2],nums[2],nums[3]]. After replacement, nums = [2,3,3,3,3].
- At 2<sup>nd</sup> second, replace values at each index with [nums[1],nums[1],nums[2],nums[3],nums[4]]. After replacement, nums = [3,3,3,3,3].
It can be proven that 2 seconds is the minimum amount of seconds needed for equalizing the array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> We don&#39;t need to perform any operations as all elements in the initial array are the same.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Enumeration

We assume that all elements eventually become $x$, and $x$ must be an element in the array.

The number $x$ can expand one bit to the left and right every second. If there are multiple identical $x$, then the time required to expand the entire array depends on the maximum distance between two adjacent $x$.

Therefore, we enumerate each element as the final $x$, calculate the maximum distance $t$ between two adjacent elements in each $x$, then the final answer is $\min\limits_{x \in nums} \left\lfloor \frac{t}{2} \right\rfloor$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minimumSeconds(self, nums: List[int]) -> int:
        d = defaultdict(list)
        for i, x in enumerate(nums):
            d[x].append(i)
        ans = inf
        n = len(nums)
        for idx in d.values():
            t = idx[0] + n - idx[-1]
            for i, j in pairwise(idx):
                t = max(t, j - i)
            ans = min(ans, t // 2)
        return ans
```

```java
class Solution {
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int ans = 1 << 30;
        for (List<Integer> idx : d.values()) {
            int m = idx.size();
            int t = idx.get(0) + n - idx.get(m - 1);
            for (int i = 1; i < m; ++i) {
                t = Math.max(t, idx.get(i) - idx.get(i - 1));
            }
            ans = Math.min(ans, t / 2);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumSeconds(vector<int>& nums) {
        unordered_map<int, vector<int>> d;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        int ans = 1 << 30;
        for (auto& [_, idx] : d) {
            int m = idx.size();
            int t = idx[0] + n - idx[m - 1];
            for (int i = 1; i < m; ++i) {
                t = max(t, idx[i] - idx[i - 1]);
            }
            ans = min(ans, t / 2);
        }
        return ans;
    }
};
```

```go
func minimumSeconds(nums []int) int {
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	ans := 1 << 30
	n := len(nums)
	for _, idx := range d {
		m := len(idx)
		t := idx[0] + n - idx[m-1]
		for i := 1; i < m; i++ {
			t = max(t, idx[i]-idx[i-1])
		}
		ans = min(ans, t/2)
	}
	return ans
}
```

```ts
function minimumSeconds(nums: number[]): number {
    const d: Map<number, number[]> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    let ans = 1 << 30;
    for (const [_, idx] of d) {
        const m = idx.length;
        let t = idx[0] + n - idx[m - 1];
        for (let i = 1; i < m; ++i) {
            t = Math.max(t, idx[i] - idx[i - 1]);
        }
        ans = Math.min(ans, t >> 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
