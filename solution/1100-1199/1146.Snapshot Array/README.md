# [1146. 快照数组](https://leetcode.cn/problems/snapshot-array)

[English Version](/solution/1100-1199/1146.Snapshot%20Array/README_EN.md)

<!-- tags:设计,数组,哈希表,二分查找 -->

<!-- difficulty:中等 -->

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

### 方法一：数组 + 二分查找

我们维护一个长度为 $\text{length}$ 的数组，数组中的每个元素是一个列表，用来存储每次设置的值以及对应的快照 ID。

调用 `set` 方法时，将值和快照 ID 添加到对应索引的列表中。时间复杂度 $O(1)$。

调用 `snap` 方法时，我们先将快照 ID 加一，然后返回快照 ID 减一。时间复杂度 $O(1)$。

调用 `get` 方法时，我们使用二分查找找到对应位置的第一个快照 ID 大于 `snap_id` 的值，然后返回前一个的值。如果找不到，则返回 0。时间复杂度 $O(\log n)$。

空间复杂度 $O(n)$。

<!-- tabs:start -->

```python
class SnapshotArray:

    def __init__(self, length: int):
        self.arr = [[] for _ in range(length)]
        self.i = 0

    def set(self, index: int, val: int) -> None:
        self.arr[index].append((self.i, val))

    def snap(self) -> int:
        self.i += 1
        return self.i - 1

    def get(self, index: int, snap_id: int) -> int:
        i = bisect_left(self.arr[index], (snap_id, inf)) - 1
        return 0 if i < 0 else self.arr[index][i][1]


# Your SnapshotArray object will be instantiated and called as such:
# obj = SnapshotArray(length)
# obj.set(index,val)
# param_2 = obj.snap()
# param_3 = obj.get(index,snap_id)
```

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
        int l = 0, r = arr[index].size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[index].get(mid)[0] > snap_id) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        --l;
        return l < 0 ? 0 : arr[index].get(l)[1];
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

```cpp
class SnapshotArray {
public:
    SnapshotArray(int length) {
        arr.resize(length);
    }

    void set(int index, int val) {
        arr[index].emplace_back(i, val);
    }

    int snap() {
        return i++;
    }

    int get(int index, int snap_id) {
        auto it = upper_bound(arr[index].begin(), arr[index].end(), make_pair(snap_id, INT_MAX));
        return it == arr[index].begin() ? 0 : prev(it)->second;
    }

private:
    vector<vector<pair<int, int>>> arr;
    int i = 0;
};

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray* obj = new SnapshotArray(length);
 * obj->set(index,val);
 * int param_2 = obj->snap();
 * int param_3 = obj->get(index,snap_id);
 */
```

```go
type SnapshotArray struct {
	arr [][][2]int
	i   int
}

func Constructor(length int) SnapshotArray {
	return SnapshotArray{make([][][2]int, length), 0}
}

func (this *SnapshotArray) Set(index int, val int) {
	this.arr[index] = append(this.arr[index], [2]int{this.i, val})
}

func (this *SnapshotArray) Snap() int {
	this.i++
	return this.i - 1
}

func (this *SnapshotArray) Get(index int, snap_id int) int {
	i := sort.Search(len(this.arr[index]), func(i int) bool { return this.arr[index][i][0] > snap_id }) - 1
	if i < 0 {
		return 0
	}
	return this.arr[index][i][1]
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * obj := Constructor(length);
 * obj.Set(index,val);
 * param_2 := obj.Snap();
 * param_3 := obj.Get(index,snap_id);
 */
```

```ts
class SnapshotArray {
    private arr: [number, number][][];
    private i: number = 0;
    constructor(length: number) {
        this.arr = Array.from({ length }, () => []);
    }

    set(index: number, val: number): void {
        this.arr[index].push([this.i, val]);
    }

    snap(): number {
        return this.i++;
    }

    get(index: number, snap_id: number): number {
        let [l, r] = [0, this.arr[index].length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (this.arr[index][mid][0] > snap_id) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        --l;
        return l < 0 ? 0 : this.arr[index][l][1];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * var obj = new SnapshotArray(length)
 * obj.set(index,val)
 * var param_2 = obj.snap()
 * var param_3 = obj.get(index,snap_id)
 */
```

<!-- tabs:end -->

<!-- end -->
