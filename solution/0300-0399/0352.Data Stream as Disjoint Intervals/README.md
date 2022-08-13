# [352. 将数据流变为多个不相交区间](https://leetcode.cn/problems/data-stream-as-disjoint-intervals)

[English Version](/solution/0300-0399/0352.Data%20Stream%20as%20Disjoint%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;给你一个由非负整数&nbsp;<code>a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub></code> 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。</p>

<p>实现 <code>SummaryRanges</code> 类：</p>

<div class="original__bRMd">
<div>
<ul>
	<li><code>SummaryRanges()</code> 使用一个空数据流初始化对象。</li>
	<li><code>void addNum(int val)</code> 向数据流中加入整数 <code>val</code> 。</li>
	<li><code>int[][] getIntervals()</code> 以不相交区间&nbsp;<code>[start<sub>i</sub>, end<sub>i</sub>]</code> 的列表形式返回对数据流中整数的总结。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
<strong>输出：</strong>
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

<strong>解释：</strong>
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= val &lt;= 10<sup>4</sup></code></li>
	<li>最多调用&nbsp;<code>addNum</code> 和 <code>getIntervals</code> 方法 <code>3 * 10<sup>4</sup></code> 次</li>
</ul>
</div>
</div>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedDict


class SummaryRanges:
    def __init__(self):
        self.mp = SortedDict()

    def addNum(self, val: int) -> None:
        n = len(self.mp)
        ridx = self.mp.bisect_right(val)
        lidx = n if ridx == 0 else ridx - 1
        keys = self.mp.keys()
        values = self.mp.values()
        if (
            lidx != n
            and ridx != n
            and values[lidx][1] + 1 == val
            and values[ridx][0] - 1 == val
        ):
            self.mp[keys[lidx]][1] = self.mp[keys[ridx]][1]
            self.mp.pop(keys[ridx])
        elif lidx != n and val <= values[lidx][1] + 1:
            self.mp[keys[lidx]][1] = max(val, self.mp[keys[lidx]][1])
        elif ridx != n and val >= values[ridx][0] - 1:
            self.mp[keys[ridx]][0] = min(val, self.mp[keys[ridx]][0])
        else:
            self.mp[val] = [val, val]

    def getIntervals(self) -> List[List[int]]:
        return list(self.mp.values())


# # Your SummaryRanges object will be instantiated and called as such:
# # obj = SummaryRanges()
# # obj.addNum(val)
# # param_2 = obj.getIntervals()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class SummaryRanges {
    private TreeMap<Integer, int[]> mp;

    public SummaryRanges() {
        mp = new TreeMap<>();
    }

    public void addNum(int val) {
        Integer l = mp.floorKey(val);
        Integer r = mp.ceilingKey(val);
        if (l != null && r != null && mp.get(l)[1] + 1 == val && mp.get(r)[0] - 1 == val) {
            mp.get(l)[1] = mp.get(r)[1];
            mp.remove(r);
        } else if (l != null && val <= mp.get(l)[1] + 1) {
            mp.get(l)[1] = Math.max(val, mp.get(l)[1]);
        } else if (r != null && val >= mp.get(r)[0] - 1) {
            mp.get(r)[0] = Math.min(val, mp.get(r)[0]);
        } else {
            mp.put(val, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[mp.size()][2];
        int i = 0;
        for (int[] range : mp.values()) {
            res[i++] = range;
        }
        return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
```

### **C++**

```cpp
class SummaryRanges {
private:
    map<int, vector<int>> mp;

public:
    SummaryRanges() {
    }

    void addNum(int val) {
        auto r = mp.upper_bound(val);
        auto l = r == mp.begin() ? mp.end() : prev(r);
        if (l != mp.end() && r != mp.end() && l->second[1] + 1 == val && r->second[0] - 1 == val) {
            l->second[1] = r->second[1];
            mp.erase(r);
        } else if (l != mp.end() && val <= l->second[1] + 1)
            l->second[1] = max(val, l->second[1]);
        else if (r != mp.end() && val >= r->second[0] - 1)
            r->second[0] = min(val, r->second[0]);
        else
            mp[val] = {val, val};
    }

    vector<vector<int>> getIntervals() {
        vector<vector<int>> res;
        for (auto& range : mp) res.push_back(range.second);
        return res;
    }
};

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges* obj = new SummaryRanges();
 * obj->addNum(val);
 * vector<vector<int>> param_2 = obj->getIntervals();
 */
```

### **...**

```

```

<!-- tabs:end -->
