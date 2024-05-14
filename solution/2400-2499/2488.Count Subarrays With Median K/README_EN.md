---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2488.Count%20Subarrays%20With%20Median%20K/README_EN.md
rating: 1998
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

# [2488. Count Subarrays With Median K](https://leetcode.com/problems/count-subarrays-with-median-k)

[中文文档](/solution/2400-2499/2488.Count%20Subarrays%20With%20Median%20K/README.md)

## Description

<p>You are given an array <code>nums</code> of size <code>n</code> consisting of <strong>distinct </strong>integers from <code>1</code> to <code>n</code> and a positive integer <code>k</code>.</p>

<p>Return <em>the number of non-empty subarrays in </em><code>nums</code><em> that have a <strong>median</strong> equal to </em><code>k</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>The median of an array is the <strong>middle </strong>element after sorting the array in <strong>ascending </strong>order. If the array is of even length, the median is the <strong>left </strong>middle element.

    <ul>
    	<li>For example, the median of <code>[2,3,1,4]</code> is <code>2</code>, and the median of <code>[8,4,3,5,1]</code> is <code>4</code>.</li>
    </ul>
    </li>
    <li>A subarray is a contiguous part of an array.</li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,4,5], k = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1], k = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> [3] is the only subarray that has a median equal to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= n</code></li>
	<li>The integers in <code>nums</code> are distinct.</li>
</ul>

## Solutions

### Solution 1: Traversal + Counting

First, we find the position $i$ of the median $k$ in the array, and then start traversing from $i$ to both sides, counting the number of subarrays with a median of $k$.

Define an answer variable $ans$, which represents the number of subarrays with a median of $k$. Initially, $ans = 1$, which means that there is currently a subarray of length $1$ with a median of $k$. In addition, define a counter $cnt$, used to count the number of differences between the "number of elements larger than $k$" and the "number of elements smaller than $k$" in the currently traversed array.

Next, start traversing to the right from $i + 1$. We maintain a variable $x$, which represents the difference between the "number of elements larger than $k$" and the "number of elements smaller than $k$" in the current right subarray. If $x \in [0, 1]$, then the median of the current right subarray is $k$, and the answer variable $ans$ is incremented by $1$. Then, we add the value of $x$ to the counter $cnt$.

Similarly, start traversing to the left from $i - 1$, also maintaining a variable $x$, which represents the difference between the "number of elements larger than $k$" and the "number of elements smaller than $k$" in the current left subarray. If $x \in [0, 1]$, then the median of the current left subarray is $k$, and the answer variable $ans$ is incremented by $1$. If $-x$ or $-x + 1$ is also in the counter, it means that there is currently a subarray that spans both sides of $i$, with a median of $k$, and the answer variable $ans$ increases the corresponding value in the counter, that is, $ans += cnt[-x] + cnt[-x + 1]$.

Finally, return the answer variable $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

> In coding, we can directly open an array of length $2 \times n + 1$, used to count the difference between the "number of elements larger than $k$" and the "number of elements smaller than $k$" in the current array. Each time we add the difference by $n$, we can convert the range of the difference from $[-n, n]$ to $[0, 2n]$.

<!-- tabs:start -->

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        i = nums.index(k)
        cnt = Counter()
        ans = 1
        x = 0
        for v in nums[i + 1 :]:
            x += 1 if v > k else -1
            ans += 0 <= x <= 1
            cnt[x] += 1
        x = 0
        for j in range(i - 1, -1, -1):
            x += 1 if nums[j] > k else -1
            ans += 0 <= x <= 1
            ans += cnt[-x] + cnt[-x + 1]
        return ans
```

```java
class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        for (; nums[i] != k; ++i) {
        }
        int[] cnt = new int[n << 1 | 1];
        int ans = 1;
        int x = 0;
        for (int j = i + 1; j < n; ++j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ++cnt[x + n];
        }
        x = 0;
        for (int j = i - 1; j >= 0; --j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ans += cnt[-x + n] + cnt[-x + 1 + n];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), k) - nums.begin();
        int cnt[n << 1 | 1];
        memset(cnt, 0, sizeof(cnt));
        int ans = 1;
        int x = 0;
        for (int j = i + 1; j < n; ++j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ++cnt[x + n];
        }
        x = 0;
        for (int j = i - 1; ~j; --j) {
            x += nums[j] > k ? 1 : -1;
            if (x >= 0 && x <= 1) {
                ++ans;
            }
            ans += cnt[-x + n] + cnt[-x + 1 + n];
        }
        return ans;
    }
};
```

```go
func countSubarrays(nums []int, k int) int {
	i, n := 0, len(nums)
	for nums[i] != k {
		i++
	}
	ans := 1
	cnt := make([]int, n<<1|1)
	x := 0
	for j := i + 1; j < n; j++ {
		if nums[j] > k {
			x++
		} else {
			x--
		}
		if x >= 0 && x <= 1 {
			ans++
		}
		cnt[x+n]++
	}
	x = 0
	for j := i - 1; j >= 0; j-- {
		if nums[j] > k {
			x++
		} else {
			x--
		}
		if x >= 0 && x <= 1 {
			ans++
		}
		ans += cnt[-x+n] + cnt[-x+1+n]
	}
	return ans
}
```

```ts
function countSubarrays(nums: number[], k: number): number {
    const i = nums.indexOf(k);
    const n = nums.length;
    const cnt = new Array((n << 1) | 1).fill(0);
    let ans = 1;
    let x = 0;
    for (let j = i + 1; j < n; ++j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ++cnt[x + n];
    }
    x = 0;
    for (let j = i - 1; ~j; --j) {
        x += nums[j] > k ? 1 : -1;
        ans += x >= 0 && x <= 1 ? 1 : 0;
        ans += cnt[-x + n] + cnt[-x + 1 + n];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
