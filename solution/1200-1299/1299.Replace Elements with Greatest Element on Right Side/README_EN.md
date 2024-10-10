---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1299.Replace%20Elements%20with%20Greatest%20Element%20on%20Right%20Side/README_EN.md
rating: 1219
source: Biweekly Contest 16 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1299. Replace Elements with Greatest Element on Right Side](https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side)

[中文文档](/solution/1200-1299/1299.Replace%20Elements%20with%20Greatest%20Element%20on%20Right%20Side/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>arr</code>,&nbsp;replace every element in that array with the greatest element among the elements to its&nbsp;right, and replace the last element with <code>-1</code>.</p>

<p>After doing so, return the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [17,18,5,4,6,1]
<strong>Output:</strong> [18,6,6,6,1,-1]
<strong>Explanation:</strong>
- index 0 --&gt; the greatest element to the right of index 0 is index 1 (18).
- index 1 --&gt; the greatest element to the right of index 1 is index 4 (6).
- index 2 --&gt; the greatest element to the right of index 2 is index 4 (6).
- index 3 --&gt; the greatest element to the right of index 3 is index 4 (6).
- index 4 --&gt; the greatest element to the right of index 4 is index 5 (1).
- index 5 --&gt; there are no elements to the right of index 5, so we put -1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [400]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> There are no elements to the right of index 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

We use a variable $mx$ to record the maximum value to the right of the current position, initially $mx = -1$.

Then we traverse the array from right to left. For each position $i$, we denote the current value as $x$, update the current position's value to $mx$, and then update $mx = \max(mx, x)$.

Finally, return the original array.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def replaceElements(self, arr: List[int]) -> List[int]:
        mx = -1
        for i in reversed(range(len(arr))):
            x = arr[i]
            arr[i] = mx
            mx = max(mx, x)
        return arr
```

#### Java

```java
class Solution {
    public int[] replaceElements(int[] arr) {
        for (int i = arr.length - 1, mx = -1; i >= 0; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = Math.max(mx, x);
        }
        return arr;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> replaceElements(vector<int>& arr) {
        for (int i = arr.size() - 1, mx = -1; ~i; --i) {
            int x = arr[i];
            arr[i] = mx;
            mx = max(mx, x);
        }
        return arr;
    }
};
```

#### Go

```go
func replaceElements(arr []int) []int {
	for i, mx := len(arr)-1, -1; i >= 0; i-- {
		x := arr[i]
		arr[i] = mx
		mx = max(mx, x)
	}
	return arr
}
```

#### TypeScript

```ts
function replaceElements(arr: number[]): number[] {
    for (let i = arr.length - 1, mx = -1; ~i; --i) {
        const x = arr[i];
        arr[i] = mx;
        mx = Math.max(mx, x);
    }
    return arr;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
