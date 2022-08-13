# [2349. 设计数字容器系统](https://leetcode.cn/problems/design-a-number-container-system)

[English Version](/solution/2300-2399/2349.Design%20a%20Number%20Container%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个数字容器系统，可以实现以下功能：</p>

<ul>
	<li>在系统中给定下标处&nbsp;<strong>插入</strong>&nbsp;或者 <strong>替换</strong>&nbsp;一个数字。</li>
	<li><strong>返回</strong>&nbsp;系统中给定数字的最小下标。</li>
</ul>

<p>请你实现一个&nbsp;<code>NumberContainers</code>&nbsp;类：</p>

<ul>
	<li><code>NumberContainers()</code>&nbsp;初始化数字容器系统。</li>
	<li><code>void change(int index, int number)</code> 在下标&nbsp;<code>index</code>&nbsp;处填入&nbsp;<code>number</code>&nbsp;。如果该下标&nbsp;<code>index</code>&nbsp;处已经有数字了，那么用 <code>number</code>&nbsp;替换该数字。</li>
	<li><code>int find(int number)</code>&nbsp;返回给定数字&nbsp;<code>number</code>&nbsp;在系统中的最小下标。如果系统中没有&nbsp;<code>number</code>&nbsp;，那么返回&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
<strong>输出：</strong>
[null, -1, null, null, null, null, 1, null, 2]

<strong>解释：</strong>
NumberContainers nc = new NumberContainers();
nc.find(10); // 没有数字 10 ，所以返回 -1 。
nc.change(2, 10); // 容器中下标为 2 处填入数字 10 。
nc.change(1, 10); // 容器中下标为 1 处填入数字 10 。
nc.change(3, 10); // 容器中下标为 3 处填入数字 10 。
nc.change(5, 10); // 容器中下标为 5 处填入数字 10 。
nc.find(10); // 数字 10 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10 ，现在被替换为 20 。
nc.find(10); // 数字 10 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= index, number &lt;= 10<sup>9</sup></code></li>
	<li>调用&nbsp;<code>change</code> 和&nbsp;<code>find</code>&nbsp;的&nbsp;<strong>总次数</strong>&nbsp;不超过&nbsp;<code>10<sup>5</sup></code> 次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
