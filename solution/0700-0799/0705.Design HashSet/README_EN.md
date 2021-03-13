# [705. Design HashSet](https://leetcode.com/problems/design-hashset)

[中文文档](/solution/0700-0799/0705.Design%20HashSet/README.md)

## Description

<p>Design a HashSet&nbsp;without using any built-in hash table libraries.</p>

<p>To be specific, your design should include these functions:</p>

<ul>
    <li><code>add(value)</code>:&nbsp;Insert a value into the HashSet.&nbsp;</li>
    <li><code>contains(value)</code> : Return whether the value exists in the HashSet or not.</li>
    <li><code>remove(value)</code>: Remove a value in&nbsp;the HashSet. If the value does not exist in the HashSet, do nothing.</li>
</ul>

<p><br />

<strong>Example:</strong></p>

<pre>

MyHashSet hashSet = new MyHashSet();

hashSet.add(1); &nbsp; &nbsp; &nbsp; &nbsp; 

hashSet.add(2); &nbsp; &nbsp; &nbsp; &nbsp; 

hashSet.contains(1); &nbsp;&nbsp;&nbsp;// returns true

hashSet.contains(3); &nbsp;&nbsp;&nbsp;// returns false (not found)

hashSet.add(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

hashSet.contains(2); &nbsp;&nbsp;&nbsp;// returns true

hashSet.remove(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

hashSet.contains(2); &nbsp;&nbsp;&nbsp;// returns false (already removed)

</pre>

<p><br />

<strong>Note:</strong></p>

<ul>
    <li>All values will be in the range of <code>[0, 1000000]</code>.</li>
    <li>The number of operations will be in the range of&nbsp;<code>[1, 10000]</code>.</li>
    <li>Please do not use the built-in HashSet library.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **...**

```

```

<!-- tabs:end -->
