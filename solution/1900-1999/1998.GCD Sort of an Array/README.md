---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1998.GCD%20Sort%20of%20an%20Array/README.md
rating: 2429
source: 第 257 场周赛 Q4
tags:
    - 并查集
    - 数组
    - 数学
    - 数论
    - 排序
---

# [1998. 数组的最大公因数排序](https://leetcode.cn/problems/gcd-sort-of-an-array)

[English Version](/solution/1900-1999/1998.GCD%20Sort%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，你可以在 <code>nums</code> 上执行下述操作 <strong>任意次</strong> ：</p>

<ul>
	<li>如果 <code>gcd(nums[i], nums[j]) &gt; 1</code> ，交换 <code>nums[i]</code> 和 <code>nums[j]</code> 的位置。其中 <code>gcd(nums[i], nums[j])</code> 是&nbsp;<code>nums[i]</code> 和 <code>nums[j]</code> 的最大公因数。</li>
</ul>

<p>如果能使用上述交换方式将 <code>nums</code> 按 <strong>非递减顺序</strong> 排列，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,21,3]
<strong>输出：</strong>true
<strong>解释：</strong>可以执行下述操作完成对 [7,21,3] 的排序：
- 交换 7 和 21 因为 gcd(7,21) = 7 。nums = [<em><strong>21</strong></em>,<em><strong>7</strong></em>,3]
- 交换 21 和 3 因为 gcd(21,3) = 3 。nums = [<em><strong>3</strong></em>,7,<em><strong>21</strong></em>]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,2,6,2]
<strong>输出：</strong>false
<strong>解释：</strong>无法完成排序，因为 5 不能与其他元素交换。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [10,5,9,3,15]
<strong>输出：</strong>true
<strong>解释：</strong>
可以执行下述操作完成对 [10,5,9,3,15] 的排序：
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [<em><strong>15</strong></em>,5,9,3,<em><strong>10</strong></em>]
- 交换 15 和 3 因为 gcd(15,3) = 3 。nums = [<em><strong>3</strong></em>,5,9,<em><strong>15</strong></em>,10]
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [3,5,9,<em><strong>10</strong></em>,<em><strong>15</strong></em>]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def gcdSort(self, nums: List[int]) -> bool:
        n = 10**5 + 10
        p = list(range(n))
        f = defaultdict(list)
        mx = max(nums)
        for i in range(2, mx + 1):
            if f[i]:
                continue
            for j in range(i, mx + 1, i):
                f[j].append(i)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in nums:
            for j in f[i]:
                p[find(i)] = find(j)

        s = sorted(nums)
        for i, num in enumerate(nums):
            if s[i] != num and find(num) != find(s[i]):
                return False
        return True
```

```java
class Solution {
    private int[] p;

    public boolean gcdSort(int[] nums) {
        int n = 100010;
        p = new int[n];
        Map<Integer, List<Integer>> f = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        for (int i = 2; i <= mx; ++i) {
            if (f.containsKey(i)) {
                continue;
            }
            for (int j = i; j <= mx; j += i) {
                f.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
            }
        }
        for (int i : nums) {
            for (int j : f.get(i)) {
                p[find(i)] = find(j);
            }
        }
        int[] s = new int[nums.length];
        System.arraycopy(nums, 0, s, 0, nums.length);
        Arrays.sort(s);
        for (int i = 0; i < nums.length; ++i) {
            if (s[i] != nums[i] && find(nums[i]) != find(s[i])) {
                return false;
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    vector<int> p;

    bool gcdSort(vector<int>& nums) {
        int n = 100010;
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int mx = 0;
        for (auto num : nums) mx = max(mx, num);
        unordered_map<int, vector<int>> f;
        for (int i = 2; i <= mx; ++i) {
            if (!f[i].empty()) continue;
            for (int j = i; j <= mx; j += i) f[j].push_back(i);
        }
        for (int i : nums) {
            for (int j : f[i]) p[find(i)] = find(j);
        }
        vector<int> s = nums;
        sort(s.begin(), s.end());
        for (int i = 0; i < nums.size(); ++i) {
            if (s[i] != nums[i] && find(s[i]) != find(nums[i])) return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

```go
var p []int

func gcdSort(nums []int) bool {
	n := 100010
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mx := 0
	for _, num := range nums {
		mx = max(mx, num)
	}
	f := make([][]int, mx+1)
	for i := 2; i <= mx; i++ {
		if len(f[i]) > 0 {
			continue
		}
		for j := i; j <= mx; j += i {
			f[j] = append(f[j], i)
		}
	}
	for _, i := range nums {
		for _, j := range f[i] {
			p[find(i)] = find(j)
		}
	}
	s := make([]int, len(nums))
	for i, num := range nums {
		s[i] = num
	}
	sort.Ints(s)
	for i, num := range nums {
		if s[i] != num && find(s[i]) != find(num) {
			return false
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

<!-- tabs:end -->

<!-- end -->
