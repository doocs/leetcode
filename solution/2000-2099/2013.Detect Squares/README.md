# [2013. 检测正方形](https://leetcode.cn/problems/detect-squares)

[English Version](/solution/2000-2099/2013.Detect%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：</p>

<ul>
	<li><strong>添加</strong> 一个在数据流中的新点到某个数据结构中<strong>。</strong>可以添加 <strong>重复</strong> 的点，并会视作不同的点进行处理。</li>
	<li>给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 <strong>面积为正</strong> 的 <strong>轴对齐正方形</strong> ，<strong>统计</strong> 满足该要求的方案数目<strong>。</strong></li>
</ul>

<p><strong>轴对齐正方形</strong> 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。</p>

<p>实现 <code>DetectSquares</code> 类：</p>

<ul>
	<li><code>DetectSquares()</code> 使用空数据结构初始化对象</li>
	<li><code>void add(int[] point)</code> 向数据结构添加一个新的点 <code>point = [x, y]</code></li>
	<li><code>int count(int[] point)</code> 统计按上述方式与点 <code>point = [x, y]</code> 共同构造 <strong>轴对齐正方形</strong> 的方案数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2013.Detect%20Squares/images/image.png" style="width: 869px; height: 504px;" />
<pre>
<strong>输入：</strong>
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
<strong>输出：</strong>
[null, null, null, null, 1, 0, null, 2]

<strong>解释：</strong>
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // 返回 1 。你可以选择：
// - 第一个，第二个，和第三个点
detectSquares.count([14, 8]); // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
detectSquares.add([11, 2]); // 允许添加重复的点。
detectSquares.count([11, 10]); // 返回 2 。你可以选择：
// - 第一个，第二个，和第三个点
// - 第一个，第三个，和第四个点

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>point.length == 2</code></li>
	<li><code>0 &lt;= x, y &lt;= 1000</code></li>
	<li>调用&nbsp;<code>add</code> 和 <code>count</code> 的 <strong>总次数</strong> 最多为 <code>5000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以用一个哈希表 $cnt$ 维护所有点的信息，其中 $cnt[x][y]$ 表示点 $(x, y)$ 的个数。

当调用 $add(x, y)$ 方法时，我们将 $cnt[x][y]$ 的值加 $1$。

当调用 $count(x_1, y_1)$ 方法时，我们需要获取另外的三个点，构成一个轴对齐正方形。我们可以枚举平行于 $x$ 轴且与 $(x_1, y_1)$ 的距离为 $d$ 的点 $(x_2, y_1)$，如果存在这样的点，根据这两个点，我们可以确定另外两个点为 $(x_1, y_1 + d)$ 和 $(x_2, y_1 + d)$，或者 $(x_1, y_1 - d)$ 和 $(x_2, y_1 - d)$。我们将这两种情况的方案数累加即可。

时间复杂度方面，调用 $add(x, y)$ 方法的时间复杂度为 $O(1)$，调用 $count(x_1, y_1)$ 方法的时间复杂度为 $O(n)$；空间复杂度为 $O(n)$。其中 $n$ 为数据流中的点的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class DetectSquares:
    def __init__(self):
        self.cnt = defaultdict(Counter)

    def add(self, point: List[int]) -> None:
        x, y = point
        self.cnt[x][y] += 1

    def count(self, point: List[int]) -> int:
        x1, y1 = point
        if x1 not in self.cnt:
            return 0
        ans = 0
        for x2 in self.cnt.keys():
            if x2 != x1:
                d = x2 - x1
                ans += self.cnt[x2][y1] * self.cnt[x1][y1 + d] * self.cnt[x2][y1 + d]
                ans += self.cnt[x2][y1] * self.cnt[x1][y1 - d] * self.cnt[x2][y1 - d]
        return ans


# Your DetectSquares object will be instantiated and called as such:
# obj = DetectSquares()
# obj.add(point)
# param_2 = obj.count(point)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();

    public DetectSquares() {
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        cnt.computeIfAbsent(x, k -> new HashMap<>()).merge(y, 1, Integer::sum);
    }

    public int count(int[] point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.containsKey(x1)) {
            return 0;
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x2 = e.getKey();
            if (x2 != x1) {
                int d = x2 - x1;
                var cnt1 = cnt.get(x1);
                var cnt2 = e.getValue();
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 + d, 0)
                    * cnt2.getOrDefault(y1 + d, 0);
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 - d, 0)
                    * cnt2.getOrDefault(y1 - d, 0);
            }
        }
        return ans;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
```

### **C++**

```cpp
class DetectSquares {
public:
    DetectSquares() {

    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++cnt[x][y];
    }

    int count(vector<int> point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.count(x1)) {
            return 0;
        }
        int ans = 0;
        for (auto& [x2, cnt2] : cnt) {
            if (x2 != x1) {
                int d = x2 - x1;
                auto& cnt1 = cnt[x1];
                ans += cnt2[y1] * cnt1[y1 + d] * cnt2[y1 + d];
                ans += cnt2[y1] * cnt1[y1 - d] * cnt2[y1 - d];
            }
        }
        return ans;
    }

private:
    unordered_map<int, unordered_map<int, int>> cnt;
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
```

### **Go**

```go
type DetectSquares struct {
	cnt map[int]map[int]int
}

func Constructor() DetectSquares {
	return DetectSquares{map[int]map[int]int{}}
}

func (this *DetectSquares) Add(point []int) {
	x, y := point[0], point[1]
	if _, ok := this.cnt[x]; !ok {
		this.cnt[x] = map[int]int{}
	}
	this.cnt[x][y]++
}

func (this *DetectSquares) Count(point []int) (ans int) {
	x1, y1 := point[0], point[1]
	if cnt1, ok := this.cnt[x1]; ok {
		for x2, cnt2 := range this.cnt {
			if x2 != x1 {
				d := x2 - x1
				ans += cnt2[y1] * cnt1[y1+d] * cnt2[y1+d]
				ans += cnt2[y1] * cnt1[y1-d] * cnt2[y1-d]
			}
		}
	}
	return
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(point);
 * param_2 := obj.Count(point);
 */
```

### **...**

```

```

<!-- tabs:end -->
