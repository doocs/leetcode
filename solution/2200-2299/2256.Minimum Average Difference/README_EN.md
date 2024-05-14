# [2256. Minimum Average Difference](https://leetcode.com/problems/minimum-average-difference)

[中文文档](/solution/2200-2299/2256.Minimum%20Average%20Difference/README.md)

<!-- tags:Array,Prefix Sum -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>average difference</strong> of the index <code>i</code> is the <strong>absolute</strong> <strong>difference</strong> between the average of the <strong>first</strong> <code>i + 1</code> elements of <code>nums</code> and the average of the <strong>last</strong> <code>n - i - 1</code> elements. Both averages should be <strong>rounded down</strong> to the nearest integer.</p>

<p>Return<em> the index with the <strong>minimum average difference</strong></em>. If there are multiple such indices, return the <strong>smallest</strong> one.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>absolute difference</strong> of two numbers is the absolute value of their difference.</li>
	<li>The <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided (<strong>integer division</strong>) by <code>n</code>.</li>
	<li>The average of <code>0</code> elements is considered to be <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5,3,9,5,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
- The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
- The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
- The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
- The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
- The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
The average difference of index 3 is the minimum average difference so return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The only index is 0 so return 0.
The average difference of index 0 is: |0 / 1 - 0| = |0 - 0| = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Traverse

We directly traverse the array $nums$. For each index $i$, we maintain the sum of the first $i+1$ elements $pre$ and the sum of the last $n-i-1$ elements $suf$. We calculate the absolute difference of the average of the first $i+1$ elements and the average of the last $n-i-1$ elements, denoted as $t$. If $t$ is less than the current minimum value $mi$, we update the answer $ans=i$ and the minimum value $mi=t$.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumAverageDifference(self, nums: List[int]) -> int:
        pre, suf = 0, sum(nums)
        n = len(nums)
        ans, mi = 0, inf
        for i, x in enumerate(nums):
            pre += x
            suf -= x
            a = pre // (i + 1)
            b = 0 if n - i - 1 == 0 else suf // (n - i - 1)
            if (t := abs(a - b)) < mi:
                ans = i
                mi = t
        return ans
```

```java
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long pre = 0, suf = 0;
        for (int x : nums) {
            suf += x;
        }
        int ans = 0;
        long mi = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            long a = pre / (i + 1);
            long b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            long t = Math.abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll pre = 0;
        ll suf = accumulate(nums.begin(), nums.end(), 0LL);
        int ans = 0;
        ll mi = suf;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            ll a = pre / (i + 1);
            ll b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            ll t = abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
};
```

```go
func minimumAverageDifference(nums []int) (ans int) {
	n := len(nums)
	pre, suf := 0, 0
	for _, x := range nums {
		suf += x
	}
	mi := suf
	for i, x := range nums {
		pre += x
		suf -= x
		a := pre / (i + 1)
		b := 0
		if n-i-1 != 0 {
			b = suf / (n - i - 1)
		}
		if t := abs(a - b); t < mi {
			ans = i
			mi = t
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function minimumAverageDifference(nums: number[]): number {
    const n = nums.length;
    let pre = 0;
    let suf = nums.reduce((a, b) => a + b);
    let ans = 0;
    let mi = suf;
    for (let i = 0; i < n; ++i) {
        pre += nums[i];
        suf -= nums[i];
        const a = Math.floor(pre / (i + 1));
        const b = n - i - 1 === 0 ? 0 : Math.floor(suf / (n - i - 1));
        const t = Math.abs(a - b);
        if (t < mi) {
            ans = i;
            mi = t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
