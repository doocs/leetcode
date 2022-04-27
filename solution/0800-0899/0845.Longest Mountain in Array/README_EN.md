# [845. Longest Mountain in Array](https://leetcode.com/problems/longest-mountain-in-array)

[中文文档](/solution/0800-0899/0845.Longest%20Mountain%20in%20Array/README.md)

## Description

<p>You may recall that an array <code>arr</code> is a <strong>mountain array</strong> if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some index <code>i</code> (<strong>0-indexed</strong>) with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given an integer array <code>arr</code>, return <em>the length of the longest subarray, which is a mountain</em>. Return <code>0</code> if there is no mountain subarray.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,4,7,3,2,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The largest mountain is [1,4,7,3,2] which has length 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no mountain.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Can you solve it using only one pass?</li>
	<li>Can you solve it in <code>O(1)</code> space?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        left, right = 0, 1
        status = -1
        ans = 0
        while right < len(arr):
            if status == -1 or status == 1:
                if arr[right] == arr[right - 1]:
                    status = -1
                if status == -1:
                    if arr[right] > arr[right - 1]:
                        status = 1
                    else:
                        left = right
                if status == 1 and arr[right] < arr[right - 1]:
                    status = 2
            else:
                if arr[right] == arr[right - 1]:
                    status = -1
                    ans = max(ans, right - left)
                    left = right
                elif arr[right] > arr[right - 1]:
                    status = 1
                    ans = max(ans, right - left)
                    left = right - 1
            right += 1
        if status == 2:
            ans = max(right - left, ans)
        return ans
```

### **Java**

```java
class Solution {
    public int longestMountain(int[] arr) {
        int left = 0, right = 0;
        int ans = 0;
        int status = -1;
        while (++right < arr.length) {
            if (status == -1 || status == 1) {
                if (arr[right] == arr[right - 1]) {
                    status = -1;
                }
                if (status == -1) {
                    if (arr[right] > arr[right - 1]) {
                        status = 1;
                    } else {
                        left = right;
                    }
                }
                if (status == 1 && arr[right] < arr[right - 1]) {
                    status = 2;
                }
            } else {
                if (arr[right] > arr[right - 1]) {
                    status = 1;
                    ans = Math.max(right - left, ans);
                    left = right - 1;
                } else if (arr[right] == arr[right - 1]) {
                    status = -1;
                    ans = Math.max(right - left, ans);
                    left = right;
                }
            }
        }
        if (status == 2) {
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
