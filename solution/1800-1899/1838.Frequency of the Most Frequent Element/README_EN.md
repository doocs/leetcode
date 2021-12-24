# [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element)

[中文文档](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README.md)

## Description

<p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

<p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 5
<strong>Output:</strong> 3<strong>
Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,8,13], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,6], k = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        window = 0
        l, r, n = 0, 1, len(nums)
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            r += 1
            while window > k:
                window -= nums[r - 1] - nums[l]
                l += 1
            ans = max(ans, r - l)
        return ans
```

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(1, n + 1):
            presum[i] = presum[i - 1] + nums[i - 1]

        def check(count):
            for i in range(n - count + 1):
                j = i + count - 1
                if nums[j] * count - (presum[j + 1] - presum[i]) <= k:
                    return True
            return False

        left, right = 1, n
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int window = 0;
        int l = 0, r = 1, n = nums.length;
        while (r < n) {
            window += (nums[r] - nums[r - 1]) * (r++ - l);
            while (window > k) {
                window -= nums[r - 1] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[] nums;
    private int k;
    private int n;
    private int[] presum;

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        this.k = k;
        n = nums.length;
        presum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int count) {
        for (int i = 0; i < n - count + 1; ++i) {
            int j = i + count - 1;
            if (nums[j] * count - (presum[j + 1] - presum[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```

### **Go**

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	window := 0
	l, r, n := 0, 1, len(nums)
	for r < n {
		window += (nums[r] - nums[r-1]) * (r - l)
		r++
		for window > k {
			window -= nums[r-1] - nums[l]
			l++
		}
		ans = max(ans, r-l)
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nums;
    int k;
    vector<long long> presum;
    int n;

    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        n = nums.size();
        this->k = k;
        this->nums = nums;
        presum = vector<long long>(n + 1);
        for (int i = 1; i <= n; ++i) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    bool check(int count) {
        for (int i = 0; i < n - count + 1; ++i) {
            int j = i + count - 1;
            if ((long long) nums[j] * count - (presum[j + 1] - presum[i]) <= k) return true;
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
