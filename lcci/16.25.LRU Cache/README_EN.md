# [16.25. LRU Cache](https://leetcode-cn.com/problems/lru-cache-lcci)

## Description
<p>Design and build a &quot;least recently used&quot; cache, which evicts the least recently used item. The cache should map from keys to values (allowing you to insert and retrieve a value associ&shy;ated with a particular key) and be initialized with a max size. When it is full, it should evict the least recently used item.</p>

<p>You should implement following operations:&nbsp;&nbsp;<code>get</code>&nbsp;and <code>put</code>.</p>

<p>Get a value by key:&nbsp;<code>get(key)</code> - If key is in the cache, return the value, otherwise return -1.<br />
Write a key-value pair to the cache:&nbsp;<code>put(key, value)</code> - If the key is not in the cache, then write its value to the cache. Evict the least recently used item before writing if necessary.</p>

<p><strong>Example:</strong></p>

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



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
