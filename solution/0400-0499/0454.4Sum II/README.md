# [454. 四数相加 II](https://leetcode.cn/problems/4sum-ii)

[English Version](/solution/0400-0499/0454.4Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你四个整数数组 <code>nums1</code>、<code>nums2</code>、<code>nums3</code> 和 <code>nums4</code> ，数组长度都是 <code>n</code> ，请你计算有多少个元组 <code>(i, j, k, l)</code> 能满足：</p>

<ul>
	<li><code>0 &lt;= i, j, k, l &lt; n</code></li>
	<li><code>nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
<strong>输出：</strong>2
<strong>解释：</strong>
两个元组如下：
1. (0, 0, 0, 1) -&gt; nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -&gt; nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p>&nbsp; <strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>n == nums3.length</code></li>
	<li><code>n == nums4.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-2<sup>28</sup> &lt;= nums1[i], nums2[i], nums3[i], nums4[i] &lt;= 2<sup>28</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以将数组 $nums1$ 和 $nums2$ 中的元素 $a$ 和 $b$ 相加，将所有可能的和存储在哈希表 $cnt$ 中，其中键为两数之和，值为两数之和出现的次数。

然后我们遍历数组 $nums3$ 和 $nums4$ 中的元素 $c$ 和 $d$，令 $c+d$ 为目标值，那么答案即为 $cnt[-(c+d)]$ 的累加和。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fourSumCount(
        self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]
    ) -> int:
        cnt = Counter(a + b for a in nums1 for b in nums2)
        return sum(cnt[-(c + d)] for c in nums3 for d in nums4)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                cnt.merge(a + b, 1, Integer::sum);
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += cnt.getOrDefault(-(c + d), 0);
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
    int fourSumCount(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3, vector<int>& nums4) {
        unordered_map<int, int> cnt;
        for (int a : nums1) {
            for (int b : nums2) {
                ++cnt[a + b];
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += cnt[-(c + d)];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) (ans int) {
	cnt := map[int]int{}
	for _, a := range nums1 {
		for _, b := range nums2 {
			cnt[a+b]++
		}
	}
	for _, c := range nums3 {
		for _, d := range nums4 {
			ans += cnt[-(c + d)]
		}
	}
	return
}
```

### **TypeScript**

```ts
function fourSumCount(
    nums1: number[],
    nums2: number[],
    nums3: number[],
    nums4: number[],
): number {
    const cnt: Map<number, number> = new Map();
    for (const a of nums1) {
        for (const b of nums2) {
            const x = a + b;
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    let ans = 0;
    for (const c of nums3) {
        for (const d of nums4) {
            const x = c + d;
            ans += cnt.get(-x) || 0;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
