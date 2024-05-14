# [1537. Get the Maximum Score](https://leetcode.com/problems/get-the-maximum-score)

[中文文档](/solution/1500-1599/1537.Get%20the%20Maximum%20Score/README.md)

<!-- tags:Greedy,Array,Two Pointers,Dynamic Programming -->

<!-- difficulty:Hard -->

## Description

<p>You are given two <strong>sorted</strong> arrays of distinct integers <code>nums1</code> and <code>nums2</code>.</p>

<p>A <strong>valid<strong><em> </em></strong>path</strong> is defined as follows:</p>

<ul>
	<li>Choose array <code>nums1</code> or <code>nums2</code> to traverse (from index-0).</li>
	<li>Traverse the current array from left to right.</li>
	<li>If you are reading any value that is present in <code>nums1</code> and <code>nums2</code> you are allowed to change your path to the other array. (Only one repeated value is considered in the valid path).</li>
</ul>

<p>The <strong>score</strong> is defined as the sum of unique values in a valid path.</p>

<p>Return <em>the maximum score you can obtain of all possible <strong>valid paths</strong></em>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1537.Get%20the%20Maximum%20Score/images/sample_1_1893.png" style="width: 500px; height: 151px;" />
<pre>
<strong>Input:</strong> nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
<strong>Output:</strong> 30
<strong>Explanation:</strong> Valid paths:
[2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
[4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
The maximum is obtained with the path in green <strong>[2,4,6,8,10]</strong>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,3,5,7,9], nums2 = [3,5,100]
<strong>Output:</strong> 109
<strong>Explanation:</strong> Maximum sum is obtained with the path <strong>[1,3,5,100]</strong>.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
<strong>Output:</strong> 40
<strong>Explanation:</strong> There are no common elements between nums1 and nums2.
Maximum sum is obtained with the path [6,7,8,9,10].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>nums1</code> and <code>nums2</code> are strictly increasing.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxSum(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        m, n = len(nums1), len(nums2)
        i = j = 0
        f = g = 0
        while i < m or j < n:
            if i == m:
                g += nums2[j]
                j += 1
            elif j == n:
                f += nums1[i]
                i += 1
            elif nums1[i] < nums2[j]:
                f += nums1[i]
                i += 1
            elif nums1[i] > nums2[j]:
                g += nums2[j]
                j += 1
            else:
                f = g = max(f, g) + nums1[i]
                i += 1
                j += 1
        return max(f, g) % mod
```

```java
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        final int mod = (int) 1e9 + 7;
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        long f = 0, g = 0;
        while (i < m || j < n) {
            if (i == m) {
                g += nums2[j++];
            } else if (j == n) {
                f += nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                f += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                g += nums2[j++];
            } else {
                f = g = Math.max(f, g) + nums1[i];
                i++;
                j++;
            }
        }
        return (int) (Math.max(f, g) % mod);
    }
}
```

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        int m = nums1.size(), n = nums2.size();
        int i = 0, j = 0;
        long long f = 0, g = 0;
        while (i < m || j < n) {
            if (i == m) {
                g += nums2[j++];
            } else if (j == n) {
                f += nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                f += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                g += nums2[j++];
            } else {
                f = g = max(f, g) + nums1[i];
                i++;
                j++;
            }
        }
        return max(f, g) % mod;
    }
};
```

```go
func maxSum(nums1 []int, nums2 []int) int {
	const mod int = 1e9 + 7
	m, n := len(nums1), len(nums2)
	i, j := 0, 0
	f, g := 0, 0
	for i < m || j < n {
		if i == m {
			g += nums2[j]
			j++
		} else if j == n {
			f += nums1[i]
			i++
		} else if nums1[i] < nums2[j] {
			f += nums1[i]
			i++
		} else if nums1[i] > nums2[j] {
			g += nums2[j]
			j++
		} else {
			f = max(f, g) + nums1[i]
			g = f
			i++
			j++
		}
	}
	return max(f, g) % mod
}
```

```ts
function maxSum(nums1: number[], nums2: number[]): number {
    const mod = 1e9 + 7;
    const m = nums1.length;
    const n = nums2.length;
    let [f, g] = [0, 0];
    let [i, j] = [0, 0];
    while (i < m || j < n) {
        if (i === m) {
            g += nums2[j++];
        } else if (j === n) {
            f += nums1[i++];
        } else if (nums1[i] < nums2[j]) {
            f += nums1[i++];
        } else if (nums1[i] > nums2[j]) {
            g += nums2[j++];
        } else {
            f = g = Math.max(f, g) + nums1[i];
            i++;
            j++;
        }
    }
    return Math.max(f, g) % mod;
}
```

<!-- tabs:end -->

<!-- end -->
