# [1889. 装包裹的最小浪费空间](https://leetcode.cn/problems/minimum-space-wasted-from-packaging)

[English Version](/solution/1800-1899/1889.Minimum%20Space%20Wasted%20From%20Packaging/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>n</code> 个包裹，你需要把它们装在箱子里，<strong>每个箱子装一个包裹</strong>。总共有 <code>m</code> 个供应商提供 <strong>不同尺寸</strong> 的箱子（每个规格都有无数个箱子）。如果一个包裹的尺寸 <strong>小于等于</strong> 一个箱子的尺寸，那么这个包裹就可以放入这个箱子之中。</p>

<p>包裹的尺寸用一个整数数组 <code>packages</code> 表示，其中 <code>packages[i]</code> 是第 <code>i</code> 个包裹的尺寸。供应商用二维数组 <code>boxes</code> 表示，其中 <code>boxes[j]</code> 是第 <code>j</code> 个供应商提供的所有箱子尺寸的数组。</p>

<p>你想要选择 <strong>一个供应商</strong> 并只使用该供应商提供的箱子，使得 <strong>总浪费空间最小</strong> 。对于每个装了包裹的箱子，我们定义 <strong>浪费的</strong> 空间等于 <code>箱子的尺寸 - 包裹的尺寸</code> 。<strong>总浪费空间</strong> 为 <strong>所有</strong> 箱子中浪费空间的总和。</p>

<ul>
	<li>比方说，如果你想要用尺寸数组为 <code>[4,8]</code> 的箱子装下尺寸为 <code>[2,3,5]</code> 的包裹，你可以将尺寸为 <code>2</code> 和 <code>3</code> 的两个包裹装入两个尺寸为 <code>4</code> 的箱子中，同时把尺寸为 <code>5</code> 的包裹装入尺寸为 <code>8</code> 的箱子中。总浪费空间为 <code>(4-2) + (4-3) + (8-5) = 6</code> 。</li>
</ul>

<p>请你选择 <strong>最优</strong> 箱子供应商，使得 <strong>总浪费空间最小</strong> 。如果 <strong>无法</strong> 将所有包裹放入箱子中，请你返回 <code>-1</code> 。由于答案可能会 <strong>很大</strong> ，请返回它对<strong> </strong><code>10<sup>9</sup> + 7</code> <b>取余</b> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>packages = [2,3,5], boxes = [[4,8],[2,8]]
<b>输出：</b>6
<b>解释：</b>选择第一个供应商最优，用两个尺寸为 4 的箱子和一个尺寸为 8 的箱子。
总浪费空间为 (4-2) + (4-3) + (8-5) = 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>packages = [2,3,5], boxes = [[1,4],[2,3],[3,4]]
<b>输出：</b>-1
<b>解释：</b>没有箱子能装下尺寸为 5 的包裹。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>packages = [3,5,8,10,11,12], boxes = [[12],[11,9],[10,5,14]]
<b>输出：</b>9
<b>解释：</b>选择第三个供应商最优，用两个尺寸为 5 的箱子，两个尺寸为 10 的箱子和两个尺寸为 14 的箱子。
总浪费空间为 (5-3) + (5-5) + (10-8) + (10-10) + (14-11) + (14-12) = 9 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == packages.length</code></li>
	<li><code>m == boxes.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= m <= 10<sup>5</sup></code></li>
	<li><code>1 <= packages[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= boxes[j].length <= 10<sup>5</sup></code></li>
	<li><code>1 <= boxes[j][k] <= 10<sup>5</sup></code></li>
	<li><code>sum(boxes[j].length) <= 10<sup>5</sup></code></li>
	<li><code>boxes[j]</code> 中的元素 <strong>互不相同</strong> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们首先对包裹的尺寸数组 $packages$ 进行排序。

然后遍历每个供应商的箱子数组 $boxes$，对于每个箱子数组 $boxes$，我们也对其进行排序。

如果当前箱子数组中最大的箱子尺寸小于包裹数组中最大的包裹尺寸，那么我们就可以跳过这个供应商，因为这个供应商的箱子无法装下最大的包裹。否则，我们从小到大遍历当前供应商的每个箱子 $b$，用一个指针 $i$ 指向当前待装箱的包裹。

接下来，我们在数组 $packages$ 中二分查找，找到第一个尺寸大于 $b$ 的包裹 $packages[j]$。那么我们可以用箱子 $b$ 装下 $packages[i..j-1]$ 中的所有包裹，此时总空间为 $b \times (j - i)$。然后我们将指针 $i$ 移动到 $j$，继续寻找下一个箱子的装包情况。

遍历完该供应商的所有箱子后，我们就可以得到该供应商所需的箱子总空间。我们遍历所有供应商，找到所需箱子的最小总空间，那么浪费的空间就是总空间减去所有包裹的总尺寸。

时间复杂度 $O(n \times \log n + l \times \log l + l \times \log n)$，空间复杂度 $O(\log n + \log l)$。其中 $n$ 是包裹的数量，而 $l$ 是所有箱子的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minWastedSpace(self, packages: List[int], boxes: List[List[int]]) -> int:
        mod = 10**9 + 7
        ans = inf
        packages.sort()
        for box in boxes:
            box.sort()
            if packages[-1] > box[-1]:
                continue
            s = i = 0
            for b in box:
                j = bisect_right(packages, b, lo=i)
                s += (j - i) * b
                i = j
            ans = min(ans, s)
        if ans == inf:
            return -1
        return (ans - sum(packages)) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        final long inf = 1L << 62;
        Arrays.sort(packages);
        long ans = inf;
        for (var box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long s = 0;
            int i = 0;
            for (int b : box) {
                int j = search(packages, b, i);
                s += 1L * (j - i) * b;
                i = j;
            }
            ans = Math.min(ans, s);
        }
        if (ans == inf) {
            return -1;
        }
        long s = 0;
        for (int p : packages) {
            s += p;
        }
        final int mod = (int) 1e9 + 7;
        return (int) ((ans - s) % mod);
    }

    private int search(int[] nums, int x, int l) {
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minWastedSpace(vector<int>& packages, vector<vector<int>>& boxes) {
        int n = packages.size(), m = boxes.size();
        sort(packages.begin(), packages.end());
        const int mod = 1e9 + 7;
        const long long inf = 1LL << 62;
        long long ans = inf;
        for (auto& box : boxes) {
            sort(box.begin(), box.end());
            if (packages.back() > box.back()) {
                continue;
            }
            int i = 0;
            long long s = 0;
            for (auto& b : box) {
                int j = upper_bound(packages.begin() + i, packages.end(), b) - packages.begin();
                s += 1LL * (j - i) * b;
                i = j;
            }
            ans = min(ans, s);
        }
        return ans == inf ? -1 : (ans - accumulate(packages.begin(), packages.end(), 0LL)) % mod;
    }
};
```

### **Go**

```go
func minWastedSpace(packages []int, boxes [][]int) int {
	n := len(packages)
	inf := 1 << 62
	sort.Ints(packages)
	ans := inf
	for _, box := range boxes {
		sort.Ints(box)
		if packages[n-1] > box[len(box)-1] {
			continue
		}
		s, i := 0, 0
		for _, b := range box {
			j := sort.SearchInts(packages[i:], b+1) + i
			s += (j - i) * b
			i = j
		}
		ans = min(ans, s)
	}
	if ans == inf {
		return -1
	}
	s := 0
	for _, p := range packages {
		s += p
	}
	const mod = 1e9 + 7
	return (ans - s) % mod
}
```

### **TypeScript**

```ts
function minWastedSpace(packages: number[], boxes: number[][]): number {
    const n = packages.length;
    const inf = Infinity;
    packages.sort((a, b) => a - b);
    let ans = inf;
    for (const box of boxes) {
        box.sort((a, b) => a - b);
        if (packages[n - 1] > box[box.length - 1]) {
            continue;
        }
        let s = 0;
        let i = 0;
        for (const b of box) {
            const j = search(packages, b, i);
            s += (j - i) * b;
            i = j;
        }
        ans = Math.min(ans, s);
    }
    if (ans === inf) {
        return -1;
    }
    const s = packages.reduce((a, b) => a + b, 0);
    return (ans - s) % 1000000007;
}

function search(nums: number[], x: number, l: number): number {
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

### **...**

```

```

<!-- tabs:end -->
