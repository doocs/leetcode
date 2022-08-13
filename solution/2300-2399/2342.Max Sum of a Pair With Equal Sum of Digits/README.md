# [2342. 数位和相等数对的最大和](https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits)

[English Version](/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组中的元素都是 <strong>正</strong> 整数。请你选出两个下标 <code>i</code> 和 <code>j</code>（<code>i != j</code>），且 <code>nums[i]</code> 的数位和 与&nbsp; <code>nums[j]</code> 的数位和相等。</p>

<p>请你找出所有满足条件的下标 <code>i</code> 和 <code>j</code> ，找出并返回<em> </em><code>nums[i] + nums[j]</code><em> </em>可以得到的 <strong>最大值</strong> <em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [18,43,36,13,7]
<strong>输出：</strong>54
<strong>解释：</strong>满足条件的数对 (i, j) 为：
- (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
- (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
所以可以获得的最大和是 54 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,12,19,14]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在满足条件的数对，返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
