# [2918. Minimum Equal Sum of Two Arrays After Replacing Zeros](https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros)

[中文文档](/solution/2900-2999/2918.Minimum%20Equal%20Sum%20of%20Two%20Arrays%20After%20Replacing%20Zeros/README.md)

<!-- tags:Greedy,Array -->

## Description

<p>You are given two arrays <code>nums1</code> and <code>nums2</code> consisting of positive integers.</p>

<p>You have to replace <strong>all</strong> the <code>0</code>&#39;s in both arrays with <strong>strictly</strong> positive integers such that the sum of elements of both arrays becomes <strong>equal</strong>.</p>

<p>Return <em>the <strong>minimum</strong> equal sum you can obtain, or </em><code>-1</code><em> if it is impossible</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,2,0,1,0], nums2 = [6,5,0]
<strong>Output:</strong> 12
<strong>Explanation:</strong> We can replace 0&#39;s in the following way:
- Replace the two 0&#39;s in nums1 with the values 2 and 4. The resulting array is nums1 = [3,2,2,1,4].
- Replace the 0 in nums2 with the value 1. The resulting array is nums2 = [6,5,1].
Both arrays have an equal sum of 12. It can be shown that it is the minimum sum we can obtain.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,0,2,0], nums2 = [1,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to make the sum of both arrays equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Case Analysis

We consider the case where we treat all $0$s in the array as $1$s, and calculate the sum of the two arrays separately, denoted as $s_1$ and $s_2$. Without loss of generality, we assume that $s_1 \le s_2$.

-   If $s_1 = s_2$, then the answer is $s_1$.
-   If $s_1 \lt s_2$, then there must exist a $0$ in $nums1$ to make the sum of the two arrays equal. In this case, the answer is $s_2$. Otherwise, it means that it is impossible to make the sum of the two arrays equal, and we return $-1$.

The time complexity is $O(n + m)$, where $n$ and $m$ are the lengths of the arrays $nums1$ and $nums2$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minSum(self, nums1: List[int], nums2: List[int]) -> int:
        s1 = sum(nums1) + nums1.count(0)
        s2 = sum(nums2) + nums2.count(0)
        if s1 > s2:
            return self.minSum(nums2, nums1)
        if s1 == s2:
            return s1
        return -1 if nums1.count(0) == 0 else s2
```

```java
class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long s1 = 0, s2 = 0;
        boolean hasZero = false;
        for (int x : nums1) {
            hasZero |= x == 0;
            s1 += Math.max(x, 1);
        }
        for (int x : nums2) {
            s2 += Math.max(x, 1);
        }
        if (s1 > s2) {
            return minSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
}
```

```cpp
class Solution {
public:
    long long minSum(vector<int>& nums1, vector<int>& nums2) {
        long long s1 = 0, s2 = 0;
        bool hasZero = false;
        for (int x : nums1) {
            hasZero |= x == 0;
            s1 += max(x, 1);
        }
        for (int x : nums2) {
            s2 += max(x, 1);
        }
        if (s1 > s2) {
            return minSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
};
```

```go
func minSum(nums1 []int, nums2 []int) int64 {
	s1, s2 := 0, 0
	hasZero := false
	for _, x := range nums1 {
		if x == 0 {
			hasZero = true
		}
		s1 += max(x, 1)
	}
	for _, x := range nums2 {
		s2 += max(x, 1)
	}
	if s1 > s2 {
		return minSum(nums2, nums1)
	}
	if s1 == s2 {
		return int64(s1)
	}
	if hasZero {
		return int64(s2)
	}
	return -1
}
```

```ts
function minSum(nums1: number[], nums2: number[]): number {
    let [s1, s2] = [0, 0];
    let hasZero = false;
    for (const x of nums1) {
        if (x === 0) {
            hasZero = true;
        }
        s1 += Math.max(x, 1);
    }
    for (const x of nums2) {
        s2 += Math.max(x, 1);
    }
    if (s1 > s2) {
        return minSum(nums2, nums1);
    }
    if (s1 === s2) {
        return s1;
    }
    return hasZero ? s2 : -1;
}
```

```cs
public class Solution {
    public long MinSum(int[] nums1, int[] nums2) {
        long s1 = 0, s2 = 0;
        bool hasZero = false;
        foreach (int x in nums1) {
            hasZero |= x == 0;
            s1 += Math.Max(x, 1);
        }
        foreach (int x in nums2) {
            s2 += Math.Max(x, 1);
        }
        if (s1 > s2) {
            return MinSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
}
```

<!-- tabs:end -->

<!-- end -->
