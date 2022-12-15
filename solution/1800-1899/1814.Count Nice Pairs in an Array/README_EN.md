# [1814. Count Nice Pairs in an Array](https://leetcode.com/problems/count-nice-pairs-in-an-array)

[中文文档](/solution/1800-1899/1814.Count%20Nice%20Pairs%20in%20an%20Array/README.md)

## Description

<p>You are given an array <code>nums</code> that consists of non-negative integers. Let us define <code>rev(x)</code> as the reverse of the non-negative integer <code>x</code>. For example, <code>rev(123) = 321</code>, and <code>rev(120) = 21</code>. A pair of indices <code>(i, j)</code> is <strong>nice</strong> if it satisfies all of the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; nums.length</code></li>
	<li><code>nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])</code></li>
</ul>

<p>Return <em>the number of nice pairs of indices</em>. Since that number can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [42,11,1,97]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [13,10,35,24,76]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        cnt = Counter(x - rev(x) for x in nums)
        ans = 0
        mod = 10**9 + 7
        for v in cnt.values():
            ans = (ans + v * (v - 1) // 2) % mod
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int y = x - rev(x);
            cnt.put(y, cnt.getOrDefault(y, 0) + 1);
        }
        long ans = 0;
        for (int v : cnt.values()) {
            ans = (ans + (long) v * (v - 1) / 2) % MOD;
        }
        return (int) ans;
    }

    private int rev(int x) {
        int y = 0;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countNicePairs(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            int y = x - rev(x);
            cnt[y]++;
        }
        long long ans = 0;
        for (auto& [_, v] : cnt) {
            ans = (ans + 1ll * v * (v - 1) / 2) % mod;
        }
        return ans;
    }

    int rev(int x) {
        int y = 0;
        while (x) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
};
```

### **Go**

```go
func countNicePairs(nums []int) (ans int) {
	const mod int = 1e9 + 7
	rev := func(x int) (y int) {
		for x > 0 {
			y = y*10 + x%10
			x /= 10
		}
		return
	}
	cnt := map[int]int{}
	for _, x := range nums {
		y := x - rev(x)
		cnt[y]++
	}
	for _, v := range cnt {
		ans = (ans + v*(v-1)/2) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
