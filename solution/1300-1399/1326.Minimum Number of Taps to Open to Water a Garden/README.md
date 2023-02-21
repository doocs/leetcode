# [1326. 灌溉花园的最少水龙头数目](https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden)

[English Version](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 x 轴上有一个一维的花园。花园长度为&nbsp;<code>n</code>，从点&nbsp;<code>0</code>&nbsp;开始，到点&nbsp;<code>n</code>&nbsp;结束。</p>

<p>花园里总共有&nbsp;<code>n + 1</code> 个水龙头，分别位于&nbsp;<code>[0, 1, ..., n]</code> 。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个长度为&nbsp;<code>n + 1</code> 的整数数组&nbsp;<code>ranges</code>&nbsp;，其中&nbsp;<code>ranges[i]</code> （下标从 0 开始）表示：如果打开点&nbsp;<code>i</code>&nbsp;处的水龙头，可以灌溉的区域为&nbsp;<code>[i -&nbsp; ranges[i], i + ranges[i]]</code>&nbsp;。</p>

<p>请你返回可以灌溉整个花园的&nbsp;<strong>最少水龙头数目</strong>&nbsp;。如果花园始终存在无法灌溉到的地方，请你返回&nbsp;<strong>-1</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/images/1685_example_1.png" /></p>

<pre>
<strong>输入：</strong>n = 5, ranges = [3,4,1,1,0,0]
<strong>输出：</strong>1
<strong>解释：
</strong>点 0 处的水龙头可以灌溉区间 [-3,3]
点 1 处的水龙头可以灌溉区间 [-3,5]
点 2 处的水龙头可以灌溉区间 [1,3]
点 3 处的水龙头可以灌溉区间 [2,4]
点 4 处的水龙头可以灌溉区间 [4,4]
点 5 处的水龙头可以灌溉区间 [5,5]
只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, ranges = [0,0,0,0]
<strong>输出：</strong>-1
<strong>解释：</strong>即使打开所有水龙头，你也无法灌溉整个花园。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>ranges.length == n + 1</code></li>
	<li><code>0 &lt;= ranges[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们注意到，对于所有能覆盖某个左端点的水龙头，选择能覆盖最远右端点的那个水龙头是最优的。

因此，我们可以先预处理数组 $ranges$，对于第 $i$ 个水龙头，它能覆盖的左端点 $l = max(0, i - ranges[i])$，右端点 $r = i + ranges[i]$，我们算出所有能覆盖左端点 $l$ 的水龙头中，右端点最大的那个位置，记录在数组 $last[i]$ 中。

然后我们定义以下三个变量，其中：

-   变量 $ans$ 表示最终答案，即最少水龙头数目；
-   变量 $mx$ 表示当前能覆盖的最远右端点；
-   变量 $pre$ 表示上一个水龙头覆盖的最远右端点。

我们在 $[0,...n-1]$ 的范围内遍历所有位置，对于当前位置 $i$，我们用 $last[i]$ 更新 $mx$，即 $mx = max(mx, last[i])$。

-   如果 $mx \leq i$，说明无法覆盖下一个位置，返回 $-1$。
-   如果 $pre = i$，说明需要使用一个新的子区间，因此我们将 $ans$ 加 $1$，并且更新 $pre = mx$。

遍历结束后，返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为花园的长度。

相似题目：

-   [45. 跳跃游戏 II](/solution/0000-0099/0045.Jump%20Game%20II/README.md)
-   [55. 跳跃游戏](/solution/0000-0099/0055.Jump%20Game/README.md)
-   [1024. 视频拼接](/solution/1000-1099/1024.Video%20Stitching/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minTaps(self, n: int, ranges: List[int]) -> int:
        last = [0] * (n + 1)
        for i, x in enumerate(ranges):
            l, r = max(0, i - x), i + x
            last[l] = max(last[l], r)

        ans = mx = pre = 0
        for i in range(n):
            mx = max(mx, last[i])
            if mx <= i:
                return -1
            if pre == i:
                ans += 1
                pre = mx
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] last = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            int l = Math.max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = Math.max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
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
    int minTaps(int n, vector<int>& ranges) {
        vector<int> last(n + 1);
        for (int i = 0; i < n + 1; ++i) {
            int l = max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minTaps(n int, ranges []int) (ans int) {
	last := make([]int, n+1)
	for i, x := range ranges {
		l, r := max(0, i-x), i+x
		last[l] = max(last[l], r)
	}
	var pre, mx int
	for i, j := range last[:n] {
		mx = max(mx, j)
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
	}
	return
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
function minTaps(n: number, ranges: number[]): number {
    const last = new Array(n + 1).fill(0);
    for (let i = 0; i < n + 1; ++i) {
        const l = Math.max(0, i - ranges[i]);
        const r = i + ranges[i];
        last[l] = Math.max(last[l], r);
    }
    let ans = 0;
    let mx = 0;
    let pre = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, last[i]);
        if (mx <= i) {
            return -1;
        }
        if (pre == i) {
            ++ans;
            pre = mx;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
