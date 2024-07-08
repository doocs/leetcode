---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3209.Number%20of%20Subarrays%20With%20AND%20Value%20of%20K/README_EN.md
---

<!-- problem:start -->

# [3209. Number of Subarrays With AND Value of K](https://leetcode.com/problems/number-of-subarrays-with-and-value-of-k)

[中文文档](/solution/3200-3299/3209.Number%20of%20Subarrays%20With%20AND%20Value%20of%20K/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return the number of <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code> where the bitwise <code>AND</code> of the elements of the subarray equals <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>All subarrays contain only 1&#39;s.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays having an <code>AND</code> value of 1 are: <code>[<u><strong>1</strong></u>,1,2]</code>, <code>[1,<u><strong>1</strong></u>,2]</code>, <code>[<u><strong>1,1</strong></u>,2]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays having an <code>AND</code> value of 2 are: <code>[1,<b><u>2</u></b>,3]</code>, <code>[1,<u><strong>2,3</strong></u>]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

According to the problem description, we need to find the result of the bitwise AND operation of elements from index $l$ to $r$ in the array $\textit{nums}$, that is, $\textit{nums}[l] \land \textit{nums}[l + 1] \land \cdots \land \textit{nums}[r]$, where $\land$ represents the bitwise AND operation.

If we fix the right endpoint $r$, then the range of the left endpoint $l$ is $[0, r]$. Since the sum of bitwise AND decreases monotonically as $l$ decreases, and the value of $nums[i]$ does not exceed $10^9$, the interval $[0, r]$ can have at most $30$ different values. Therefore, we can use a set to maintain all the values of $\textit{nums}[l] \land \textit{nums}[l + 1] \land \cdots \land \textit{nums}[r]$ and the number of times these values occur.

When we traverse from $r$ to $r+1$, the values with $r+1$ as the right endpoint are the values obtained by performing the bitwise AND operation of each value in the set with $nums[r + 1]$, plus $\textit{nums}[r + 1]$ itself.

Therefore, we only need to enumerate each value in the set and perform the bitwise AND operation with $\textit{nums[r]}$ to get all the values and their occurrences with $r$ as the right endpoint. Then, we need to add the occurrence count of $\textit{nums[r]}$. At this point, we add the occurrence count of value $k$ to the answer. Continue traversing $r$ until the entire array is traversed.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $n$ and $M$ are the length of the array $\textit{nums}$ and the maximum value in the array $\textit{nums}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = 0
        pre = Counter()
        for x in nums:
            cur = Counter()
            for y, v in pre.items():
                cur[x & y] += v
            cur[x] += 1
            ans += cur[k]
            pre = cur
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> pre = new HashMap<>();
        for (int x : nums) {
            Map<Integer, Integer> cur = new HashMap<>();
            for (var e : pre.entrySet()) {
                int y = e.getKey(), v = e.getValue();
                cur.merge(x & y, v, Integer::sum);
            }
            cur.merge(x, 1, Integer::sum);
            ans += cur.getOrDefault(k, 0);
            pre = cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int k) {
        long long ans = 0;
        unordered_map<int, int> pre;
        for (int x : nums) {
            unordered_map<int, int> cur;
            for (auto& [y, v] : pre) {
                cur[x & y] += v;
            }
            cur[x]++;
            ans += cur[k];
            pre = cur;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, k int) (ans int64) {
	pre := map[int]int{}
	for _, x := range nums {
		cur := map[int]int{}
		for y, v := range pre {
			cur[x&y] += v
		}
		cur[x]++
		ans += int64(cur[k])
		pre = cur
	}
	return
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], k: number): number {
    let ans = 0;
    let pre = new Map<number, number>();
    for (const x of nums) {
        const cur = new Map<number, number>();
        for (const [y, v] of pre) {
            const z = x & y;
            cur.set(z, (cur.get(z) || 0) + v);
        }
        cur.set(x, (cur.get(x) || 0) + 1);
        ans += cur.get(k) || 0;
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
