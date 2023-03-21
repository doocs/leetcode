# [2570. 合并两个二维数组 - 求和法](https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values)

[English Version](/solution/2500-2599/2570.Merge%20Two%202D%20Arrays%20by%20Summing%20Values/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>二维</strong> 整数数组 <code>nums1</code> 和 <code>nums2.</code></p>

<ul>
	<li><code>nums1[i] = [id<sub>i</sub>, val<sub>i</sub>]</code> 表示编号为 <code>id<sub>i</sub></code> 的数字对应的值等于 <code>val<sub>i</sub></code> 。</li>
	<li><code>nums2[i] = [id<sub>i</sub>, val<sub>i</sub>]</code>&nbsp;表示编号为 <code>id<sub>i</sub></code> 的数字对应的值等于 <code>val<sub>i</sub></code> 。</li>
</ul>

<p>每个数组都包含 <strong>互不相同</strong> 的 id ，并按 id 以 <strong>递增</strong> 顺序排列。</p>

<p>请你将两个数组合并为一个按 id 以递增顺序排列的数组，并符合下述条件：</p>

<ul>
	<li>只有在两个数组中至少出现过一次的 id 才能包含在结果数组内。</li>
	<li>每个 id 在结果数组中 <strong>只能出现一次</strong> ，并且其对应的值等于两个数组中该 id 所对应的值求和。如果某个数组中不存在该 id ，则认为其对应的值等于 <code>0</code> 。</li>
</ul>

<p>返回结果数组。返回的数组需要按 id 以递增顺序排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
<strong>输出：</strong>[[1,6],[2,3],[3,2],[4,6]]
<strong>解释：</strong>结果数组中包含以下元素：
- id = 1 ，对应的值等于 2 + 4 = 6 。
- id = 2 ，对应的值等于 3 。
- id = 3 ，对应的值等于 2 。
- id = 4 ，对应的值等于5 + 1 = 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
<strong>输出：</strong>[[1,3],[2,4],[3,6],[4,3],[5,5]]
<strong>解释：</strong>不存在共同 id ，在结果数组中只需要包含每个 id 和其对应的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 200</code></li>
	<li><code>nums1[i].length == nums2[j].length == 2</code></li>
	<li><code>1 &lt;= id<sub>i</sub>, val<sub>i</sub> &lt;= 1000</code></li>
	<li>数组中的 id 互不相同</li>
	<li>数据均按 id 以严格递增顺序排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

我们可以用一个哈希表或数组 `cnt` 统计两个数组中每个数字出现的次数。

然后我们从小到大枚举 `cnt` 中的每个数字，如果该数字出现的次数大于 $0$，则将其加入答案数组中。

时间复杂度 $O(n + m)$，空间复杂度 $O(M)$。其中 $n$ 和 $m$ 分别是两个数组的长度；而 $M$ 是两个数组中数字的最大值，本题中 $M = 1000$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mergeArrays(
        self, nums1: List[List[int]], nums2: List[List[int]]
    ) -> List[List[int]]:
        cnt = Counter()
        for i, v in nums1 + nums2:
            cnt[i] += v
        return sorted(cnt.items())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[] cnt = new int[1001];
        for (var x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (var x : nums2) {
            cnt[x[0]] += x[1];
        }
        int n = 0;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ++n;
            }
        }
        int[][] ans = new int[n][2];
        for (int i = 0, j = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ans[j++] = new int[] {i, cnt[i]};
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
    vector<vector<int>> mergeArrays(vector<vector<int>>& nums1, vector<vector<int>>& nums2) {
        int cnt[1001]{};
        for (auto& x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (auto& x : nums2) {
            cnt[x[0]] += x[1];
        }
        vector<vector<int>> ans;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i]) {
                ans.push_back({i, cnt[i]});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mergeArrays(nums1 [][]int, nums2 [][]int) (ans [][]int) {
	cnt := [1001]int{}
	for _, x := range nums1 {
		cnt[x[0]] += x[1]
	}
	for _, x := range nums2 {
		cnt[x[0]] += x[1]
	}
	for i, x := range cnt {
		if x > 0 {
			ans = append(ans, []int{i, x})
		}
	}
	return
}
```

### **TypeScript**

```ts
function mergeArrays(nums1: number[][], nums2: number[][]): number[][] {
    const n = 1001;
    const cnt = new Array(n).fill(0);
    for (const [a, b] of nums1) {
        cnt[a] += b;
    }
    for (const [a, b] of nums2) {
        cnt[a] += b;
    }
    const ans: number[][] = [];
    for (let i = 0; i < n; ++i) {
        if (cnt[i] > 0) {
            ans.push([i, cnt[i]]);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
