# [2945. Find Maximum Non-decreasing Array Length](https://leetcode.com/problems/find-maximum-non-decreasing-array-length)

[中文文档](/solution/2900-2999/2945.Find%20Maximum%20Non-decreasing%20Array%20Length/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p>You can perform any number of operations, where each operation involves selecting a <strong>subarray</strong> of the array and replacing it with the <strong>sum</strong> of its elements. For example, if the given array is <code>[1,3,5,6]</code> and you select subarray <code>[3,5]</code> the array will convert to <code>[1,8,6]</code>.</p>

<p>Return <em>the </em><strong><em>maximum</em></strong><em> length of a </em><strong><em>non-decreasing</em></strong><em> array that can be made after applying operations.</em></p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> This array with length 3 is not non-decreasing.
We have two ways to make the array length two.
First, choosing subarray [2,2] converts the array to [5,4].
Second, choosing subarray [5,2] converts the array to [7,2].
In these two ways the array is not non-decreasing.
And if we choose subarray [5,2,2] and replace it with [9] it becomes non-decreasing. 
So the answer is 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The array is non-decreasing. So the answer is 4.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Replacing [3,2] with [5] converts the given array to [4,5,6] that is non-decreasing.
Because the given array is not non-decreasing, the maximum<!-- notionvc: 3447a505-d1ee-4411-8cae-e52162f53a55 --> possible answer is 3.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaximumLength(self, nums: List[int]) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        f = [0] * (n + 1)
        pre = [0] * (n + 2)
        for i in range(1, n + 1):
            pre[i] = max(pre[i], pre[i - 1])
            f[i] = f[pre[i]] + 1
            j = bisect_left(s, s[i] * 2 - s[pre[i]])
            pre[j] = i
        return f[n]
```

### **Java**

```java
class Solution {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int[] f = new int[n + 1];
        int[] pre = new int[n + 2];
        for (int i = 1; i <= n; ++i) {
            pre[i] = Math.max(pre[i], pre[i - 1]);
            f[i] = f[pre[i]] + 1;
            int j = Arrays.binarySearch(s, s[i] * 2 - s[pre[i]]);
            pre[j < 0 ? -j - 1 : j] = i;
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaximumLength(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1];
        int pre[n + 2];
        long long s[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        memset(f, 0, sizeof(f));
        memset(pre, 0, sizeof(pre));
        for (int i = 1; i <= n; ++i) {
            pre[i] = max(pre[i], pre[i - 1]);
            f[i] = f[pre[i]] + 1;
            int j = lower_bound(s, s + n + 1, s[i] * 2 - s[pre[i]]) - s;
            pre[j] = i;
        }
        return f[n];
    }
};
```

### **Go**

```go
func findMaximumLength(nums []int) int {
	n := len(nums)
	f := make([]int, n+1)
	pre := make([]int, n+2)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i := 1; i <= n; i++ {
		pre[i] = max(pre[i], pre[i-1])
		f[i] = f[pre[i]] + 1
		j := sort.SearchInts(s, s[i]*2-s[pre[i]])
		pre[j] = max(pre[j], i)
	}
	return f[n]
}
```

### **TypeScript**

```ts
function findMaximumLength(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    const pre: number[] = Array(n + 2).fill(0);
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        pre[i] = Math.max(pre[i], pre[i - 1]);
        f[i] = f[pre[i]] + 1;
        const j = search(s, s[i] * 2 - s[pre[i]]);
        pre[j] = i;
    }
    return f[n];
}
```

### **...**

```

```

<!-- tabs:end -->
