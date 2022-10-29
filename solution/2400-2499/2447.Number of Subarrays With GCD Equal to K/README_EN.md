# [2447. Number of Subarrays With GCD Equal to K](https://leetcode.com/problems/number-of-subarrays-with-gcd-equal-to-k)

[中文文档](/solution/2400-2499/2447.Number%20of%20Subarrays%20With%20GCD%20Equal%20to%20K/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>subarrays</strong> of </em><code>nums</code><em> where the greatest common divisor of the subarray&#39;s elements is </em><code>k</code>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>The <strong>greatest common divisor of an array</strong> is the largest integer that evenly divides all the array elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,3,1,2,6,3], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The subarrays of nums where 3 is the greatest common divisor of all the subarray&#39;s elements are:
- [9,<u><strong>3</strong></u>,1,2,6,3]
- [9,3,1,2,6,<u><strong>3</strong></u>]
- [<u><strong>9,3</strong></u>,1,2,6,3]
- [9,3,1,2,<u><strong>6,3</strong></u>]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4], k = 7
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no subarrays of nums where 7 is the greatest common divisor of all the subarray&#39;s elements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            x = nums[i]
            for j in range(i, n):
                x = gcd(x, nums[j])
                if x == k:
                    ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = i; j < n; ++j) {
                x = gcd(x, nums[j]);
                if (x == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarrayGCD(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = i; j < n; ++j) {
                x = __gcd(x, nums[j]);
                ans += x == k;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func subarrayGCD(nums []int, k int) int {
	ans, n := 0, len(nums)
	for i, x := range nums {
		for j := i; j < n; j++ {
			x = gcd(x, nums[j])
			if x == k {
				ans++
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function subarrayGCD(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        for (let j = i; j < n; j++) {
            x = gcd(nums[j], x);
            if (x == k) ans += 1;
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (a > b) [a, b] = [b, a];
    if (a == 0) return b;
    return gcd(b % a, a);
}
```

### **...**

```

```

<!-- tabs:end -->
