# [2364. Count Number of Bad Pairs](https://leetcode.com/problems/count-number-of-bad-pairs)

[中文文档](/solution/2300-2399/2364.Count%20Number%20of%20Bad%20Pairs/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A pair of indices <code>(i, j)</code> is a <strong>bad pair</strong> if <code>i &lt; j</code> and <code>j - i != nums[j] - nums[i]</code>.</p>

<p>Return<em> the total number of <strong>bad pairs</strong> in </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

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
        arr = [i - v for i, v in enumerate(nums)]
        cnt = Counter(arr)
        n = len(arr)
        return sum(v * (n - v) for v in cnt.values()) >> 1
```

### **Java**

```java
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] = i - nums[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        long ans = 0;
        for (int v : cnt.values()) {
            ans += v * (n - v);
        }
        return ans >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countBadPairs(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) nums[i] = i - nums[i];
        unordered_map<int, int> cnt;
        for (int v : nums) cnt[v]++;
        long long ans = 0;
        for (auto [_, v] : cnt) ans += 1ll * v * (n - v);
        return ans >> 1;
    }
};
```

### **Go**

```go
func countBadPairs(nums []int) int64 {
	n := len(nums)
	for i := range nums {
		nums[i] = i - nums[i]
	}
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	ans := 0
	for _, v := range cnt {
		ans += v * (n - v)
	}
	ans >>= 1
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
