# [2616. Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs)

[中文文档](/solution/2600-2699/2616.Minimize%20the%20Maximum%20Difference%20of%20Pairs/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>p</code>. Find <code>p</code> pairs of indices of <code>nums</code> such that the <strong>maximum</strong> difference amongst all the pairs is <strong>minimized</strong>. Also, ensure no index appears more than once amongst the <code>p</code> pairs.</p>

<p>Note that for a pair of elements at the index <code>i</code> and <code>j</code>, the difference of this pair is <code>|nums[i] - nums[j]|</code>, where <code>|x|</code> represents the <strong>absolute</strong> <strong>value</strong> of <code>x</code>.</p>

<p>Return <em>the <strong>minimum</strong> <strong>maximum</strong> difference among all </em><code>p</code> <em>pairs.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,1,2,7,1,3], p = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,1,2], p = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= p &lt;= (nums.length)/2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

class Solution:
    def minimizeMax(self, nums: List[int], p: int) -> int:
        def check(diff: int) -> bool:
            cnt = i = 0
            while i < len(nums) - 1:
                if nums[i + 1] - nums[i] <= diff:
                    cnt += 1
                    i += 2
                else:
                    i += 1
            return cnt >= p

        nums.sort()
        return bisect_left(range(nums[-1] - nums[0] + 1), True, key=check)
```

### **Java**

```java
class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0] + 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (count(nums, mid) >= p) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int count(int[] nums, int diff) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] <= diff) {
                ++cnt;
                ++i;
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizeMax(vector<int>& nums, int p) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int l = 0, r = nums[n - 1] - nums[0] + 1;
        auto check = [&](int diff) -> bool {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums[i + 1] - nums[i] <= diff) {
                    ++cnt;
                    ++i;
                }
            }
            return cnt >= p;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func minimizeMax(nums []int, p int) int {
	sort.Ints(nums)
	n := len(nums)
	r := nums[n-1] - nums[0] + 1
	return sort.Search(r, func(diff int) bool {
		cnt := 0
		for i := 0; i < n-1; i++ {
			if nums[i+1]-nums[i] <= diff {
				cnt++
				i++
			}
		}
		return cnt >= p
	})
}
```

### **...**

```

```

<!-- tabs:end -->
