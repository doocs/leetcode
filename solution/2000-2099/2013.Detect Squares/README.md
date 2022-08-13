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

哈希表实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class DetectSquares:
    def __init__(self):
        self.mp = defaultdict(Counter)

    def add(self, point: List[int]) -> None:
        x, y = point
        self.mp[x][y] += 1

    def count(self, point: List[int]) -> int:
        x, y = point
        ans = 0
        if x not in self.mp:
            return ans
        xcnt = self.mp[x]

        for x1, counter in self.mp.items():
            if x1 != x:
                d = x1 - x
                ans += xcnt[y + d] * counter[y] * counter[y + d]
                ans += xcnt[y - d] * counter[y] * counter[y - d]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();

    public DetectSquares() {

    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        if (!mp.containsKey(x)) {
            mp.put(x, new HashMap<>());
        }
        mp.get(x).put(y, mp.get(x).getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.containsKey(x)) {
            return ans;
        }
        Map<Integer, Integer> xcnt = mp.get(x);
        for (Map.Entry<Integer, Map<Integer, Integer>> e : mp.entrySet()) {
            int x1 = e.getKey();
            Map<Integer, Integer> counter = e.getValue();
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt.getOrDefault(y + d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y + d, 0);
                ans += xcnt.getOrDefault(y - d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y - d, 0);
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
    unordered_map<int, unordered_map<int, int>> mp;

    DetectSquares() {
    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++mp[x][y];
    }

    int count(vector<int> point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.count(x)) return ans;
        auto xcnt = mp[x];
        for (auto e : mp) {
            int x1 = e.first;
            auto counter = e.second;
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt[y + d] * counter[y] * counter[y + d];
                ans += xcnt[y - d] * counter[y] * counter[y - d];
            }
        }
        return ans;
    }
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
```

### **...**

```

```

<!-- tabs:end -->
