# [1774. 最接近目标价格的甜点成本](https://leetcode.cn/problems/closest-dessert-cost)

[English Version](/solution/1700-1799/1774.Closest%20Dessert%20Cost/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你打算做甜点，现在需要购买配料。目前共有 <code>n</code> 种冰激凌基料和 <code>m</code> 种配料可供选购。而制作甜点需要遵循以下几条规则：</p>

<ul>
	<li>必须选择 <strong>一种</strong> 冰激凌基料。</li>
	<li>可以添加 <strong>一种或多种</strong> 配料，也可以不添加任何配料。</li>
	<li>每种类型的配料 <strong>最多两份</strong> 。</li>
</ul>

<p>给你以下三个输入：</p>

<ul>
	<li><code>baseCosts</code> ，一个长度为 <code>n</code> 的整数数组，其中每个 <code>baseCosts[i]</code> 表示第 <code>i</code> 种冰激凌基料的价格。</li>
	<li><code>toppingCosts</code>，一个长度为 <code>m</code> 的整数数组，其中每个 <code>toppingCosts[i]</code> 表示 <strong>一份</strong> 第 <code>i</code> 种冰激凌配料的价格。</li>
	<li><code>target</code> ，一个整数，表示你制作甜点的目标价格。</li>
</ul>

<p>你希望自己做的甜点总成本尽可能接近目标价格 <code>target</code> 。</p>

<p>返回最接近<em> </em><code>target</code> 的甜点成本。如果有多种方案，返回 <strong>成本相对较低</strong> 的一种。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>baseCosts = [1,7], toppingCosts = [3,4], target = 10
<strong>输出：</strong>10
<strong>解释：</strong>考虑下面的方案组合（所有下标均从 0 开始）：
- 选择 1 号基料：成本 7
- 选择 1 份 0 号配料：成本 1 x 3 = 3
- 选择 0 份 1 号配料：成本 0 x 4 = 0
总成本：7 + 3 + 0 = 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
<strong>输出：</strong>17
<strong>解释：</strong>考虑下面的方案组合（所有下标均从 0 开始）：
- 选择 1 号基料：成本 3
- 选择 1 份 0 号配料：成本 1 x 4 = 4
- 选择 2 份 1 号配料：成本 2 x 5 = 10
- 选择 0 份 2 号配料：成本 0 x 100 = 0
总成本：3 + 4 + 10 + 0 = 17 。不存在总成本为 18 的甜点制作方案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>baseCosts = [3,10], toppingCosts = [2,5], target = 9
<strong>输出：</strong>8
<strong>解释：</strong>可以制作总成本为 8 和 10 的甜点。返回 8 ，因为这是成本更低的方案。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>baseCosts = [10], toppingCosts = [1], target = 1
<strong>输出：</strong>10
<strong>解释：</strong>注意，你可以选择不添加任何配料，但你必须选择一种基料。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == baseCosts.length</code></li>
	<li><code>m == toppingCosts.length</code></li>
	<li><code>1 <= n, m <= 10</code></li>
	<li><code>1 <= baseCosts[i], toppingCosts[i] <= 10<sup>4</sup></code></li>
	<li><code>1 <= target <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举子集和 + 排序 + 二分查找**

每种类型的配料最多可以选两份，因此，我们可以复制一份配料，然后利用 `DFS` 枚举子集之和。在实现上，我们可以只枚举一半的配料的所有子集和，然后在另一半配料子集和中，利用二分查找找到最接近的配料。

时间复杂度 $O(n\times 2^m \times \log {2^m})$。

相似题目：[1755. 最接近目标值的子序列和](/solution/1700-1799/1755.Closest%20Subsequence%20Sum/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closestCost(self, baseCosts: List[int], toppingCosts: List[int], target: int) -> int:
        def dfs(i, t):
            if i >= len(toppingCosts):
                arr.append(t)
                return
            dfs(i + 1, t)
            dfs(i + 1, t + toppingCosts[i])

        arr = []
        dfs(0, 0)
        arr.sort()
        d = ans = inf

        # 选择一种冰激淋基料
        for x in baseCosts:
            # 枚举子集和
            for y in arr:
                # 二分查找
                i = bisect_left(arr, target - x - y)
                for j in (i, i - 1):
                    if 0 <= j < len(arr):
                        t = abs(x + y + arr[j] - target)
                        if d > t or (d == t and ans > x + y + arr[j]):
                            d = t
                            ans = x + y + arr[j]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<Integer> arr = new ArrayList<>();
    private int[] ts;
    private int inf = 1 << 30;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        ts = toppingCosts;
        dfs(0, 0);
        Collections.sort(arr);
        int d = inf, ans = inf;

        // 选择一种冰激淋基料
        for (int x : baseCosts) {
            // 枚举子集和
            for (int y : arr) {
                // 二分查找
                int i = search(target - x - y);
                for (int j : new int[] {i, i - 1}) {
                    if (j >= 0 && j < arr.size()) {
                        int t = Math.abs(x + y + arr.get(j) - target);
                        if (d > t || (d == t && ans > x + y + arr.get(j))) {
                            d = t;
                            ans = x + y + arr.get(j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private int search(int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private void dfs(int i, int t) {
        if (i >= ts.length) {
            arr.add(t);
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t + ts[i]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int inf = INT_MAX;
    int closestCost(vector<int>& baseCosts, vector<int>& toppingCosts, int target) {
        vector<int> arr;
        function<void(int, int)> dfs = [&](int i, int t) {
            if (i >= toppingCosts.size()) {
                arr.push_back(t);
                return;
            }
            dfs(i + 1, t);
            dfs(i + 1, t + toppingCosts[i]);
        };
        dfs(0, 0);
        sort(arr.begin(), arr.end());
        int d = inf, ans = inf;
        // 选择一种冰激淋基料
        for (int x : baseCosts) {
            // 枚举子集和
            for (int y : arr) {
                // 二分查找
                int i = lower_bound(arr.begin(), arr.end(), target - x - y) - arr.begin();
                for (int j = i - 1; j < i + 1; ++j) {
                    if (j >= 0 && j < arr.size()) {
                        int t = abs(x + y + arr[j] - target);
                        if (d > t || (d == t && ans > x + y + arr[j])) {
                            d = t;
                            ans = x + y + arr[j];
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func closestCost(baseCosts []int, toppingCosts []int, target int) int {
	arr := []int{}
	var dfs func(int, int)
	dfs = func(i, t int) {
		if i >= len(toppingCosts) {
			arr = append(arr, t)
			return
		}
		dfs(i+1, t)
		dfs(i+1, t+toppingCosts[i])
	}
	dfs(0, 0)
	sort.Ints(arr)
	const inf = 1 << 30
	ans, d := inf, inf
    // 选择一种冰激淋基料
	for _, x := range baseCosts {
        // 枚举子集和
		for _, y := range arr {
            // 二分查找
			i := sort.Search(len(arr), func(i int) bool { return arr[i] >= target-x-y })
			for j := i - 1; j < i+1; j++ {
				if j >= 0 && j < len(arr) {
					t := abs(x + y + arr[j] - target)
					if d > t || (d == t && ans > x+y+arr[j]) {
						d = t
						ans = x + y + arr[j]
					}
				}
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
