# [2956. Find Common Elements Between Two Arrays](https://leetcode.com/problems/find-common-elements-between-two-arrays)

[中文文档](/solution/2900-2999/2956.Find%20Common%20Elements%20Between%20Two%20Arrays/README.md)

<!-- tags:Array,Hash Table -->

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of sizes <code>n</code> and <code>m</code>, respectively.</p>

<p>Consider calculating the following values:</p>

<ul>
	<li>The number of indices <code>i</code> such that <code>0 &lt;= i &lt; n</code> and <code>nums1[i]</code> occurs <strong>at least</strong> once in <code>nums2</code>.</li>
	<li>The number of indices <code>i</code> such that <code>0 &lt;= i &lt; m</code> and <code>nums2[i]</code> occurs <strong>at least</strong> once in <code>nums1</code>.</li>
</ul>

<p>Return <em>an integer array </em><code>answer</code><em> of size </em><code>2</code><em> containing the two values <strong>in the above order</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]
<strong>Output:</strong> [3,4]
<strong>Explanation:</strong> We calculate the values as follows:
- The elements at indices 1, 2, and 3 in nums1 occur at least once in nums2. So the first value is 3.
- The elements at indices 0, 1, 3, and 4 in nums2 occur at least once in nums1. So the second value is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,4,2,3], nums2 = [1,5]
<strong>Output:</strong> [0,0]
<strong>Explanation:</strong> There are no common elements between the two arrays, so the two values will be 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>m == nums2.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Hash Table or Array

We can use two hash tables or arrays $s1$ and $s2$ to record the elements that appear in the two arrays respectively.

Next, we create an array $ans$ of length $2$, where $ans[0]$ represents the number of elements in $nums1$ that appear in $s2$, and $ans[1]$ represents the number of elements in $nums2$ that appear in $s1$.

Then, we traverse each element $x$ in the array $nums1$. If $x$ has appeared in $s2$, we increment $ans[0]$. After that, we traverse each element $x$ in the array $nums2$. If $x$ has appeared in $s1$, we increment $ans[1]$.

Finally, we return the array $ans$.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the lengths of the arrays $nums1$ and $nums2$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def findIntersectionValues(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s1, s2 = set(nums1), set(nums2)
        return [sum(x in s2 for x in nums1), sum(x in s1 for x in nums2)]
```

```java
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] s1 = new int[101];
        int[] s2 = new int[101];
        for (int x : nums1) {
            s1[x] = 1;
        }
        for (int x : nums2) {
            s2[x] = 1;
        }
        int[] ans = new int[2];
        for (int x : nums1) {
            ans[0] += s2[x];
        }
        for (int x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findIntersectionValues(vector<int>& nums1, vector<int>& nums2) {
        int s1[101]{};
        int s2[101]{};
        for (int& x : nums1) {
            s1[x] = 1;
        }
        for (int& x : nums2) {
            s2[x] = 1;
        }
        vector<int> ans(2);
        for (int& x : nums1) {
            ans[0] += s2[x];
        }
        for (int& x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
};
```

```go
func findIntersectionValues(nums1 []int, nums2 []int) []int {
	s1 := [101]int{}
	s2 := [101]int{}
	for _, x := range nums1 {
		s1[x] = 1
	}
	for _, x := range nums2 {
		s2[x] = 1
	}
	ans := make([]int, 2)
	for _, x := range nums1 {
		ans[0] += s2[x]
	}
	for _, x := range nums2 {
		ans[1] += s1[x]
	}
	return ans
}
```

```ts
function findIntersectionValues(nums1: number[], nums2: number[]): number[] {
    const s1: number[] = Array(101).fill(0);
    const s2: number[] = Array(101).fill(0);
    for (const x of nums1) {
        s1[x] = 1;
    }
    for (const x of nums2) {
        s2[x] = 1;
    }
    const ans: number[] = Array(2).fill(0);
    for (const x of nums1) {
        ans[0] += s2[x];
    }
    for (const x of nums2) {
        ans[1] += s1[x];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
