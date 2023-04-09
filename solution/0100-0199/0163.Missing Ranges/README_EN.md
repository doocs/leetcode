# [163. Missing Ranges](https://leetcode.com/problems/missing-ranges)

[中文文档](/solution/0100-0199/0163.Missing%20Ranges/README.md)

## Description

<p>You are given an inclusive range <code>[lower, upper]</code> and a <strong>sorted unique</strong> integer array <code>nums</code>, where all elements are within the inclusive range.</p>

<p>A number <code>x</code> is considered <strong>missing</strong> if <code>x</code> is in the range <code>[lower, upper]</code> and <code>x</code> is not in <code>nums</code>.</p>

<p>Return <em>the <strong>shortest sorted</strong> list of ranges that <b>exactly covers all the missing numbers</b></em>. That is, no element of <code>nums</code> is included in any of the ranges, and each missing number is covered by one of the ranges.</p>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,3,50,75], lower = 0, upper = 99
<strong>Output:</strong> [[2,2],[4,49],[51,74],[76,99]]
<strong>Explanation:</strong> The ranges are:
[2,2]
[4,49]
[51,74]
[76,99]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1], lower = -1, upper = -1
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no missing ranges since there are no missing numbers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>9</sup> &lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>lower &lt;= nums[i] &lt;= upper</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        def f(a, b):
            return str(a) if a == b else f'{a}->{b}'

        n = len(nums)
        if n == 0:
            return [f(lower, upper)]
        ans = []
        if nums[0] > lower:
            ans.append(f(lower, nums[0] - 1))
        for a, b in pairwise(nums):
            if b - a > 1:
                ans.append(f(a + 1, b - 1))
        if nums[-1] < upper:
            ans.append(f(nums[-1] + 1, upper))
        return ans
```

### **Java**

```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add(f(lower, upper));
            return ans;
        }
        if (nums[0] > lower) {
            ans.add(f(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            int a = nums[i - 1], b = nums[i];
            if (b - a > 1) {
                ans.add(f(a + 1, b - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(f(nums[n - 1] + 1, upper));
        }
        return ans;
    }

    private String f(int a, int b) {
        return a == b ? a + "" : a + "->" + b;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findMissingRanges(vector<int>& nums, int lower, int upper) {
        auto f = [](int a, int b) {
            return a == b ? to_string(a) : to_string(a) + "->" + to_string(b);
        };
        int n = nums.size();
        vector<string> ans;
        if (n == 0) {
            ans.emplace_back(f(lower, upper));
            return ans;
        }
        if (nums[0] > lower) {
            ans.emplace_back(f(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            int a = nums[i - 1], b = nums[i];
            if (b - a > 1) {
                ans.emplace_back(f(a + 1, b - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.emplace_back(f(nums[n - 1] + 1, upper));
        }
        return ans;
    }
};
```

### **Go**

```go
func findMissingRanges(nums []int, lower int, upper int) (ans []string) {
	f := func(a, b int) string {
		if a == b {
			return strconv.Itoa(a)
		}
		return strconv.Itoa(a) + "->" + strconv.Itoa(b)
	}
	n := len(nums)
	if n == 0 {
		ans = append(ans, f(lower, upper))
		return
	}
	if nums[0] > lower {
		ans = append(ans, f(lower, nums[0]-1))
	}
	for i := 1; i < n; i++ {
		a, b := nums[i-1], nums[i]
		if b-a > 1 {
			ans = append(ans, f(a+1, b-1))
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, f(nums[n-1]+1, upper))
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
