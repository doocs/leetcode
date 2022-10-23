# [2340. Minimum Adjacent Swaps to Make a Valid Array](https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array)

[中文文档](/solution/2300-2399/2340.Minimum%20Adjacent%20Swaps%20to%20Make%20a%20Valid%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p><strong>Swaps</strong> of <strong>adjacent</strong> elements are able to be performed on <code>nums</code>.</p>

<p>A <strong>valid</strong> array meets the following conditions:</p>

<ul>
	<li>The largest element (any of the largest elements if there are multiple) is at the rightmost position in the array.</li>
	<li>The smallest element (any of the smallest elements if there are multiple) is at the leftmost position in the array.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> swaps required to make </em><code>nums</code><em> a valid array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,5,3,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Perform the following swaps:
- Swap 1: Swap the 3<sup>rd</sup> and 4<sup>th</sup> elements, nums is then [3,4,5,<u><strong>3</strong></u>,<u><strong>5</strong></u>,1].
- Swap 2: Swap the 4<sup>th</sup> and 5<sup>th</sup> elements, nums is then [3,4,5,3,<u><strong>1</strong></u>,<u><strong>5</strong></u>].
- Swap 3: Swap the 3<sup>rd</sup> and 4<sup>th</sup> elements, nums is then [3,4,5,<u><strong>1</strong></u>,<u><strong>3</strong></u>,5].
- Swap 4: Swap the 2<sup>nd</sup> and 3<sup>rd</sup> elements, nums is then [3,4,<u><strong>1</strong></u>,<u><strong>5</strong></u>,3,5].
- Swap 5: Swap the 1<sup>st</sup> and 2<sup>nd</sup> elements, nums is then [3,<u><strong>1</strong></u>,<u><strong>4</strong></u>,5,3,5].
- Swap 6: Swap the 0<sup>th</sup> and 1<sup>st</sup> elements, nums is then [<u><strong>1</strong></u>,<u><strong>3</strong></u>,4,5,3,5].
It can be shown that 6 swaps is the minimum swaps required to make a valid array.
</pre>

<strong class="example">Example 2:</strong>

<pre>
<strong>Input:</strong> nums = [9]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array is already valid, so we return 0.
</pre>

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
    def minimumSwaps(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        i, j = -1, -1
        for k, v in enumerate(nums):
            if v == mi and i == -1:
                i = k
            if v == mx:
                j = k
        if i == j:
            return 0
        n = len(nums)
        if i < j:
            return i + n - 1 - j
        return i + n - 2 - j
```

### **Java**

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int mi = min(nums), mx = max(nums);
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) {
                i = k;
            }
            if (nums[k] == mx) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }

    private int max(int[] nums) {
        int v = 0;
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }

    private int min(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.min(v, x);
        }
        return v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int n = nums.size();
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) i = k;
            if (nums[k] == mx) j = k;
        }
        if (i == j) return 0;
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }
};
```

### **Go**

```go
func minimumSwaps(nums []int) int {
	mi, mx := nums[0], 0
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	i, j := -1, -1
	for k, v := range nums {
		if v == mi && i == -1 {
			i = k
		}
		if v == mx {
			j = k
		}
	}
	if i == j {
		return 0
	}
	n := len(nums)
	if i < j {
		return i + n - 1 - j
	}
	return i + n - 2 - j
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
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
