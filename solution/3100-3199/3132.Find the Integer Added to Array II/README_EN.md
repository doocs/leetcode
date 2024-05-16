---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3132.Find%20the%20Integer%20Added%20to%20Array%20II/README_EN.md
rating: 1620
source: Weekly Contest 395 Q2
tags:
    - Array
    - Two Pointers
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [3132. Find the Integer Added to Array II](https://leetcode.com/problems/find-the-integer-added-to-array-ii)

[中文文档](/solution/3100-3199/3132.Find%20the%20Integer%20Added%20to%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>.</p>

<p>From <code>nums1</code> two elements have been removed, and all other elements have been increased (or decreased in the case of negative) by an integer, represented by the variable <code>x</code>.</p>

<p>As a result, <code>nums1</code> becomes <strong>equal</strong> to <code>nums2</code>. Two arrays are considered <strong>equal</strong> when they contain the same integers with the same frequencies.</p>

<p>Return the <strong>minimum</strong> possible integer<em> </em><code>x</code><em> </em>that achieves this equivalence.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [4,20,16,12,8], nums2 = [14,18,10]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">-2</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing elements at indices <code>[0,4]</code> and adding -2, <code>nums1</code> becomes <code>[18,14,10]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [3,5,5,3], nums2 = [7,7]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing elements at indices <code>[0,3]</code> and adding 2, <code>nums1</code> becomes <code>[7,7]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums1.length &lt;= 200</code></li>
	<li><code>nums2.length == nums1.length - 2</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
	<li>The test cases are generated in a way that there is an integer <code>x</code> such that <code>nums1</code> can become equal to <code>nums2</code> by removing two elements and adding <code>x</code> to each element of <code>nums1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Enumeration + Two Pointers

First, we sort the arrays $nums1$ and $nums2$. Since we need to remove two elements from $nums1$, we only need to consider the first three elements of $nums1$, denoted as $a_1, a_2, a_3$. We can enumerate the first element $b_1$ of $nums2$, then we can get $x = b_1 - a_i$, where $i \in \{1, 2, 3\}$. Then we can use the two pointers method to determine whether there exists an integer $x$ that makes $nums1$ and $nums2$ equal, and take the smallest $x$ that satisfies the condition.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        def f(x: int) -> bool:
            i = j = cnt = 0
            while i < len(nums1) and j < len(nums2):
                if nums2[j] - nums1[i] != x:
                    cnt += 1
                else:
                    j += 1
                i += 1
            return cnt <= 2

        nums1.sort()
        nums2.sort()
        return min(
            x
            for x in (nums2[0] - nums1[0], nums2[0] - nums1[1], nums2[0] - nums1[2])
            if f(x)
        )
```

```java
class Solution {
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = 1 << 30;
        for (int i = 0; i < 3; ++i) {
            int x = nums2[0] - nums1[i];
            if (f(nums1, nums2, x)) {
                ans = Math.min(ans, x);
            }
        }
        return ans;
    }

    private boolean f(int[] nums1, int[] nums2, int x) {
        int i = 0, j = 0, cnt = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] - nums1[i] != x) {
                ++cnt;
            } else {
                ++j;
            }
            ++i;
        }
        return cnt <= 2;
    }
}
```

```cpp
class Solution {
public:
    int minimumAddedInteger(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        int ans = 1 << 30;
        auto f = [&](int x) {
            int i = 0, j = 0, cnt = 0;
            while (i < nums1.size() && j < nums2.size()) {
                if (nums2[j] - nums1[i] != x) {
                    ++cnt;
                } else {
                    ++j;
                }
                ++i;
            }
            return cnt <= 2;
        };
        for (int i = 0; i < 3; ++i) {
            int x = nums2[0] - nums1[i];
            if (f(x)) {
                ans = min(ans, x);
            }
        }
        return ans;
    }
};
```

```go
func minimumAddedInteger(nums1 []int, nums2 []int) int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	ans := 1 << 30
	f := func(x int) bool {
		i, j, cnt := 0, 0, 0
		for i < len(nums1) && j < len(nums2) {
			if nums2[j]-nums1[i] != x {
				cnt++
			} else {
				j++
			}
			i++
		}
		return cnt <= 2
	}
	for _, a := range nums1[:3] {
		x := nums2[0] - a
		if f(x) {
			ans = min(ans, x)
		}
	}
	return ans
}
```

```ts
function minimumAddedInteger(nums1: number[], nums2: number[]): number {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => a - b);
    const f = (x: number): boolean => {
        let [i, j, cnt] = [0, 0, 0];
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] - nums1[i] !== x) {
                ++cnt;
            } else {
                ++j;
            }
            ++i;
        }
        return cnt <= 2;
    };
    let ans = Infinity;
    for (let i = 0; i < 3; ++i) {
        const x = nums2[0] - nums1[i];
        if (f(x)) {
            ans = Math.min(ans, x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
