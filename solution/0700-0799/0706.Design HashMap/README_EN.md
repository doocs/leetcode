# [706. Design HashMap](https://leetcode.com/problems/design-hashmap)

[中文文档](/solution/0700-0799/0706.Design%20HashMap/README.md)

## Description

<p>Design a HashMap&nbsp;without using any built-in hash table libraries.</p>

<p>To be specific, your design should include these functions:</p>

<ul>
    <li><code>put(key, value)</code> :&nbsp;Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.</li>
    <li><code>get(key)</code>: Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.</li>
    <li><code>remove(key)</code> :&nbsp;Remove the mapping for the value key if this map contains the mapping for the key.</li>
</ul>

<p><br />

<strong>Example:</strong></p>

<pre>

MyHashMap hashMap = new MyHashMap();

hashMap.put(1, 1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

hashMap.put(2, 2); &nbsp; &nbsp; &nbsp; &nbsp; 

hashMap.get(1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns 1

hashMap.get(3); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns -1 (not found)

hashMap.put(2, 1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// update the existing value

hashMap.get(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns 1 

hashMap.remove(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// remove the mapping for 2

hashMap.get(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns -1 (not found) 

</pre>

<p><br />

<strong>Note:</strong></p>

<ul>
    <li>All keys and values will be in the range of <code>[0, 1000000]</code>.</li>
    <li>The number of operations will be in the range of&nbsp;<code>[1, 10000]</code>.</li>
    <li>Please do not use the built-in HashMap library.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MyHashMap:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.data = [-1] * 1000001

    def put(self, key: int, value: int) -> None:
        """
        value will always be non-negative.
        """
        self.data[key] = value

    def get(self, key: int) -> int:
        """
        Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
        """
        return self.data[key]

    def remove(self, key: int) -> None:
        """
        Removes the mapping of the specified value key if this map contains a mapping for the key
        """
        self.data[key] = -1



# Your MyHashMap object will be instantiated and called as such:
# obj = MyHashMap()
# obj.put(key,value)
# param_2 = obj.get(key)
# obj.remove(key)
```

### **Java**

```java
class MyHashMap {

    private int[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new int[1000001];
        Arrays.fill(data, -1);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        data[key] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return data[key];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        data[key] = -1;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
```

### **...**

```

```

<!-- tabs:end -->
