# [446. Arithmetic Slices II - Subsequence](https://leetcode.com/problems/arithmetic-slices-ii-subsequence)

[中文文档](/solution/0400-0499/0446.Arithmetic%20Slices%20II%20-%20Subsequence/README.md)

<!-- tags:Array,Dynamic Programming -->

## Description

<p>Given an integer array <code>nums</code>, return <em>the number of all the <strong>arithmetic subsequences</strong> of</em> <code>nums</code>.</p>

<p>A sequence of numbers is called arithmetic if it consists of <strong>at least three elements</strong> and if the difference between any two consecutive elements is the same.</p>

<ul>
	<li>For example, <code>[1, 3, 5, 7, 9]</code>, <code>[7, 7, 7, 7]</code>, and <code>[3, -1, -5, -9]</code> are arithmetic sequences.</li>
	<li>For example, <code>[1, 1, 2, 5, 7]</code> is not an arithmetic sequence.</li>
</ul>

<p>A <strong>subsequence</strong> of an array is a sequence that can be formed by removing some elements (possibly none) of the array.</p>

<ul>
	<li>For example, <code>[2,5,10]</code> is a subsequence of <code>[1,2,1,<strong><u>2</u></strong>,4,1,<u><strong>5</strong></u>,<u><strong>10</strong></u>]</code>.</li>
</ul>

<p>The test cases are generated so that the answer fits in <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6,8,10]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7,7]
<strong>Output:</strong> 16
<strong>Explanation:</strong> Any subsequence of this array is arithmetic.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1&nbsp; &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        f = [defaultdict(int) for _ in nums]
        ans = 0
        for i, x in enumerate(nums):
            for j, y in enumerate(nums[:i]):
                d = x - y
                ans += f[j][d]
                f[i][d] += f[j][d] + 1
        return ans
```

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] f = new Map[n];
        Arrays.setAll(f, k -> new HashMap<>());
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                Long d = 1L * nums[i] - nums[j];
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                f[i].merge(d, cnt + 1, Integer::sum);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        unordered_map<long long, int> f[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long long d = 1LL * nums[i] - nums[j];
                int cnt = f[j][d];
                ans += cnt;
                f[i][d] += cnt + 1;
            }
        }
        return ans;
    }
};
```

```go
func numberOfArithmeticSlices(nums []int) (ans int) {
	f := make([]map[int]int, len(nums))
	for i := range f {
		f[i] = map[int]int{}
	}
	for i, x := range nums {
		for j, y := range nums[:i] {
			d := x - y
			cnt := f[j][d]
			ans += cnt
			f[i][d] += cnt + 1
		}
	}
	return
}
```

```ts
function numberOfArithmeticSlices(nums: number[]): number {
    const n = nums.length;
    const f: Map<number, number>[] = new Array(n).fill(0).map(() => new Map());
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const d = nums[i] - nums[j];
            const cnt = f[j].get(d) || 0;
            ans += cnt;
            f[i].set(d, (f[i].get(d) || 0) + cnt + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
