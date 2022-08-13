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
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,7,5], nums2 = [2,3,5]
<strong>Output:</strong> 3
<strong>Explanation: </strong>There are two possible optimal solutions:
- Replace the second element with the first: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>1</strong></u>,5], or
- Replace the second element with the third: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>5</strong></u>,5].
Both will yield an absolute sum difference of <code>|1-2| + (|1-3| or |5-3|) + |5-5| = </code>3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
<strong>Output:</strong> 0
<strong>Explanation: </strong>nums1 is equal to nums2 so no replacement is needed. This will result in an 
absolute sum difference of 0.
</pre>

<p><strong>Example 3:</strong></p>

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
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        mod = 10**9 + 7
        s = sum(diff)
        if s == 0:
            return 0
        nums1.sort()
        n = len(nums1)
        mx = 0
        for i, b in enumerate(nums2):
            d = diff[i]
            if d == 0:
                continue
            idx = bisect_left(nums1, b)
            a1 = a2 = 10**6
            if idx != n:
                a1 = nums1[idx]
            if idx:
                a2 = nums1[idx - 1]
            c = min(abs(b - a1), abs(b - a2))
            mx = max(mx, d - c)
        return (s - mx) % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % MOD;
        }
        if (s == 0) {
            return 0;
        }
        Arrays.sort(nums1);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) {
                continue;
            }
            int b = nums2[i];
            int idx = search(nums1, b);
            int a1 = 1000000, a2 = 1000000;
            if (idx != n) {
                a1 = nums1[idx];
            }
            if (idx != 0) {
                a2 = nums1[idx - 1];
            }
            int c = Math.min(Math.abs(b - a1), Math.abs(b - a2));
            mx = Math.max(mx, d - c);
        }
        return (s - mx + MOD) % MOD;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
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
        int n = nums1.size();
        vector<int> diff(n);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % mod;
        }
        if (s == 0) return 0;
        sort(nums1.begin(), nums1.end());
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) continue;
            int b = nums2[i];
            int idx = lower_bound(nums1.begin(), nums1.end(), b) - nums1.begin();
            int a1 = 1e6, a2 = 1e6;
            if (idx != n) a1 = nums1[idx];
            if (idx != 0) a2 = nums1[idx - 1];
            int c = min(abs(b - a1), abs(b - a2));
            mx = max(mx, d - c);
        }
        return (s - mx + mod) % mod;
    }
};
```

### **Go**

```go
func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	mod := int(1e9) + 7
	n := len(nums1)
	diff := make([]int, n)
	s := 0
	for i := 0; i < n; i++ {
		diff[i] = abs(nums1[i] - nums2[i])
		s = (s + diff[i]) % mod
	}
	if s == 0 {
		return 0
	}
	sort.Ints(nums1)
	mx := 0
	for i, b := range nums2 {
		d := diff[i]
		if d == 0 {
			continue
		}
		idx := search(nums1, b)
		a1, a2 := 1000000, 1000000
		if idx != n {
			a1 = nums1[idx]
		}
		if idx != 0 {
			a2 = nums1[idx-1]
		}
		c := min(abs(b-a1), abs(b-a2))
		mx = max(mx, d-c)
	}
	return (s - mx + mod) % mod
}

func search(nums []int, x int) int {
	left, right := 0, len(nums)
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
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

### **...**

```

```

<!-- tabs:end -->
