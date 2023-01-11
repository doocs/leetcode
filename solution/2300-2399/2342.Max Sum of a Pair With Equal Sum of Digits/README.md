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

**方法一：哈希表 + 排序**

对于数组中的每个元素，计算其数位和，将其存入哈希表 $d$ 中，哈希表的键为数位和，值为数组中所有数位和为该键的元素组成的数组。

遍历哈希表 $d$，对于每个键值对，如果该键对应的数组长度大于 $1$，则对该数组进行降序排序，取前两个元素相加，更新答案。

最终返回答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：哈希表（优化）**

我们创建一个哈希表 $d$，其中哈希表的键为数位和，值为已遍历过的元素中数位和为该键的最大元素。

我们直接对数组 `nums` 进行遍历，对于每个元素 $v$，计算其数位和 $y$，如果 $d[y]$ 存在，则更新答案为 $max(ans, v + d[y])$。然后我们更新 $d[y]=max(d[y], v)$。

最终返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为数组 `nums` 的长度，而 $C$ 为数组 `nums` 的最大数位和。本题中 $nums[i] \leq 10^9$，因此我们固定取 $C=100$ 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
