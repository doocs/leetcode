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

数组实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MyHashSet:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.data = [False] * 1000001

    def add(self, key: int) -> None:
        self.data[key] = True

    def remove(self, key: int) -> None:
        self.data[key] = False

    def contains(self, key: int) -> bool:
        """
        Returns true if this set contains the specified element
        """
        return self.data[key]


# Your MyHashSet object will be instantiated and called as such:
# obj = MyHashSet()
# obj.add(key)
# obj.remove(key)
# param_3 = obj.contains(key)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

-   可以一次性开辟一个大的数组，存放所有元素。

```java
class MyHashSet {

    private boolean[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new boolean[1000001];
    }

    public void add(int key) {
        data[key] = true;
    }

    public void remove(int key) {
        data[key] = false;
    }

    /** Returns true if this set contains the specified element */
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

-   也可以开辟一个大小为 `SIZE` 的数组，数组的每个位置是一个链表。

```java
class MyHashSet {

    private static final int SIZE = 1000;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[SIZE];
        Arrays.fill(data, new LinkedList<Integer>());
    }

    public void add(int key) {
        int index = hash(key);
        Iterator<Integer> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Integer e = iterator.next();
            if (e == key) return;
        }
        data[index].addFirst(key);
    }

    public void remove(int key) {
        int index = hash(key);
        ListIterator<Integer> iterator = data[index].listIterator();
        while (iterator.hasNext()) {
            Integer e = iterator.next();
            if (e == key) iterator.remove();
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hash(key);
        Iterator<Integer> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Integer e = iterator.next();
            if (e == key) return true;
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
