# [1818. Minimum Absolute Sum Difference](https://leetcode.com/problems/minimum-absolute-sum-difference)

[中文文档](/solution/1800-1899/1818.Minimum%20Absolute%20Sum%20Difference/README.md)

## Description

<p>You are given two positive integer arrays <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>.</p>

<p>The <strong>absolute sum difference</strong> of arrays <code>nums1</code> and <code>nums2</code> is defined as the <strong>sum</strong> of <code>|nums1[i] - nums2[i]|</code> for each <code>0 &lt;= i &lt; n</code> (<strong>0-indexed</strong>).</p>

<p>You can replace <strong>at most one</strong> element of <code>nums1</code> with <strong>any</strong> other element in <code>nums1</code> to <strong>minimize</strong> the absolute sum difference.</p>

<p>Return the <em>minimum absolute sum difference <strong>after</strong> replacing at most one<strong> </strong>element in the array <code>nums1</code>.</em> Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><code>|x|</code> is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code>, or</li>
	<li><code>-x</code> if <code>x &lt; 0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,7,5], nums2 = [2,3,5]
<strong>Output:</strong> 3
<strong>Explanation: </strong>There are two possible optimal solutions:
- Replace the second element with the first: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>1</strong></u>,5], or
- Replace the second element with the third: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>5</strong></u>,5].
Both will yield an absolute sum difference of <code>|1-2| + (|1-3| or |5-3|) + |5-5| = </code>3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
<strong>Output:</strong> 0
<strong>Explanation: </strong>nums1 is equal to nums2 so no replacement is needed. This will result in an 
absolute sum difference of 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
<strong>Output:</strong> 20
<strong>Explanation: </strong>Replace the first element with the second: [<u><strong>1</strong></u>,10,4,4,2,7] =&gt; [<u><strong>10</strong></u>,10,4,4,2,7].
This yields an absolute sum difference of <code>|10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20</code>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        nums = sorted(nums1)
        s = sum(abs(a - b) for a, b in zip(nums1, nums2)) % mod
        mx = 0
        for a, b in zip(nums1, nums2):
            d1, d2 = abs(a - b), inf
            i = bisect_left(nums, b)
            if i < len(nums):
                d2 = min(d2, abs(nums[i] - b))
            if i:
                d2 = min(d2, abs(nums[i - 1] - b))
            mx = max(mx, d1 - d2)
        return (s - mx + mod) % mod
```

### **Java**

```java
class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int mod = (int) 1e9 + 7;
        int[] nums = nums1.clone();
        Arrays.sort(nums);
        int s = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = Math.abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = search(nums, nums2[i]);
            if (j < n) {
                d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
            }
            if (j > 0) {
                d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
            }
            mx = Math.max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minAbsoluteSumDiff(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        vector<int> nums(nums1);
        sort(nums.begin(), nums.end());
        int s = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            s = (s + abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = lower_bound(nums.begin(), nums.end(), nums2[i]) - nums.begin();
            if (j < n) {
                d2 = min(d2, abs(nums[j] - nums2[i]));
            }
            if (j) {
                d2 = min(d2, abs(nums[j - 1] - nums2[i]));
            }
            mx = max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }
};
```

### **Go**

```go
func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	n := len(nums1)
	nums := make([]int, n)
	copy(nums, nums1)
	sort.Ints(nums)
	s, mx := 0, 0
	const mod int = 1e9 + 7
	for i, a := range nums1 {
		b := nums2[i]
		s = (s + abs(a-b)) % mod
	}
	for i, a := range nums1 {
		b := nums2[i]
		d1, d2 := abs(a-b), 1<<30
		j := sort.Search(n, func(k int) bool { return nums[k] >= b })
		if j < n {
			d2 = min(d2, abs(nums[j]-b))
		}
		if j > 0 {
			d2 = min(d2, abs(nums[j-1]-b))
		}
		mx = max(mx, d1-d2)
	}
	return (s - mx + mod) % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var minAbsoluteSumDiff = function (nums1, nums2) {
    const mod = 10 ** 9 + 7;
    const nums = [...nums1];
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
    }
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const d1 = Math.abs(nums1[i] - nums2[i]);
        let d2 = 1 << 30;
        let j = search(nums, nums2[i]);
        if (j < n) {
            d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
        }
        if (j) {
            d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
        }
        mx = Math.max(mx, d1 - d2);
    }
    return (s - mx + mod) % mod;
};

function search(nums, x) {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **TypeScript**

```ts
function minAbsoluteSumDiff(nums1: number[], nums2: number[]): number {
    const mod = 10 ** 9 + 7;
    const nums = [...nums1];
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
    }
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const d1 = Math.abs(nums1[i] - nums2[i]);
        let d2 = 1 << 30;
        let j = search(nums, nums2[i]);
        if (j < n) {
            d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
        }
        if (j) {
            d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
        }
        mx = Math.max(mx, d1 - d2);
    }
    return (s - mx + mod) % mod;
}

function search(nums: number[], x: number): number {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
