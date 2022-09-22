# [705. 设计哈希集合](https://leetcode.cn/problems/design-hashset)

[English Version](/solution/0700-0799/0705.Design%20HashSet/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>不使用任何内建的哈希表库设计一个哈希集合（HashSet）。</p>

<p>实现 <code>MyHashSet</code> 类：</p>

<ul>
	<li><code>void add(key)</code> 向哈希集合中插入值 <code>key</code> 。</li>
	<li><code>bool contains(key)</code> 返回哈希集合中是否存在这个值 <code>key</code> 。</li>
	<li><code>void remove(key)</code> 将给定值 <code>key</code> 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。</li>
</ul>
&nbsp;

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
<strong>输出：</strong>
[null, null, null, true, false, null, true, null, false]

<strong>解释：</strong>
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // 返回 True
myHashSet.contains(3); // 返回 False ，（未找到）
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // 返回 True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // 返回 False ，（已移除）</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= key &lt;= 10<sup>6</sup></code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>add</code>、<code>remove</code> 和 <code>contains</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：静态数组实现**

直接创建一个大小为 $1000001$ 的数组，初始时数组中的每个元素都为 `false`，表示哈希集合中不存在该元素。

往哈希集合添加元素时，将数组中对应位置的值置为 `true`；删除元素时，将数组中对应位置的值置为 `false`；当查询元素是否存在时，直接返回数组中对应位置的值即可。

以上操作的时间复杂度均为 $O(1)$。

**方法二：数组嵌套链表**

我们也可以开辟一个大小为 `SIZE=1000` 的数组，数组的每个位置是一个链表。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MyHashSet:

    def __init__(self):
        self.data = [False] * 1000001

    def add(self, key: int) -> None:
        self.data[key] = True

    def remove(self, key: int) -> None:
        self.data[key] = False

    def contains(self, key: int) -> bool:
        return self.data[key]


# Your MyHashSet object will be instantiated and called as such:
# obj = MyHashSet()
# obj.add(key)
# obj.remove(key)
# param_3 = obj.contains(key)
```

```python
class MyHashSet:

    def __init__(self):
        self.size = 1000
        self.data = [[] for _ in range(self.size)]

    def add(self, key: int) -> None:
        if self.contains(key):
            return
        idx = self.hash(key)
        self.data[idx].append(key)

    def remove(self, key: int) -> None:
        if not self.contains(key):
            return
        idx = self.hash(key)
        self.data[idx].remove(key)

    def contains(self, key: int) -> bool:
        idx = self.hash(key)
        return any(v == key for v in self.data[idx])

    def hash(self, key) -> int:
        return key % self.size


# Your MyHashSet object will be instantiated and called as such:
# obj = MyHashSet()
# obj.add(key)
# obj.remove(key)
# param_3 = obj.contains(key)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MyHashSet {
    private boolean[] data = new boolean[1000001];

    public MyHashSet() {

    }

    public void add(int key) {
        data[key] = true;
    }

    public void remove(int key) {
        data[key] = false;
    }

    public boolean contains(int key) {
        return data[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
```

```java
class MyHashSet {
    private static final int SIZE = 1000;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        if (contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].addFirst(key);
    }

    public void remove(int key) {
        if (!contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].remove(Integer.valueOf(key));
    }

    public boolean contains(int key) {
        int idx = hash(key);
        Iterator<Integer> it = data[idx].iterator();
        while (it.hasNext()) {
            Integer e = it.next();
            if (e == key) {
                return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return key % SIZE;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
```

### **C++**

```cpp
class MyHashSet {
public:
    bool data[1000001];

    MyHashSet() {
        memset(data, false, sizeof data);
    }

    void add(int key) {
        data[key] = true;
    }

    void remove(int key) {
        data[key] = false;
    }

    bool contains(int key) {
        return data[key];
    }
};

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet* obj = new MyHashSet();
 * obj->add(key);
 * obj->remove(key);
 * bool param_3 = obj->contains(key);
 */
```

```cpp
class MyHashSet {
private:
    int size = 1000;
    vector<list<int>> data;

public:
    MyHashSet(): data(size) {

    }

    void add(int key) {
        if (contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].push_back(key);
    }

    void remove(int key) {
        if (!contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].remove(key);
    }

    bool contains(int key) {
        int idx = hash(key);
        for (auto it = data[idx].begin(); it != data[idx].end(); it++) {
            if ((*it) == key) {
                return true;
            }
        }
        return false;
    }

    int hash(int key) {
        return key % size;
    }
};

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet* obj = new MyHashSet();
 * obj->add(key);
 * obj->remove(key);
 * bool param_3 = obj->contains(key);
 */
```

### **Go**

```go
type MyHashSet struct {
	data []bool
}

func Constructor() MyHashSet {
	data := make([]bool, 1000010)
	return MyHashSet{data}
}

func (this *MyHashSet) Add(key int) {
	this.data[key] = true
}

func (this *MyHashSet) Remove(key int) {
	this.data[key] = false
}

func (this *MyHashSet) Contains(key int) bool {
	return this.data[key]
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(key);
 * obj.Remove(key);
 * param_3 := obj.Contains(key);
 */
```

```go
type MyHashSet struct {
	data []list.List
}

func Constructor() MyHashSet {
	return MyHashSet{make([]list.List, 1000)}
}

func (this *MyHashSet) Add(key int) {
	if this.Contains(key) {
		return
	}
	idx := this.hash(key)
	this.data[idx].PushBack(key)
}

func (this *MyHashSet) Remove(key int) {
	idx := this.hash(key)
	for e := this.data[idx].Front(); e != nil; e = e.Next() {
		if e.Value.(int) == key {
			this.data[idx].Remove(e)
		}
	}
}

func (this *MyHashSet) Contains(key int) bool {
	idx := this.hash(key)
	for e := this.data[idx].Front(); e != nil; e = e.Next() {
		if e.Value.(int) == key {
			return true
		}
	}
	return false
}

func (this *MyHashSet) hash(key int) int {
	return key % len(this.data)
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(key);
 * obj.Remove(key);
 * param_3 := obj.Contains(key);
 */
```

### **TypeScript**

```ts
class MyHashSet {
    data: Array<boolean>;
    constructor() {
        this.data = new Array(10 ** 6 + 1).fill(false);
    }

    add(key: number): void {
        this.data[key] = true;
    }

    remove(key: number): void {
        this.data[key] = false;
    }

    contains(key: number): boolean {
        return this.data[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * var obj = new MyHashSet()
 * obj.add(key)
 * obj.remove(key)
 * var param_3 = obj.contains(key)
 */
```

### **...**

```

```

<!-- tabs:end -->
