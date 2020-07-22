# [146. LRU Cache](https://leetcode.com/problems/lru-cache)

## Description
<p>Design and implement a data structure for <a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a>. It should support the following operations: <code>get</code> and <code>put</code>.</p>



<p><code>get(key)</code> - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.<br />

<code>put(key, value)</code> - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.</p>



<p>The cache is initialized with a <strong>positive</strong> capacity.</p>



<p><b>Follow up:</b><br />

Could you do both operations in <b>O(1)</b> time complexity?</p>



<p><b>Example:</b></p>



<pre>

LRUCache cache = new LRUCache( 2 /* capacity */ );



cache.put(1, 1);

cache.put(2, 2);

cache.get(1);       // returns 1

cache.put(3, 3);    // evicts key 2

cache.get(2);       // returns -1 (not found)

cache.put(4, 4);    // evicts key 1

cache.get(1);       // returns -1 (not found)

cache.get(3);       // returns 3

cache.get(4);       // returns 4

</pre>



<p>&nbsp;</p>




## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->