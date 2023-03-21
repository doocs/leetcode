# [352. Data Stream as Disjoint Intervals](https://leetcode.com/problems/data-stream-as-disjoint-intervals)

[中文文档](/solution/0300-0399/0352.Data%20Stream%20as%20Disjoint%20Intervals/README.md)

## Description

<p>Given a data stream input of non-negative integers <code>a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub></code>, summarize the numbers seen so far as a list of disjoint intervals.</p>

<p>Implement the <code>SummaryRanges</code> class:</p>

<ul>
	<li><code>SummaryRanges()</code> Initializes the object with an empty stream.</li>
	<li><code>void addNum(int value)</code> Adds the integer <code>value</code> to the stream.</li>
	<li><code>int[][] getIntervals()</code> Returns a summary of the integers in the stream currently as a list of disjoint intervals <code>[start<sub>i</sub>, end<sub>i</sub>]</code>. The answer should be sorted by <code>start<sub>i</sub></code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SummaryRanges&quot;, &quot;addNum&quot;, &quot;getIntervals&quot;, &quot;addNum&quot;, &quot;getIntervals&quot;, &quot;addNum&quot;, &quot;getIntervals&quot;, &quot;addNum&quot;, &quot;getIntervals&quot;, &quot;addNum&quot;, &quot;getIntervals&quot;]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
<strong>Output</strong>
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

<strong>Explanation</strong>
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // return [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= value &lt;= 10<sup>4</sup></code></li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>addNum</code> and <code>getIntervals</code>.</li>
	<li>At most <code>10<sup>2</sup></code>&nbsp;calls will be made to&nbsp;<code>getIntervals</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
            mp.put(val, new int[] {val, val});
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
