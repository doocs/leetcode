# [352. 将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals)

[English Version](/solution/0300-0399/0352.Data%20Stream%20as%20Disjoint%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数的数据流输入 a<sub>1</sub>，a<sub>2</sub>，&hellip;，a<sub>n，</sub>&hellip;，将到目前为止看到的数字总结为不相交的区间列表。</p>

<p>例如，假设数据流中的整数为 1，3，7，2，6，&hellip;，每次的总结为：</p>

<pre>[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong><br>
如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?</p>

<p><strong>提示：</strong><br>
特别感谢 <a href="https://discuss.leetcode.com/user/yunhong">@yunhong</a> 提供了本问题和其测试用例。</p>

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
        if lidx != n and ridx != n and values[lidx][1] + 1 == val and values[ridx][0] - 1 == val:
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
        if (l != mp.end() && r != mp.end() && l->second[1] + 1 == val && r->second[0] - 1 == val)
        {
            l->second[1] = r->second[1];
            mp.erase(r);
        }
        else if (l != mp.end() && val <= l->second[1] + 1) l->second[1] = max(val, l->second[1]);
        else if (r != mp.end() && val >= r->second[0] - 1) r->second[0] = min(val, r->second[0]);
        else mp[val] = {val, val};
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
