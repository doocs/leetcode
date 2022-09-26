# [17.19. Missing Two](https://leetcode.cn/problems/missing-two-lcci)

[中文文档](/lcci/17.19.Missing%20Two/README.md)

## Description

<p>You are given an array with all the numbers from 1 to N appearing exactly once, except for two number that is missing. How can you find the missing number in O(N) time and 0(1) space?</p>

<p>You can return the missing numbers in any order.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [1]

<strong>Output: </strong>[2,3]</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [2,3]

<strong>Output: </strong>[1,4]</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>nums.length &lt;=&nbsp;30000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        n = len(nums) + 2
        xor = 0
        for v in nums:
            xor ^= v
        for i in range(1, n + 1):
            xor ^= i

        diff = xor & (-xor)
        a = 0
        for v in nums:
            if v & diff:
                a ^= v
        for i in range(1, n + 1):
            if i & diff:
                a ^= i
        b = xor ^ a
        return [a, b]
```

### **Java**

```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int xor = 0;
        for (int v : nums) {
            xor ^= v;
        }
        for (int i = 1; i <= n; ++i) {
            xor ^= i;
        }
        int diff = xor & (-xor);
        int a = 0;
        for (int v : nums) {
            if ((v & diff) != 0) {
                a ^= v;
            }
        }
        for (int i = 1; i <= n; ++i) {
            if ((i & diff) != 0) {
                a ^= i;
            }
        }
        int b = xor ^ a;
        return new int[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> missingTwo(vector<int>& nums) {
        int n = nums.size() + 2;
        int eor = 0;
        for (int v : nums) eor ^= v;
        for (int i = 1; i <= n; ++i) eor ^= i;

        int diff = eor & -eor;
        int a = 0;
        for (int v : nums) if (v & diff) a ^= v;
        for (int i = 1; i <= n; ++i) if (i & diff) a ^= i;
        int b = eor ^ a;
        return {a, b};
    }
};
```

### **Go**

```go
func missingTwo(nums []int) []int {
	n := len(nums) + 2
	xor := 0
	for _, v := range nums {
		xor ^= v
	}
	for i := 1; i <= n; i++ {
		xor ^= i
	}
	diff := xor & -xor
	a := 0
	for _, v := range nums {
		if (v & diff) != 0 {
			a ^= v
		}
	}
	for i := 1; i <= n; i++ {
		if (i & diff) != 0 {
			a ^= i
		}
	}
	b := xor ^ a
	return []int{a, b}
}
```

### **...**

```

```

<!-- tabs:end -->
