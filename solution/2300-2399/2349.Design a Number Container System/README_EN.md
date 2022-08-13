# [2349. Design a Number Container System](https://leetcode.com/problems/design-a-number-container-system)

[中文文档](/solution/2300-2399/2349.Design%20a%20Number%20Container%20System/README.md)

## Description

<p>Design a number container system that can do the following:</p>

<ul>
	<li><strong>Insert </strong>or <strong>Replace</strong> a number at the given index in the system.</li>
	<li><strong>Return </strong>the smallest index for the given number in the system.</li>
</ul>

<p>Implement the <code>NumberContainers</code> class:</p>

<ul>
	<li><code>NumberContainers()</code> Initializes the number container system.</li>
	<li><code>void change(int index, int number)</code> Fills the container at <code>index</code> with the <code>number</code>. If there is already a number at that <code>index</code>, replace it.</li>
	<li><code>int find(int number)</code> Returns the smallest index for the given <code>number</code>, or <code>-1</code> if there is no index that is filled by <code>number</code> in the system.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumberContainers&quot;, &quot;find&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;find&quot;, &quot;change&quot;, &quot;find&quot;]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
<strong>Output</strong>
[null, -1, null, null, null, null, 1, null, 2]

<strong>Explanation</strong>
NumberContainers nc = new NumberContainers();
nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
nc.change(2, 10); // Your container at index 2 will be filled with number 10.
nc.change(1, 10); // Your container at index 1 will be filled with number 10.
nc.change(3, 10); // Your container at index 3 will be filled with number 10.
nc.change(5, 10); // Your container at index 5 will be filled with number 10.
nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20. 
nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= index, number &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made <strong>in total</strong> to <code>change</code> and <code>find</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedSet


class NumberContainers:
    def __init__(self):
        self.mp = {}
        self.t = defaultdict(SortedSet)

    def change(self, index: int, number: int) -> None:
        if index in self.mp:
            v = self.mp[index]
            self.t[v].remove(index)
        self.mp[index] = number
        self.t[number].add(index)

    def find(self, number: int) -> int:
        s = self.t[number]
        return s[0] if s else -1


# Your NumberContainers object will be instantiated and called as such:
# obj = NumberContainers()
# obj.change(index,number)
# param_2 = obj.find(number)
```

### **Java**

```java
class NumberContainers {
    private Map<Integer, Integer> mp = new HashMap<>();
    private Map<Integer, TreeSet<Integer>> t = new HashMap<>();

    public NumberContainers() {

    }

    public void change(int index, int number) {
        if (mp.containsKey(index)) {
            int v = mp.get(index);
            t.get(v).remove(index);
            if (t.get(v).isEmpty()) {
                t.remove(v);
            }
        }
        mp.put(index, number);
        t.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        return t.containsKey(number) ? t.get(number).first() : -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
```

### **C++**

```cpp
class NumberContainers {
public:
    map<int, int> mp;
    map<int, set<int>> t;

    NumberContainers() {
    }

    void change(int index, int number) {
        auto it = mp.find(index);
        if (it != mp.end()) {
            t[it->second].erase(index);
            it->second = number;
        } else
            mp[index] = number;
        t[number].insert(index);
    }

    int find(int number) {
        auto it = t.find(number);
        return it == t.end() || it->second.empty() ? -1 : *it->second.begin();
    }
};

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers* obj = new NumberContainers();
 * obj->change(index,number);
 * int param_2 = obj->find(number);
 */
```

### **Go**

```go
type NumberContainers struct {
	mp map[int]int
	t  map[int]*redblacktree.Tree
}

func Constructor() NumberContainers {
	return NumberContainers{map[int]int{}, map[int]*redblacktree.Tree{}}
}

func (this *NumberContainers) Change(index int, number int) {
	if num, ok := this.mp[index]; ok {
		this.t[num].Remove(index)
	}
	this.mp[index] = number
	if this.t[number] == nil {
		this.t[number] = redblacktree.NewWithIntComparator()
	}
	this.t[number].Put(index, nil)
}

func (this *NumberContainers) Find(number int) int {
	s, ok := this.t[number]
	if !ok || s.Size() == 0 {
		return -1
	}
	return s.Left().Key.(int)
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Change(index,number);
 * param_2 := obj.Find(number);
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
