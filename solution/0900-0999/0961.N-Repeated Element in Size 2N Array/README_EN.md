# [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array)

[中文文档](/solution/0900-0999/0961.N-Repeated%20Element%20in%20Size%202N%20Array/README.md)

<!-- tags:Array,Hash Table -->

<!-- difficulty:Easy -->

## Description

<p>You are given an integer array <code>nums</code> with the following properties:</p>

<ul>
	<li><code>nums.length == 2 * n</code>.</li>
	<li><code>nums</code> contains <code>n + 1</code> <strong>unique</strong> elements.</li>
	<li>Exactly one element of <code>nums</code> is repeated <code>n</code> times.</li>
</ul>

<p>Return <em>the element that is repeated </em><code>n</code><em> times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,1,2,5,3,2]
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [5,1,5,2,5,3,5,4]
<strong>Output:</strong> 5
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5000</code></li>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> contains <code>n + 1</code> <strong>unique</strong> elements and one of them is repeated exactly <code>n</code> times.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def repeatedNTimes(self, nums: List[int]) -> int:
        s = set()
        for x in nums:
            if x in s:
                return x
            s.add(x)
```

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> s = new HashSet<>(nums.length / 2 + 1);
        for (int i = 0;; ++i) {
            if (!s.add(nums[i])) {
                return nums[i];
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = 0;; ++i) {
            if (s.count(nums[i])) {
                return nums[i];
            }
            s.insert(nums[i]);
        }
    }
};
```

```go
func repeatedNTimes(nums []int) int {
	s := map[int]bool{}
	for i := 0; ; i++ {
		if s[nums[i]] {
			return nums[i]
		}
		s[nums[i]] = true
	}
}
```

```ts
function repeatedNTimes(nums: number[]): number {
    const s: Set<number> = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return x;
        }
        s.add(x);
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var repeatedNTimes = function (nums) {
    const s = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return x;
        }
        s.add(x);
    }
};
```

<!-- tabs:end -->

<!-- end -->
