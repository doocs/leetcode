# [1146. Snapshot Array](https://leetcode.com/problems/snapshot-array)

[中文文档](/solution/1100-1199/1146.Snapshot%20Array/README.md)

## Description

<p>Implement a SnapshotArray that supports the following interface:</p>

<ul>
	<li><code>SnapshotArray(int length)</code> initializes an array-like data structure with the given length. <strong>Initially, each element equals 0</strong>.</li>
	<li><code>void set(index, val)</code> sets the element at the given <code>index</code> to be equal to <code>val</code>.</li>
	<li><code>int snap()</code> takes a snapshot of the array and returns the <code>snap_id</code>: the total number of times we called <code>snap()</code> minus <code>1</code>.</li>
	<li><code>int get(index, snap_id)</code> returns the value at the given <code>index</code>, at the time we took the snapshot with the given <code>snap_id</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> [&quot;SnapshotArray&quot;,&quot;set&quot;,&quot;snap&quot;,&quot;set&quot;,&quot;get&quot;]
[[3],[0,5],[],[0,6],[0,0]]
<strong>Output:</strong> [null,null,0,null,5]
<strong>Explanation: </strong>
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= index &lt; length</code></li>
	<li><code>0 &lt;= val &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= snap_id &lt; </code>(the total number of times we call <code>snap()</code>)</li>
	<li>At most <code>5 * 10<sup>4</sup></code> calls will be made to <code>set</code>, <code>snap</code>, and <code>get</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
