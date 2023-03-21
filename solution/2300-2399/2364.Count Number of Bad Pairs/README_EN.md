# [2364. Count Number of Bad Pairs](https://leetcode.com/problems/count-number-of-bad-pairs)

[中文文档](/solution/2300-2399/2364.Count%20Number%20of%20Bad%20Pairs/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A pair of indices <code>(i, j)</code> is a <strong>bad pair</strong> if <code>i &lt; j</code> and <code>j - i != nums[j] - nums[i]</code>.</p>

<p>Return<em> the total number of <strong>bad pairs</strong> in </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,3,3]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
There are a total of 5 bad pairs, so we return 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no bad pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countBadPairs(self, nums: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for i, x in enumerate(nums):
            ans += i - cnt[i - x]
            cnt[i - x] += 1
        return ans
```

### **Java**

```java
class Solution {
    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int x = i - nums[i];
            ans += i - cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countBadPairs(vector<int>& nums) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int x = i - nums[i];
            ans += i - cnt[x];
            ++cnt[x];
        }
        return ans;
    }
};
```

### **Go**

```go
func countBadPairs(nums []int) (ans int64) {
	cnt := map[int]int{}
	for i, x := range nums {
		x = i - x
		ans += int64(i - cnt[x])
		cnt[x]++
	}
	return
}
```

### **TypeScript**

```ts
function countBadPairs(nums: number[]): number {
    const cnt = new Map<number, number>();
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = i - nums[i];
        ans += i - (cnt.get(x) ?? 0);
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
