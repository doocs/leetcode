# [2584. Split the Array to Make Coprime Products](https://leetcode.com/problems/split-the-array-to-make-coprime-products)

[中文文档](/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>A <strong>split</strong> at an index <code>i</code> where <code>0 &lt;= i &lt;= n - 2</code> is called <strong>valid</strong> if the product of the first <code>i + 1</code> elements and the product of the remaining elements are coprime.</p>

<ul>
	<li>For example, if <code>nums = [2, 3, 3]</code>, then a split at the index <code>i = 0</code> is valid because <code>2</code> and <code>9</code> are coprime, while a split at the index <code>i = 1</code> is not valid because <code>6</code> and <code>3</code> are not coprime. A split at the index <code>i = 2</code> is not valid because <code>i == n - 1</code>.</li>
</ul>

<p>Return <em>the smallest index </em><code>i</code><em> at which the array can be split validly or </em><code>-1</code><em> if there is no such split</em>.</p>

<p>Two values <code>val1</code> and <code>val2</code> are coprime if <code>gcd(val1, val2) == 1</code> where <code>gcd(val1, val2)</code> is the greatest common divisor of <code>val1</code> and <code>val2</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/images/second.png" style="width: 450px; height: 211px;" />
<pre>
<strong>Input:</strong> nums = [4,7,8,15,3,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
The only valid split is at index 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2584.Split%20the%20Array%20to%20Make%20Coprime%20Products/images/capture.png" style="width: 450px; height: 215px;" />
<pre>
<strong>Input:</strong> nums = [4,7,15,8,3,5]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
There is no valid split.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findValidSplit(self, nums: List[int]) -> int:
        first = {}
        n = len(nums)
        last = list(range(n))
        for i, x in enumerate(nums):
            j = 2
            while j <= x // j:
                if x % j == 0:
                    if j in first:
                        last[first[j]] = i
                    else:
                        first[j] = i
                    while x % j == 0:
                        x //= j
                j += 1
            if x > 1:
                if x in first:
                    last[first[x]] = i
                else:
                    first[x] = i
        mx = last[0]
        for i, x in enumerate(last):
            if mx < i:
                return mx
            mx = max(mx, x)
        return -1
```

### **Java**

```java
class Solution {
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        int n = nums.length;
        int[] last = new int[n];
        for (int i = 0; i < n; ++i) {
            last[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    if (first.containsKey(j)) {
                        last[first.get(j)] = i;
                    } else {
                        first.put(j, i);
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (first.containsKey(x)) {
                    last[first.get(x)] = i;
                } else {
                    first.put(x, i);
                }
            }
        }
        int mx = last[0];
        for (int i = 0; i < n; ++i) {
            if (mx < i) {
                return mx;
            }
            mx = Math.max(mx, last[i]);
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findValidSplit(vector<int>& nums) {
        unordered_map<int, int> first;
        int n = nums.size();
        vector<int> last(n);
        iota(last.begin(), last.end(), 0);
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    if (first.count(j)) {
                        last[first[j]] = i;
                    } else {
                        first[j] = i;
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (first.count(x)) {
                    last[first[x]] = i;
                } else {
                    first[x] = i;
                }
            }
        }
        int mx = last[0];
        for (int i = 0; i < n; ++i) {
            if (mx < i) {
                return mx;
            }
            mx = max(mx, last[i]);
        }
        return -1;
    }
};
```

### **Go**

```go
func findValidSplit(nums []int) int {
	first := map[int]int{}
	n := len(nums)
	last := make([]int, n)
	for i := range last {
		last[i] = i
	}
	for i, x := range nums {
		for j := 2; j <= x/j; j++ {
			if x%j == 0 {
				if k, ok := first[j]; ok {
					last[k] = i
				} else {
					first[j] = i
				}
				for x%j == 0 {
					x /= j
				}
			}
		}
		if x > 1 {
			if k, ok := first[x]; ok {
				last[k] = i
			} else {
				first[x] = i
			}
		}
	}
	mx := last[0]
	for i, x := range last {
		if mx < i {
			return mx
		}
		mx = max(mx, x)
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
