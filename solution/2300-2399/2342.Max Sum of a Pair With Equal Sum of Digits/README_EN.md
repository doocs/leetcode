# [2342. Max Sum of a Pair With Equal Sum of Digits](https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits)

[中文文档](/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers. You can choose two indices <code>i</code> and <code>j</code>, such that <code>i != j</code>, and the sum of digits of the number <code>nums[i]</code> is equal to that of <code>nums[j]</code>.</p>

<p>Return <em>the <strong>maximum</strong> value of </em><code>nums[i] + nums[j]</code><em> that you can obtain over all possible indices </em><code>i</code><em> and </em><code>j</code><em> that satisfy the conditions.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [18,43,36,13,7]
<strong>Output:</strong> 54
<strong>Explanation:</strong> The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,12,19,14]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no two numbers that satisfy the conditions, so we return -1.
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
    def maximumSum(self, nums: List[int]) -> int:
        d = defaultdict(list)
        for i, v in enumerate(nums):
            t = 0
            while v:
                t += v % 10
                v //= 10
            d[t].append(nums[i])
        ans = -1
        for v in d.values():
            v.sort(reverse=True)
            if len(v) > 1:
                ans = max(ans, v[0] + v[1])
        return ans
```

### **Java**

```java
class Solution {
    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            int t = 0;
            while (v != 0) {
                t += v % 10;
                v /= 10;
            }
            d.computeIfAbsent(t, k -> new ArrayList<>()).add(nums[i]);
        }
        int ans = -1;
        for (List<Integer> v : d.values()) {
            int n = v.size();
            if (n > 1) {
                Collections.sort(v);
                ans = Math.max(ans, v.get(n - 1) + v.get(n - 2));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSum(vector<int>& nums) {
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < nums.size(); ++i) {
            int v = nums[i];
            int t = 0;
            while (v) {
                t += v % 10;
                v /= 10;
            }
            d[t].push_back(nums[i]);
        }
        int ans = -1;
        for (auto& [_, v] : d) {
            int n = v.size();
            if (n > 1) {
                sort(v.begin(), v.end());
                ans = max(ans, v[n - 1] + v[n - 2]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumSum(nums []int) int {
	d := map[int][]int{}
	for i, v := range nums {
		t := 0
		for v > 0 {
			t += v % 10
			v /= 10
		}
		d[t] = append(d[t], nums[i])
	}
	ans := -1
	for _, v := range d {
		n := len(v)
		if n > 1 {
			sort.Ints(v)
			ans = max(ans, v[n-1]+v[n-2])
		}
	}
	return ans
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

```

### **...**

```

```

<!-- tabs:end -->
