# [2598. Smallest Missing Non-negative Integer After Operations](https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations)

[中文文档](/solution/2500-2599/2598.Smallest%20Missing%20Non-negative%20Integer%20After%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>value</code>.</p>

<p>In one operation, you can add or subtract <code>value</code> from any element of <code>nums</code>.</p>

<ul>
	<li>For example, if <code>nums = [1,2,3]</code> and <code>value = 2</code>, you can choose to subtract <code>value</code> from <code>nums[0]</code> to make <code>nums = [-1,2,3]</code>.</li>
</ul>

<p>The MEX (minimum excluded) of an array is the smallest missing <strong>non-negative</strong> integer in it.</p>

<ul>
	<li>For example, the MEX of <code>[-1,2,3]</code> is <code>0</code> while the MEX of <code>[1,0,3]</code> is <code>2</code>.</li>
</ul>

<p>Return <em>the maximum MEX of </em><code>nums</code><em> after applying the mentioned operation <strong>any number of times</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> One can achieve this result by applying the following operations:
- Add value to nums[1] twice to make nums = [1,<strong><u>0</u></strong>,7,13,6,8]
- Subtract value from nums[2] once to make nums = [1,0,<strong><u>2</u></strong>,13,6,8]
- Subtract value from nums[3] twice to make nums = [1,0,2,<strong><u>3</u></strong>,6,8]
The MEX of nums is 4. It can be shown that 4 is the maximum MEX we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 7
<strong>Output:</strong> 2
<strong>Explanation:</strong> One can achieve this result by applying the following operation:
- subtract value from nums[2] once to make nums = [1,-10,<u><strong>0</strong></u>,13,6,8]
The MEX of nums is 2. It can be shown that 2 is the maximum MEX we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, value &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**Solution 1: Count**

We use a hash table or array $cnt$ to count the number of times each remainder of $value$ is taken modulo in the array.

Then start from $0$ and traverse, for the current number $i$ traversed, if $cnt[i \bmod value]$ is $0$, it means that there is no number in the array that takes $i$ modulo $value$ as the remainder, then $i$ is the MEX of the array, and return directly. Otherwise, reduce $cnt[i \bmod value]$ by $1$ and continue to traverse.

The time complexity is $O(n)$ and the space complexity is $O(value)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSmallestInteger(self, nums: List[int], value: int) -> int:
        cnt = Counter(x % value for x in nums)
        for i in range(len(nums) + 1):
            if cnt[i % value] == 0:
                return i
            cnt[i % value] -= 1
```

### **Java**

```java
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] cnt = new int[value];
        for (int x : nums) {
            ++cnt[(x % value + value) % value];
        }
        for (int i = 0;; ++i) {
            if (cnt[i % value]-- == 0) {
                return i;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findSmallestInteger(vector<int>& nums, int value) {
        int cnt[value];
        memset(cnt, 0, sizeof(cnt));
        for (int x : nums) {
            ++cnt[(x % value + value) % value];
        }
        for (int i = 0;; ++i) {
            if (cnt[i % value]-- == 0) {
                return i;
            }
        }
    }
};
```

### **Go**

```go
func findSmallestInteger(nums []int, value int) int {
	cnt := make([]int, value)
	for _, x := range nums {
		cnt[(x%value+value)%value]++
	}
	for i := 0; ; i++ {
		if cnt[i%value] == 0 {
			return i
		}
		cnt[i%value]--
	}
}
```

### **TypeScript**

```ts
function findSmallestInteger(nums: number[], value: number): number {
    const cnt: number[] = new Array(value).fill(0);
    for (const x of nums) {
        ++cnt[((x % value) + value) % value];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i % value]-- === 0) {
            return i;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
