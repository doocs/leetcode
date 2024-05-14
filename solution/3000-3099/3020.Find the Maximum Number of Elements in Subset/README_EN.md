---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README_EN.md
rating: 1741
tags:
    - Array
    - Hash Table
    - Enumeration
---

# [3020. Find the Maximum Number of Elements in Subset](https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset)

[中文文档](/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README.md)

## Description

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>You need to select a <span data-keyword="subset">subset</span> of <code>nums</code> which satisfies the following condition:</p>

<ul>
	<li>You can place the selected elements in a <strong>0-indexed</strong> array such that it follows the pattern: <code>[x, x<sup>2</sup>, x<sup>4</sup>, ..., x<sup>k/2</sup>, x<sup>k</sup>, x<sup>k/2</sup>, ..., x<sup>4</sup>, x<sup>2</sup>, x]</code> (<strong>Note</strong> that <code>k</code> can be be any <strong>non-negative</strong> power of <code>2</code>). For example, <code>[2, 4, 16, 4, 2]</code> and <code>[3, 9, 3]</code> follow the pattern while <code>[2, 4, 8, 4, 2]</code> does not.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of elements in a subset that satisfies these conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,1,2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 2<sup>2</sup> == 4. Hence the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Enumeration

We use a hash table $cnt$ to record the occurrence count of each element in the array $nums$. For each element $x$, we can keep squaring it until its count in the hash table $cnt$ is less than $2$. At this point, we check if the count of $x$ in the hash table $cnt$ is $1$. If it is, it means that $x$ can still be included in the subset. Otherwise, we need to remove an element from the subset to ensure the subset count is odd. Then we update the answer and continue to enumerate the next element.

Note that we need to handle the case of $x = 1$ specially.

The time complexity is $O(n \times \log \log M)$, and the space complexity is $O(n)$. Here, $n$ and $M$ are the length of the array $nums$ and the maximum value in the array $nums$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        ans = cnt[1] - (cnt[1] % 2 ^ 1)
        del cnt[1]
        for x in cnt:
            t = 0
            while cnt[x] > 1:
                x = x * x
                t += 2
            t += 1 if cnt[x] else -1
            ans = max(ans, t)
        return ans
```

```java
class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge((long) x, 1, Integer::sum);
        }
        Integer t = cnt.remove(1L);
        int ans = t == null ? 0 : t - (t % 2 ^ 1);
        for (long x : cnt.keySet()) {
            t = 0;
            while (cnt.getOrDefault(x, 0) > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.getOrDefault(x, -1);
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = cnt[1] - (cnt[1] % 2 ^ 1);
        cnt.erase(1);
        for (auto [v, _] : cnt) {
            int t = 0;
            long long x = v;
            while (cnt.count(x) && cnt[x] > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.count(x) ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```go
func maximumLength(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	ans = cnt[1] - (cnt[1]%2 ^ 1)
	delete(cnt, 1)
	for x := range cnt {
		t := 0
		for cnt[x] > 1 {
			x = x * x
			t += 2
		}
		if cnt[x] > 0 {
			t += 1
		} else {
			t -= 1
		}
		ans = max(ans, t)
	}
	return
}
```

```ts
function maximumLength(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    let ans = cnt.has(1) ? cnt.get(1)! - (cnt.get(1)! % 2 ^ 1) : 0;
    cnt.delete(1);
    for (let [x, _] of cnt) {
        let t = 0;
        while (cnt.has(x) && cnt.get(x)! > 1) {
            x = x * x;
            t += 2;
        }
        t += cnt.has(x) ? 1 : -1;
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
