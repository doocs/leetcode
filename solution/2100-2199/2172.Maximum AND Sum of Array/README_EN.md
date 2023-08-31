# [2172. Maximum AND Sum of Array](https://leetcode.com/problems/maximum-and-sum-of-array)

[中文文档](/solution/2100-2199/2172.Maximum%20AND%20Sum%20of%20Array/README.md)

## Description

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>numSlots</code> such that <code>2 * numSlots &gt;= n</code>. There are <code>numSlots</code> slots numbered from <code>1</code> to <code>numSlots</code>.</p>

<p>You have to place all <code>n</code> integers into the slots such that each slot contains at <strong>most</strong> two numbers. The <strong>AND sum</strong> of a given placement is the sum of the <strong>bitwise</strong> <code>AND</code> of every number with its respective slot number.</p>

<ul>
	<li>For example, the <strong>AND sum</strong> of placing the numbers <code>[1, 3]</code> into slot <u><code>1</code></u> and <code>[4, 6]</code> into slot <u><code>2</code></u> is equal to <code>(1 AND <u>1</u>) + (3 AND <u>1</u>) + (4 AND <u>2</u>) + (6 AND <u>2</u>) = 1 + 1 + 0 + 2 = 4</code>.</li>
</ul>

<p>Return <em>the maximum possible <strong>AND sum</strong> of </em><code>nums</code><em> given </em><code>numSlots</code><em> slots.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], numSlots = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> One possible placement is [1, 4] into slot <u>1</u>, [2, 6] into slot <u>2</u>, and [3, 5] into slot <u>3</u>. 
This gives the maximum AND sum of (1 AND <u>1</u>) + (4 AND <u>1</u>) + (2 AND <u>2</u>) + (6 AND <u>2</u>) + (3 AND <u>3</u>) + (5 AND <u>3</u>) = 1 + 0 + 2 + 2 + 3 + 1 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,10,4,7,1], numSlots = 9
<strong>Output:</strong> 24
<strong>Explanation:</strong> One possible placement is [1, 1] into slot <u>1</u>, [3] into slot <u>3</u>, [4] into slot <u>4</u>, [7] into slot <u>7</u>, and [10] into slot <u>9</u>.
This gives the maximum AND sum of (1 AND <u>1</u>) + (1 AND <u>1</u>) + (3 AND <u>3</u>) + (4 AND <u>4</u>) + (7 AND <u>7</u>) + (10 AND <u>9</u>) = 1 + 1 + 3 + 4 + 7 + 8 = 24.
Note that slots 2, 5, 6, and 8 are empty which is permitted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= numSlots &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 2 * numSlots</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 15</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumANDSum(self, nums: List[int], numSlots: int) -> int:
        n = len(nums)
        m = numSlots << 1
        f = [0] * (1 << m)
        for i in range(1 << m):
            cnt = i.bit_count()
            if cnt > n:
                continue
            for j in range(m):
                if i >> j & 1:
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j // 2 + 1)))
        return max(f)
```

### **Java**

```java
class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        int n = nums.length;
        int m = numSlots << 1;
        int[] f = new int[1 << m];
        int ans = 0;
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = Integer.bitCount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumANDSum(vector<int>& nums, int numSlots) {
        int n = nums.size();
        int m = numSlots << 1;
        int f[1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = __builtin_popcount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
        }
        return *max_element(f, f + (1 << m));
    }
};
```

### **Go**

```go
func maximumANDSum(nums []int, numSlots int) (ans int) {
	n := len(nums)
	m := numSlots << 1
	f := make([]int, 1<<m)
	for i := range f {
		cnt := bits.OnesCount(uint(i))
		if cnt > n {
			continue
		}
		for j := 0; j < m; j++ {
			if i>>j&1 == 1 {
				f[i] = max(f[i], f[i^(1<<j)]+(nums[cnt-1]&(j/2+1)))
			}
		}
		ans = max(ans, f[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maximumANDSum(nums: number[], numSlots: number): number {
    const n = nums.length;
    const m = numSlots << 1;
    const f: number[] = new Array(1 << m).fill(0);
    for (let i = 0; i < 1 << m; ++i) {
        const cnt = i
            .toString(2)
            .split('')
            .filter(c => c === '1').length;
        if (cnt > n) {
            continue;
        }
        for (let j = 0; j < m; ++j) {
            if (((i >> j) & 1) === 1) {
                f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & ((j >> 1) + 1)));
            }
        }
    }
    return Math.max(...f);
}
```

### **...**

```

```

<!-- tabs:end -->
