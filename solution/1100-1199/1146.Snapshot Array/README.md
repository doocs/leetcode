# [1146. 快照数组](https://leetcode.cn/problems/snapshot-array)

[English Version](/solution/1100-1199/1146.Snapshot%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现支持下列接口的「快照数组」-&nbsp;SnapshotArray：</p>

<ul>
	<li><code>SnapshotArray(int length)</code>&nbsp;- 初始化一个与指定长度相等的 类数组 的数据结构。<strong>初始时，每个元素都等于</strong><strong>&nbsp;0</strong>。</li>
	<li><code>void set(index, val)</code>&nbsp;- 会将指定索引&nbsp;<code>index</code>&nbsp;处的元素设置为&nbsp;<code>val</code>。</li>
	<li><code>int snap()</code>&nbsp;- 获取该数组的快照，并返回快照的编号&nbsp;<code>snap_id</code>（快照号是调用&nbsp;<code>snap()</code>&nbsp;的总次数减去&nbsp;<code>1</code>）。</li>
	<li><code>int get(index, snap_id)</code>&nbsp;- 根据指定的&nbsp;<code>snap_id</code>&nbsp;选择快照，并返回该快照指定索引 <code>index</code>&nbsp;的值。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;SnapshotArray&quot;,&quot;set&quot;,&quot;snap&quot;,&quot;set&quot;,&quot;get&quot;]
     [[3],[0,5],[],[0,6],[0,0]]
<strong>输出：</strong>[null,null,0,null,5]
<strong>解释：
</strong>SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
snapshotArr.set(0,5);  // 令 array[0] = 5
snapshotArr.snap();  // 获取快照，返回 snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= length&nbsp;&lt;= 50000</code></li>
	<li>题目最多进行<code>50000</code> 次<code>set</code>，<code>snap</code>，和&nbsp;<code>get</code>的调用 。</li>
	<li><code>0 &lt;= index&nbsp;&lt;&nbsp;length</code></li>
	<li><code>0 &lt;=&nbsp;snap_id &lt;&nbsp;</code>我们调用&nbsp;<code>snap()</code>&nbsp;的总次数</li>
	<li><code>0 &lt;=&nbsp;val &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组 + 二分查找**

维护一个数组，数组中的每个元素是一个列表，列表中存储的是每次设置的值以及对应的快照编号。

每次设置值时，将值和快照编号添加到对应索引的列表中。

每次获取值时，使用二分查找，找到对应位置第一个大于快照编号 `snap_id` 的值，然后返回前一个值即可。

时间复杂度上，设置值的时间复杂度为 $O(1)$，快照的时间复杂度为 $O(1)$，获取值的时间复杂度为 $O(\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class SnapshotArray:

    def __init__(self, length: int):
        self.idx = 0
        self.arr = defaultdict(list)

    def set(self, index: int, val: int) -> None:
        self.arr[index].append((self.idx, val))

    def snap(self) -> int:
        self.idx += 1
        return self.idx - 1

    def get(self, index: int, snap_id: int) -> int:
        vals = self.arr[index]
        i = bisect_right(vals, (snap_id, inf)) - 1
        return 0 if i < 0 else vals[i][1]


# Your SnapshotArray object will be instantiated and called as such:
# obj = SnapshotArray(length)
# obj.set(index,val)
# param_2 = obj.snap()
# param_3 = obj.get(index,snap_id)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class SnapshotArray {
    private List<int[]>[] arr;
    private int idx;

    public SnapshotArray(int length) {
        arr = new List[length];
        Arrays.setAll(arr, k -> new ArrayList<>());
    }

    public void set(int index, int val) {
        arr[index].add(new int[] {idx, val});
    }

    public int snap() {
        return idx++;
    }

    public int get(int index, int snap_id) {
        var vals = arr[index];
        int left = 0, right = vals.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (vals.get(mid)[0] > snap_id) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == 0 ? 0 : vals.get(left - 1)[1];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
```

### **C++**

```cpp
class SnapshotArray {
public:
    SnapshotArray(int length) {
        idx = 0;
        arr = vector<vector<pair<int, int>>>(length);
    }

    void set(int index, int val) {
        arr[index].push_back({idx, val});
    }

    int snap() {
        return idx++;
    }

    int get(int index, int snap_id) {
        auto& vals = arr[index];
        int left = 0, right = vals.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (vals[mid].first > snap_id) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == 0 ? 0 : vals[left - 1].second;
    }

private:
    vector<vector<pair<int, int>>> arr;
    int idx;
};

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray* obj = new SnapshotArray(length);
 * obj->set(index,val);
 * int param_2 = obj->snap();
 * int param_3 = obj->get(index,snap_id);
 */
```

### **Go**

```go
type SnapshotArray struct {
	idx int
	arr [][]pair
}

func Constructor(length int) SnapshotArray {
	return SnapshotArray{0, make([][]pair, length)}
}

func (this *SnapshotArray) Set(index int, val int) {
	this.arr[index] = append(this.arr[index], pair{this.idx, val})
}

func (this *SnapshotArray) Snap() int {
	this.idx++
	return this.idx - 1
}

func (this *SnapshotArray) Get(index int, snap_id int) int {
	vals := this.arr[index]
	i := sort.Search(len(vals), func(i int) bool { return vals[i].i > snap_id })
	if i == 0 {
		return 0
	}
	return vals[i-1].v
}

type pair struct{ i, v int }

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * obj := Constructor(length);
 * obj.Set(index,val);
 * param_2 := obj.Snap();
 * param_3 := obj.Get(index,snap_id);
 */
```

### **...**

```

```

<!-- tabs:end -->
