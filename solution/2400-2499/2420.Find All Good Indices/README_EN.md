# [2420. Find All Good Indices](https://leetcode.com/problems/find-all-good-indices)

[中文文档](/solution/2400-2499/2420.Find%20All%20Good%20Indices/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code> and a positive integer <code>k</code>.</p>

<p>We call an index <code>i</code> in the range <code>k &lt;= i &lt; n - k</code> <strong>good</strong> if the following conditions are satisfied:</p>

<ul>
	<li>The <code>k</code> elements that are just <strong>before</strong> the index <code>i</code> are in <strong>non-increasing</strong> order.</li>
	<li>The <code>k</code> elements that are just <strong>after</strong> the index <code>i</code> are in <strong>non-decreasing</strong> order.</li>
</ul>

<p>Return <em>an array of all good indices sorted in <strong>increasing</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,1,1,3,4,1], k = 2
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong> There are two good indices in the array:
- Index 2. The subarray [2,1] is in non-increasing order, and the subarray [1,3] is in non-decreasing order.
- Index 3. The subarray [1,1] is in non-increasing order, and the subarray [3,4] is in non-decreasing order.
Note that the index 4 is not good because [4,1] is not non-decreasing.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,1,2], k = 2
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no good indices in this array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n / 2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def goodIndices(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        decr = [1] * (n + 1)
        incr = [1] * (n + 1)
        for i in range(2, n - 1):
            if nums[i - 1] <= nums[i - 2]:
                decr[i] = decr[i - 1] + 1
        for i in range(n - 3, -1, -1):
            if nums[i + 1] <= nums[i + 2]:
                incr[i] = incr[i + 1] + 1
        return [i for i in range(k, n - k) if decr[i] >= k and incr[i] >= k]
```

### **Java**

```java
class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] decr = new int[n];
        int[] incr = new int[n];
        Arrays.fill(decr, 1);
        Arrays.fill(incr, 1);
        for (int i = 2; i < n - 1; ++i) {
            if (nums[i - 1] <= nums[i - 2]) {
                decr[i] = decr[i - 1] + 1;
            }
        }
        for (int i = n - 3; i >= 0; --i) {
            if (nums[i + 1] <= nums[i + 2]) {
                incr[i] = incr[i + 1] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = k; i < n - k; ++i) {
            if (decr[i] >= k && incr[i] >= k) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> goodIndices(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> decr(n, 1);
        vector<int> incr(n, 1);
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] <= nums[i - 2]) {
                decr[i] = decr[i - 1] + 1;
            }
        }
        for (int i = n - 3; ~i; --i) {
            if (nums[i + 1] <= nums[i + 2]) {
                incr[i] = incr[i + 1] + 1;
            }
        }
        vector<int> ans;
        for (int i = k; i < n - k; ++i) {
            if (decr[i] >= k && incr[i] >= k) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func goodIndices(nums []int, k int) []int {
	n := len(nums)
	decr := make([]int, n)
	incr := make([]int, n)
	for i := range decr {
		decr[i] = 1
		incr[i] = 1
	}
	for i := 2; i < n; i++ {
		if nums[i-1] <= nums[i-2] {
			decr[i] = decr[i-1] + 1
		}
	}
	for i := n - 3; i >= 0; i-- {
		if nums[i+1] <= nums[i+2] {
			incr[i] = incr[i+1] + 1
		}
	}
	ans := []int{}
	for i := k; i < n-k; i++ {
		if decr[i] >= k && incr[i] >= k {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
