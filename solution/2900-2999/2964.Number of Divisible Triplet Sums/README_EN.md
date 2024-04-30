# [2964. Number of Divisible Triplet Sums 🔒](https://leetcode.com/problems/number-of-divisible-triplet-sums)

[中文文档](/solution/2900-2999/2964.Number%20of%20Divisible%20Triplet%20Sums/README.md)

<!-- tags:Array,Hash Table -->

## Description

Given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>d</code>, return <em>the number of triplets</em> <code>(i, j, k)</code> <em>such that</em> <code>i &lt; j &lt; k</code> <em>and</em> <code>(nums[i] + nums[j] + nums[k]) % d == 0</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,4,7,8], d = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong> The triplets which are divisible by 5 are: (0, 1, 2), (0, 2, 4), (1, 2, 4).
It can be shown that no other triplet is divisible by 5. Hence, the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3], d = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> Any triplet chosen here has a sum of 9, which is divisible by 3. Hence, the answer is the total number of triplets which is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3], d = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> Any triplet chosen here has a sum of 9, which is not divisible by 6. Hence, the answer is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= d &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Enumeration

We can use a hash table $cnt$ to record the occurrence times of $nums[i] \bmod d$, then enumerate $j$ and $k$, calculate the value of $nums[i] \bmod d$ that makes the equation $(nums[i] + nums[j] + nums[k]) \bmod d = 0$ hold, which is $(d - (nums[j] + nums[k]) \bmod d) \bmod d$, and accumulate its occurrence times to the answer. Then we increase the occurrence times of $nums[j] \bmod d$ by one. Continue to enumerate $j$ and $k$ until $j$ reaches the end of the array.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def divisibleTripletCount(self, nums: List[int], d: int) -> int:
        cnt = defaultdict(int)
        ans, n = 0, len(nums)
        for j in range(n):
            for k in range(j + 1, n):
                x = (d - (nums[j] + nums[k]) % d) % d
                ans += cnt[x]
            cnt[nums[j] % d] += 1
        return ans
```

```java
class Solution {
    public int divisibleTripletCount(int[] nums, int d) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, n = nums.length;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int x = (d - (nums[j] + nums[k]) % d) % d;
                ans += cnt.getOrDefault(x, 0);
            }
            cnt.merge(nums[j] % d, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int divisibleTripletCount(vector<int>& nums, int d) {
        unordered_map<int, int> cnt;
        int ans = 0, n = nums.size();
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int x = (d - (nums[j] + nums[k]) % d) % d;
                ans += cnt[x];
            }
            cnt[nums[j] % d]++;
        }
        return ans;
    }
};
```

```go
func divisibleTripletCount(nums []int, d int) (ans int) {
	n := len(nums)
	cnt := map[int]int{}
	for j := 0; j < n; j++ {
		for k := j + 1; k < n; k++ {
			x := (d - (nums[j]+nums[k])%d) % d
			ans += cnt[x]
		}
		cnt[nums[j]%d]++
	}
	return
}
```

```ts
function divisibleTripletCount(nums: number[], d: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let j = 0; j < n; ++j) {
        for (let k = j + 1; k < n; ++k) {
            const x = (d - ((nums[j] + nums[k]) % d)) % d;
            ans += cnt.get(x) || 0;
        }
        cnt.set(nums[j] % d, (cnt.get(nums[j] % d) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
