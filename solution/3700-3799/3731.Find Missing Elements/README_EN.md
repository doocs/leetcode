---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3731.Find%20Missing%20Elements/README_EN.md
---

<!-- problem:start -->

# [3731. Find Missing Elements](https://leetcode.com/problems/find-missing-elements)

[中文文档](/solution/3700-3799/3731.Find%20Missing%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> consisting of <strong>unique</strong> integers.</p>

<p>Originally, <code>nums</code> contained <strong>every integer</strong> within a certain range. However, some integers might have gone <strong>missing</strong> from the array.</p>

<p>The <strong>smallest</strong> and <strong>largest</strong> integers of the original range are still present in <code>nums</code>.</p>

<p>Return a <strong>sorted</strong> list of all the missing integers in this range. If no integers are missing, return an <strong>empty</strong> list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The smallest integer is 1 and the largest is 5, so the full range should be <code>[1,2,3,4,5]</code>. Among these, only 3 is missing.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,8,6,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>The smallest integer is 6 and the largest is 9, so the full range is <code>[6,7,8,9]</code>. All integers are already present, so no integer is missing.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<p>The smallest integer is 1 and the largest is 5, so the full range should be <code>[1,2,3,4,5]</code>. The missing integers are 2, 3, and 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We first find the minimum and maximum values in the array $\textit{nums}$, denoted as $\textit{mn}$ and $\textit{mx}$. Then we use a hash table to store all elements in the array $\textit{nums}$.

Next, we iterate through the interval $[\textit{mn} + 1, \textit{mx} - 1]$. For each integer $x$, if $x$ is not in the hash table, we add it to the answer list.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        mn, mx = min(nums), max(nums)
        s = set(nums)
        return [x for x in range(mn + 1, mx) if x not in s]
```

#### Java

```java
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int mn = 100, mx = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            s.add(x);
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = mn + 1; x < mx; ++x) {
            if (!s.contains(x)) {
                ans.add(x);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findMissingElements(vector<int>& nums) {
        int mn = 100, mx = 0;
        unordered_set<int> s;
        for (int x : nums) {
            mn = min(mn, x);
            mx = max(mx, x);
            s.insert(x);
        }
        vector<int> ans;
        for (int x = mn + 1; x < mx; ++x) {
            if (!s.count(x)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMissingElements(nums []int) (ans []int) {
	mn, mx := 100, 0
	s := make(map[int]bool)
	for _, x := range nums {
        mn = min(mn, x)
        mx = max(mx, x)
		s[x] = true
	}
	for x := mn + 1; x < mx; x++ {
		if !s[x] {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findMissingElements(nums: number[]): number[] {
    let [mn, mx] = [100, 0];
    const s = new Set<number>();
    for (const x of nums) {
        mn = Math.min(mn, x);
        mx = Math.max(mx, x);
        s.add(x);
    }
    const ans: number[] = [];
    for (let x = mn + 1; x < mx; ++x) {
        if (!s.has(x)) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
