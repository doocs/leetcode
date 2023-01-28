# [2541. Minimum Operations to Make Array Equal II](https://leetcode.com/problems/minimum-operations-to-make-array-equal-ii)

[中文文档](/solution/2500-2599/2541.Minimum%20Operations%20to%20Make%20Array%20Equal%20II/README.md)

## Description

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of equal length <code>n</code> and an integer <code>k</code>. You can perform the following operation on <code>nums1</code>:</p>

<ul>
	<li>Choose two indexes <code>i</code> and <code>j</code> and increment <code>nums1[i]</code> by <code>k</code> and decrement <code>nums1[j]</code> by <code>k</code>. In other words, <code>nums1[i] = nums1[i] + k</code> and <code>nums1[j] = nums1[j] - k</code>.</li>
</ul>

<p><code>nums1</code> is said to be <strong>equal</strong> to <code>nums2</code> if for all indices <code>i</code> such that <code>0 &lt;= i &lt; n</code>, <code>nums1[i] == nums2[i]</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of operations required to make </em><code>nums1</code><em> equal to </em><code>nums2</code>. If it is impossible to make them equal, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 operations, we can transform nums1 to nums2.
1<sup>st</sup> operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
2<sup>nd</sup> operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
One can prove that it is impossible to make arrays equal in fewer operations.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proved that it is impossible to make the two arrays equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int], k: int) -> int:
        ans = x = 0
        for a, b in zip(nums1, nums2):
            if k == 0:
                if a != b:
                    return -1
                continue
            if (a - b) % k:
                return -1
            y = (a - b) // k
            ans += abs(y)
            x += y
        return -1 if x else ans // 2
```

### **Java**

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        long ans = 0, x = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += Math.abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long ans = 0, x = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
};
```

### **Go**

```go
func minOperations(nums1 []int, nums2 []int, k int) int64 {
	ans, x := 0, 0
	for i, a := range nums1 {
		b := nums2[i]
		if k == 0 {
			if a != b {
				return -1
			}
			continue
		}
		if (a-b)%k != 0 {
			return -1
		}
		y := (a - b) / k
		ans += abs(y)
		x += y
	}
	if x != 0 {
		return -1
	}
	return int64(ans / 2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minOperations(nums1: number[], nums2: number[], k: number): number {
    const n = nums1.length;
    if (k === 0) {
        return nums1.every((v, i) => v === nums2[i]) ? 0 : -1;
    }
    let sum1 = 0;
    let sum2 = 0;
    for (let i = 0; i < n; i++) {
        const diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k !== 0) {
            return -1;
        }
        sum2 += Math.abs(diff);
    }
    if (sum1 !== 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let k = k as i64;
        let n = nums1.len();
        if k == 0 {
            return if nums1.iter().enumerate().all(|(i, &v)| v == nums2[i]) {
                0
            } else {
                -1
            };
        }
        let mut sum1 = 0;
        let mut sum2 = 0;
        for i in 0..n {
            let diff = (nums1[i] - nums2[i]) as i64;
            sum1 += diff;
            if diff % k != 0 {
                return -1;
            }
            sum2 += diff.abs();
        }
        if sum1 != 0 {
            return -1;
        }
        sum2 / (k * 2)
    }
}
```

### **C**

```c
long long minOperations(int *nums1, int nums1Size, int *nums2, int nums2Size, int k) {
    if (k == 0) {
        for (int i = 0; i < nums1Size; i++) {
            if (nums1[i] != nums2[i]) {
                return -1;
            }
        }
        return 0;
    }
    long long sum1 = 0;
    long long sum2 = 0;
    for (int i = 0; i < nums1Size; i++) {
        long long diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k != 0) {
            return -1;
        }
        sum2 += llabs(diff);
    }
    if (sum1 != 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
```

### **...**

```

```

<!-- tabs:end -->
