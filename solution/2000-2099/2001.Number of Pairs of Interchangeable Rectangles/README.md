# [2001. 可互换矩形的组数](https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles)

[English Version](/solution/2000-2099/2001.Number%20of%20Pairs%20of%20Interchangeable%20Rectangles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用一个下标从 <strong>0</strong> 开始的二维整数数组&nbsp;<code>rectangles</code> 来表示 <code>n</code> 个矩形，其中 <code>rectangles[i] = [width<sub>i</sub>, height<sub>i</sub>]</code> 表示第 <code>i</code> 个矩形的宽度和高度。</p>

<p>如果两个矩形 <code>i</code> 和 <code>j</code>（<code>i &lt; j</code>）的宽高比相同，则认为这两个矩形 <strong>可互换</strong> 。更规范的说法是，两个矩形满足&nbsp;<code>width<sub>i</sub>/height<sub>i</sub> == width<sub>j</sub>/height<sub>j</sub></code>（使用实数除法而非整数除法），则认为这两个矩形 <strong>可互换</strong> 。</p>

<p>计算并返回&nbsp;<code>rectangles</code> 中有多少对 <strong>可互换 </strong>矩形。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[4,8],[3,6],[10,20],[15,30]]
<strong>输出：</strong>6
<strong>解释：</strong>下面按下标（从 0 开始）列出可互换矩形的配对情况：
- 矩形 0 和矩形 1 ：4/8 == 3/6
- 矩形 0 和矩形 2 ：4/8 == 10/20
- 矩形 0 和矩形 3 ：4/8 == 15/30
- 矩形 1 和矩形 2 ：3/6 == 10/20
- 矩形 1 和矩形 3 ：3/6 == 15/30
- 矩形 2 和矩形 3 ：10/20 == 15/30
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[4,5],[7,8]]
<strong>输出：</strong>0
<strong>解释：</strong>不存在成对的可互换矩形。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rectangles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>rectangles[i].length == 2</code></li>
	<li><code>1 &lt;= width<sub>i</sub>, height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 哈希表**

为了能够唯一表示矩形，我们需要将矩形的宽高比化简为最简分数。因此，我们可以求出每个矩形的宽高比的最大公约数，然后将宽高比化简为最简分数。接下来，我们使用哈希表统计每个最简分数的矩形数量，然后计算每个最简分数的矩形数量的组合数，即可得到答案。

时间复杂度 $O(n \log M)$，空间复杂度 $O(n)$。其中 $n$ 和 $M$ 分别是矩形的数量和矩形的最大边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def interchangeableRectangles(self, rectangles: List[List[int]]) -> int:
        ans = 0
        cnt = Counter()
        for w, h in rectangles:
            g = gcd(w, h)
            w, h = w // g, h // g
            ans += cnt[(w, h)]
            cnt[(w, h)] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        long ans = 0;
        int n = rectangles.length + 1;
        Map<Long, Integer> cnt = new HashMap<>();
        for (var e : rectangles) {
            int w = e[0], h = e[1];
            int g = gcd(w, h);
            w /= g;
            h /= g;
            long x = (long) w * n + h;
            ans += cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long interchangeableRectangles(vector<vector<int>>& rectangles) {
        long long ans = 0;
        int n = rectangles.size();
        unordered_map<long long, int> cnt;
        for (auto& e : rectangles) {
            int w = e[0], h = e[1];
            int g = gcd(w, h);
            w /= g;
            h /= g;
            long long x = 1ll * w * (n + 1) + h;
            ans += cnt[x];
            cnt[x]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func interchangeableRectangles(rectangles [][]int) int64 {
	ans := 0
	n := len(rectangles)
	cnt := map[int]int{}
	for _, e := range rectangles {
		w, h := e[0], e[1]
		g := gcd(w, h)
		w, h = w/g, h/g
		x := w*(n+1) + h
		ans += cnt[x]
		cnt[x]++
	}
	return int64(ans)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} rectangles
 * @return {number}
 */
var interchangeableRectangles = function (rectangles) {
    const cnt = new Map();
    let ans = 0;
    for (let [w, h] of rectangles) {
        const g = gcd(w, h);
        w = Math.floor(w / g);
        h = Math.floor(h / g);
        const x = w * (rectangles.length + 1) + h;
        ans += cnt.get(x) | 0;
        cnt.set(x, (cnt.get(x) | 0) + 1);
    }
    return ans;
};

function gcd(a, b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
