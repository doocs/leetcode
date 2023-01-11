# [2342. Max Sum of a Pair With Equal Sum of Digits](https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits)

[中文文档](/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers. You can choose two indices <code>i</code> and <code>j</code>, such that <code>i != j</code>, and the sum of digits of the number <code>nums[i]</code> is equal to that of <code>nums[j]</code>.</p>

<p>Return <em>the <strong>maximum</strong> value of </em><code>nums[i] + nums[j]</code><em> that you can obtain over all possible indices </em><code>i</code><em> and </em><code>j</code><em> that satisfy the conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [18,43,36,13,7]
<strong>Output:</strong> 54
<strong>Explanation:</strong> The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        for v in nums:
            x, y = v, 0
            while x:
                y += x % 10
                x //= 10
            d[y].append(v)
        ans = -1
        for vs in d.values():
            if len(vs) > 1:
                vs.sort(reverse=True)
                ans = max(ans, vs[0] + vs[1])
        return ans
```

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        ans = -1
        d = defaultdict(int)
        for v in nums:
            x, y = v, 0
            while x:
                y += x % 10
                x //= 10
            if y in d:
                ans = max(ans, d[y] + v)
            d[y] = max(d[y], v)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumSum(int[] nums) {
        List<Integer>[] d = new List[100];
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int v : nums) {
            int y = 0;
            for (int x = v; x > 0; x /= 10) {
                y += x % 10;
            }
            d[y].add(v);
        }
        int ans = -1;
        for (var vs : d) {
            int m = vs.size();
            if (m > 1) {
                Collections.sort(vs);
                ans = Math.max(ans, vs.get(m - 1) + vs.get(m - 2));
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumSum(int[] nums) {
        int ans = -1;
        int[] d = new int[100];
        for (int v : nums) {
            int y = 0;
            for (int x = v; x > 0; x /= 10) {
                y += x % 10;
            }
            if (d[y] > 0) {
                ans = Math.max(ans, d[y] + v);
            }
            d[y] = Math.max(d[y], v);
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
        vector<vector<int>> d(100);
        for (int& v : nums) {
            int y = 0;
            for (int x = v; x > 0; x /= 10) {
                y += x % 10;
            }
            d[y].emplace_back(v);
        }
        int ans = -1;
        for (auto& vs : d) {
            if (vs.size() > 1) {
                sort(vs.rbegin(), vs.rend());
                ans = max(ans, vs[0] + vs[1]);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maximumSum(vector<int>& nums) {
        int ans = -1;
        int d[100]{};
        for (int& v : nums) {
            int y = 0;
            for (int x = v; x; x /= 10) {
                y += x % 10;
            }
            if (d[y]) {
                ans = max(ans, d[y] + v);
            }
            d[y] = max(d[y], v);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumSum(nums []int) int {
	d := [100][]int{}
	for _, v := range nums {
		y := 0
		for x := v; x > 0; x /= 10 {
			y += x % 10
		}
		d[y] = append(d[y], v)
	}
	ans := -1
	for _, vs := range d {
		m := len(vs)
		if m > 1 {
			sort.Ints(vs)
			ans = max(ans, vs[m-1]+vs[m-2])
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

```go
func maximumSum(nums []int) int {
	ans := -1
	d := [100]int{}
	for _, v := range nums {
		y := 0
		for x := v; x > 0; x /= 10 {
			y += x % 10
		}
		if d[y] > 0 {
			ans = max(ans, d[y]+v)
		}
		d[y] = max(d[y], v)
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
