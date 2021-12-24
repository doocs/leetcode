# [460. LFU 缓存](https://leetcode-cn.com/problems/lfu-cache)

[English Version](/solution/0400-0499/0460.LFU%20Cache/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你为 <a href="https://baike.baidu.com/item/%E7%BC%93%E5%AD%98%E7%AE%97%E6%B3%95">最不经常使用（LFU）</a>缓存算法设计并实现数据结构。</p>

<p>实现 <code>LFUCache</code> 类：</p>

<ul>
	<li><code>LFUCache(int capacity)</code> - 用数据结构的容量 <code>capacity</code> 初始化对象</li>
	<li><code>int get(int key)</code> - 如果键存在于缓存中，则获取键的值，否则返回 -1。</li>
	<li><code>void put(int key, int value)</code> - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 <strong>最近最久未使用</strong> 的键。</li>
</ul>

<p><strong>注意</strong>「项的使用次数」就是自插入该项以来对其调用 <code>get</code> 和 <code>put</code> 函数的次数之和。使用次数会在对应项被移除后置为 0 。</p>

<p>为了确定最不常使用的键，可以为缓存中的每个键维护一个 <strong>使用计数器</strong> 。使用计数最小的键是最久未使用的键。</p>

<p>当一个键首次插入到缓存中时，它的使用计数器被设置为 <code>1</code> (由于 put 操作)。对缓存中的键执行 <code>get</code> 或 <code>put</code> 操作，使用计数器的值将会递增。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
<strong>输出：</strong>
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

<strong>解释：</strong>
// cnt(x) = 键 x 的使用计数
// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
LFUCache lFUCache = new LFUCache(2);
lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lFUCache.get(1);      // 返回 1
                      // cache=[1,2], cnt(2)=1, cnt(1)=2
lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
                      // cache=[3,1], cnt(3)=1, cnt(1)=2
lFUCache.get(2);      // 返回 -1（未找到）
lFUCache.get(3);      // 返回 3
                      // cache=[3,1], cnt(3)=2, cnt(1)=2
lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
                      // cache=[4,3], cnt(4)=1, cnt(3)=2
lFUCache.get(1);      // 返回 -1（未找到）
lFUCache.get(3);      // 返回 3
                      // cache=[3,4], cnt(4)=1, cnt(3)=3
lFUCache.get(4);      // 返回 4
                      // cache=[3,4], cnt(4)=2, cnt(3)=3</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= capacity, key, value <= 10<sup>4</sup></code></li>
	<li>最多调用 <code>10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code> 方法</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以为这两种操作设计时间复杂度为 <code>O(1)</code> 的实现吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
