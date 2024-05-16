---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README_EN.md
rating: 1658
source: Weekly Contest 283 Q2
tags:
    - Greedy
    - Array
    - Math
    - Sorting
---

# [2195. Append K Integers With Minimal Sum](https://leetcode.com/problems/append-k-integers-with-minimal-sum)

[中文文档](/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. Append <code>k</code> <strong>unique positive</strong> integers that do <strong>not</strong> appear in <code>nums</code> to <code>nums</code> such that the resulting total sum is <strong>minimum</strong>.</p>

<p>Return<em> the sum of the</em> <code>k</code> <em>integers appended to</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,25,10,25], k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The two unique positive integers that do not appear in nums which we append are 2 and 3.
The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
The sum of the two integers appended is 2 + 3 = 5, so we return 5.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6], k = 6
<strong>Output:</strong> 25
<strong>Explanation:</strong> The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum. 
The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Greedy + Mathematics

We can add two sentinel nodes to the array, which are $0$ and $2 \times 10^9$.

Then we sort the array. For any two adjacent elements $a$ and $b$ in the array, the integers in the interval $[a+1, b-1]$ do not appear in the array, and we can add these integers to the array.

Therefore, we traverse the adjacent element pairs $(a, b)$ in the array from small to large. For each adjacent element pair, we calculate the number of integers $m$ in the interval $[a+1, b-1]$. The sum of these $m$ integers is $\frac{m \times (a+1 + a+m)}{2}$. We add this sum to the answer and subtract $m$ from $k$. If $k$ is reduced to $0$, we can stop the traversal and return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.extend([0, 2 * 10**9])
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            m = max(0, min(k, b - a - 1))
            ans += (a + 1 + a + m) * m // 2
            k -= m
        return ans
```

```java
class Solution {
    public long minimalKSum(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[1] = 2 * 1000000000;
        System.arraycopy(nums, 0, arr, 2, n);
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < n + 1 && k > 0; ++i) {
            int m = Math.max(0, Math.min(k, arr[i + 1] - arr[i] - 1));
            ans += (arr[i] + 1L + arr[i] + m) * m / 2;
            k -= m;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 0; i < nums.size() - 1 && k > 0; ++i) {
            int m = max(0, min(k, nums[i + 1] - nums[i] - 1));
            ans += 1LL * (nums[i] + 1 + nums[i] + m) * m / 2;
            k -= m;
        }
        return ans;
    }
};
```

```go
func minimalKSum(nums []int, k int) (ans int64) {
	nums = append(nums, []int{0, 2e9}...)
	sort.Ints(nums)
	for i, b := range nums[1:] {
		a := nums[i]
		m := max(0, min(k, b-a-1))
		ans += int64(a+1+a+m) * int64(m) / 2
		k -= m
	}
	return ans
}
```

```ts
function minimalKSum(nums: number[], k: number): number {
    nums.push(...[0, 2 * 10 ** 9]);
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        const m = Math.max(0, Math.min(k, nums[i + 1] - nums[i] - 1));
        ans += Number((BigInt(nums[i] + 1 + nums[i] + m) * BigInt(m)) / BigInt(2));
        k -= m;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
