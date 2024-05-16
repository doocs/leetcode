---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README_EN.md
rating: 2383
source: Weekly Contest 198 Q4
tags:
    - Bit Manipulation
    - Segment Tree
    - Array
    - Binary Search
---

# [1521. Find a Value of a Mysterious Function Closest to Target](https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target)

[中文文档](/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README.md)

## Description

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/images/change.png" style="width: 635px; height: 312px;" /></p>

<p>Winston was given the above mysterious function <code>func</code>. He has an integer array <code>arr</code> and an integer <code>target</code> and he wants to find the values <code>l</code> and <code>r</code> that make the value <code>|func(arr, l, r) - target|</code> minimum possible.</p>

<p>Return <em>the minimum possible value</em> of <code>|func(arr, l, r) - target|</code>.</p>

<p>Notice that <code>func</code> should be called with the values <code>l</code> and <code>r</code> where <code>0 &lt;= l, r &lt; arr.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [9,12,3,7,15], target = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> Calling func with all the pairs of [l,r] = [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]], Winston got the following results [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0]. The value closest to 5 is 7 and 3, thus the minimum difference is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1000000,1000000,1000000], target = 1
<strong>Output:</strong> 999999
<strong>Explanation:</strong> Winston called the func with all possible values of [l,r] and he always got 1000000, thus the min difference is 999999.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4,8,16], target = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Enumeration

According to the problem description, we know that the function $func(arr, l, r)$ is actually the bitwise AND result of the elements in the array $arr$ from index $l$ to $r$, i.e., $arr[l] \& arr[l + 1] \& \cdots \& arr[r]$.

If we fix the right endpoint $r$ each time, then the range of the left endpoint $l$ is $[0, r]$. Since the sum of bitwise ANDs decreases monotonically with decreasing $l$, and the value of $arr[i]$ does not exceed $10^6$, there are at most $20$ different values in the interval $[0, r]$. Therefore, we can use a set to maintain all the values of $arr[l] \& arr[l + 1] \& \cdots \& arr[r]$. When we traverse from $r$ to $r+1$, the value with $r+1$ as the right endpoint is the value obtained by performing bitwise AND with each value in the set and $arr[r + 1]$, plus $arr[r + 1]$ itself. Therefore, we only need to enumerate each value in the set, perform bitwise AND with $arr[r]$, to obtain all the values with $r$ as the right endpoint. Then we subtract each value from $target$ and take the absolute value to obtain the absolute difference between each value and $target$. The minimum value among them is the answer.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $n$ and $M$ are the length of the array $arr$ and the maximum value in the array $arr$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def closestToTarget(self, arr: List[int], target: int) -> int:
        ans = abs(arr[0] - target)
        s = {arr[0]}
        for x in arr:
            s = {x & y for y in s} | {x}
            ans = min(ans, min(abs(y - target) for y in s))
        return ans
```

```java
class Solution {
    public int closestToTarget(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        Set<Integer> pre = new HashSet<>();
        pre.add(arr[0]);
        for (int x : arr) {
            Set<Integer> cur = new HashSet<>();
            for (int y : pre) {
                cur.add(x & y);
            }
            cur.add(x);
            for (int y : cur) {
                ans = Math.min(ans, Math.abs(y - target));
            }
            pre = cur;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int closestToTarget(vector<int>& arr, int target) {
        int ans = abs(arr[0] - target);
        unordered_set<int> pre;
        pre.insert(arr[0]);
        for (int x : arr) {
            unordered_set<int> cur;
            cur.insert(x);
            for (int y : pre) {
                cur.insert(x & y);
            }
            for (int y : cur) {
                ans = min(ans, abs(y - target));
            }
            pre = move(cur);
        }
        return ans;
    }
};
```

```go
func closestToTarget(arr []int, target int) int {
	ans := abs(arr[0] - target)
	pre := map[int]bool{arr[0]: true}
	for _, x := range arr {
		cur := map[int]bool{x: true}
		for y := range pre {
			cur[x&y] = true
		}
		for y := range cur {
			ans = min(ans, abs(y-target))
		}
		pre = cur
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function closestToTarget(arr: number[], target: number): number {
    let ans = Math.abs(arr[0] - target);
    let pre = new Set<number>();
    pre.add(arr[0]);
    for (const x of arr) {
        const cur = new Set<number>();
        cur.add(x);
        for (const y of pre) {
            cur.add(x & y);
        }
        for (const y of cur) {
            ans = Math.min(ans, Math.abs(y - target));
        }
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
